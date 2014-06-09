/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.DuplicateEmailException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Petr
 */
@Stateless
public class UserDAOBean implements UserDAO {
    
    @PersistenceContext(unitName = "webmail")
    private EntityManager entityManager;
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addUser(User user) throws DuplicateEmailException {
        if (findUser(user.getEmail()) != null) {
            throw new DuplicateEmailException("User with this email already exists: " + user.getEmail());
        }
        entityManager.persist(user);
    }
    
    @Override
    public User findUser(String email) {
        List<User> result = entityManager.createQuery("from User u where u.email like :email", User.class)
                .setParameter("email", email).getResultList();
        return result.size() == 1 ? result.get(0) : null;
    }
    
    @Override
    public User findUser(String email, String password) {
        List<User> result = entityManager.createQuery("from User u where u.email like :email and u.password like :password", User.class)
                .setParameter("email", email).setParameter("password", password).getResultList();
        return result.size() == 1 ? result.get(0) : null;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
