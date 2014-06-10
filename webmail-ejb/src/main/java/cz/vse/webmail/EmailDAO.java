package cz.vse.webmail;

import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.EmailListFilter;
import java.util.List;
import javax.ejb.Local;

/**
 * Database operations over Email entity
 * @author Petr
 */
@Local
public interface EmailDAO {
    
    /**
     * Gets all emails of given user.
     * @param user user
     * @return all emails 
     */
    public List<Email> getEmailsOfUser(User user);
    
    /**
     * Saves given email.
     * @param email email
     */
    public void saveEmail(Email email);
    
    /**
     * Returns email of the given user with given ID
     * @param emailId email ID
     * @param user user
     * @return found email
     */
    public Email getEmailOfUser(Integer emailId, User user);
    
    /**
     * Returns emails of user acording to the given filter
     * @param user user
     * @param filter email filter
     * @return found emails
     */
    public List<Email> getFilteredEmails(User user, EmailListFilter filter);
}
