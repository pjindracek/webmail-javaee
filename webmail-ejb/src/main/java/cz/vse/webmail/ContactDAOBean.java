package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation fo ContactDAO
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
    public Contact findContact(User user, Integer contactId) {
        List<Contact> contacts = entityManager.createQuery("from Contact c where c.owner = :user and c.id = :id", Contact.class)
                .setParameter("id", contactId).setParameter("user", user).getResultList();
        return contacts.size() == 1 ? contacts.get(0) : null;
    }
}
