/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Petr
 */
@Stateful
@LocalBean
public class ContactGateway {
    
    @PersistenceContext(unitName = "webmail")
    private EntityManager entityManager;
    @EJB(beanName = "ContactDAOBean")
    private ContactDAO contactDAO;
    private User user;
    private Contact contact;
    
    public Contact findContact(String email) {
        return contactDAO.findContact(user, email);
    }
    
    public Contact findContact(Integer contactId) {
        return contactDAO.findContact(user, contactId);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addOrUpdateContact(Contact contact) {
        if (contact.getId() != null) {
            entityManager.persist(contact);
            this.contact = contact;
        } else {
            this.contact = entityManager.merge(contact);
        }
    }
    
    public Contact getCurrentContact() {
        return contact;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteContact(Integer contactId) {
        entityManager.remove(findContact(contactId));
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Remove
    public Integer close() {
        entityManager.flush();
        Integer id = contact.getId();
        contact = null;
        return id;
    }
    
}
