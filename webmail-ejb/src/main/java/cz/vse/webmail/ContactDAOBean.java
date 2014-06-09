/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Petr
 */
@Stateless
public class ContactDAOBean implements ContactDAO {
    @PersistenceContext(unitName = "webmail")
    private EntityManager entityManager;
    
    @Override
    public List<Contact> getContactsOfUser(User user) {
        return entityManager.createQuery("from Contact c where c.owner = :user order by c.email asc", Contact.class)
                .setParameter("user", user).getResultList();
    }
    
    @Override
    public Contact findContact(User user, String email) {
        return entityManager.createQuery("from Contact c where c.owner = :user and c.email like :email", Contact.class)
                .setParameter("email", email).setParameter("user", user).getSingleResult();
    }
    
    @Override
    public Contact findContact(User user, Integer contactId) {
        return entityManager.createQuery("from Contact c where c.owner = :user and c.id = :id", Contact.class)
                .setParameter("id", contactId).setParameter("user", user).getSingleResult();
    }
}
