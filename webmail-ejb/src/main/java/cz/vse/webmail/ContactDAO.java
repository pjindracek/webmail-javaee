package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import java.util.List;
import javax.ejb.Local;

/**
 * DAO for operations with Contact entity.
 * @author Petr
 */
@Local
public interface ContactDAO {
    
    /**
     * Get all contacts of particular user
     * @param user user
     * @return all contatcs of user
     */
    public List<Contact> getContactsOfUser(User user);
    
    /**
     * Find contact of user with given ID
     * @param user user
     * @param contactId id of searched contact
     * @return found contact
     */
    public Contact findContact(User user, Integer contactId);
}
