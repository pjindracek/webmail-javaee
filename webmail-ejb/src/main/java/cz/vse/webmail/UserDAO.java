package cz.vse.webmail;

import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.DuplicateEmailException;
import javax.ejb.Local;

/**
 * Database operations over User entity.
 * @author Petr
 */
@Local
public interface UserDAO {
    
    /**
     * Adds or updates given user in the DB. 
     * Exception when given email of user already exists in the DB.
     * @param user user
     * @throws DuplicateEmailException 
     */
    public void addOrUpdateUser(User user) throws DuplicateEmailException;
    
    /**
     * Finds user based on his email
     * @param email email of user
     * @return found user
     */
    public User findUser(String email);
    
    /**
     * Finds user based on his credentials
     * @param email email 
     * @param password password
     * @return Found user or null if not found
     */
    public User findUser(String email, String password);
}
