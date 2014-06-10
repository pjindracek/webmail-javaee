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
 * Makes easier access to Contact entity
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
    
    /**
     * Finds contact with given ID
     * @param contactId contact ID
     * @return found contact
     */
    public Contact findContact(Integer contactId) {
        return contactDAO.findContact(user, contactId);
    }
    
    /**
     * Adds new Contact or updates exisiting one
     * @param contact contact
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addOrUpdateContact(Contact contact) {
        if (contact.getId() == null) {
            entityManager.persist(contact);
            this.contact = contact;
        } else {
            this.contact = entityManager.merge(contact);
        }
    }
    
    /**
     * Returns contact which was previously added or updated
     * @return current contact
     */
    public Contact getCurrentContact() {
        return contact;
    }

    /**
     * Deletes contact with given ID
     * @param contactId contact ID
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteContact(Integer contactId) {
        entityManager.remove(findContact(contactId));
    }
    
    /**
     * Gets user who was previously added to this bean
     * @return user of this bean
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user of this bean
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Cleans up the resources and returns ID of the used contact
     * @return 
     */
    @Remove
    public Integer close() {
        entityManager.flush();
        Integer id = contact.getId();
        contact = null;
        return id;
    }
    
}
