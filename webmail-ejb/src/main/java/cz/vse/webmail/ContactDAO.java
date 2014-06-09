/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Petr
 */
@Local
public interface ContactDAO {
    
    public List<Contact> getContactsOfUser(User user);
    
    public Contact findContact(User user, String email);
    
    public Contact findContact(User user, Integer contactId);
}
