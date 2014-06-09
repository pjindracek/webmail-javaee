/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.DuplicateEmailException;
import javax.ejb.Local;

/**
 *
 * @author Petr
 */
@Local
public interface UserDAO {
    
    public void addUser(User user) throws DuplicateEmailException;
    
    public void updateUser(User user);
    
    public User findUser(String email);
    
    public User findUser(String email, String password);
}
