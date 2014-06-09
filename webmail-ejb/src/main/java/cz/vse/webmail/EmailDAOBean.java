/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.EmailListFilter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Petr
 */
@Stateless
public class EmailDAOBean implements EmailDAO {
    
    @PersistenceContext(unitName = "webmail")
    private EntityManager entityManager;
    
    @Override
    public List<Email> getEmailsOfUser(User user) {
        return entityManager.createQuery("from Email e where e.owner = :user order by e.createdAt desc")
            .setParameter("user", user).getResultList();
    }
    
    @Override
    public void saveEmail(Email email) {
        entityManager.persist(email);
    }
    
    @Override
    public Email getEmailOfUser(Integer emailId, User user) {
        return entityManager.createQuery("from Email e where e.id = :id and e.owner = :user", Email.class)
            .setParameter("id", emailId).setParameter("user", user).getSingleResult();
    }
    
    @Override
    public List<Email> getFilteredEmails(User user, EmailListFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Email> criteria = cb.createQuery(Email.class);
        Root<Email> root = criteria.from(Email.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.<User>get("owner"), user));
        if (StringUtils.isNoneBlank(filter.getTo())) {
            predicates.add(cb.like(root.<String>get("to"), "%" + filter.getTo() + "%"));
        }
        criteria.select(root).where(predicates.toArray(new Predicate[] {  }));
        return entityManager.createQuery(criteria).getResultList();
    }
}
