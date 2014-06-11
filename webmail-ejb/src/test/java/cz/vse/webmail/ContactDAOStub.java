package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petr
 */
public class ContactDAOStub implements ContactDAO {

    private List<Contact> contacts;
    
    public ContactDAOStub(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    @Override
    public List<Contact> getContactsOfUser(User user) {
        List<Contact> result = new ArrayList<Contact>();
        for (Contact c : contacts) {
            if (c.getOwner().equals(user)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public Contact findContact(User user, Integer contactId) {
        List<Contact> results = new ArrayList<Contact>();
        for (Contact c : getContactsOfUser(user)) {
            if (contactId.equals(c.getId())) {
                results.add(c);
            }
        }
        return results.size() == 1 ? results.get(0) : null;
    }
    
}
