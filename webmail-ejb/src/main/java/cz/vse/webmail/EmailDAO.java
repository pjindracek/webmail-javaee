/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.EmailListFilter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Petr
 */
@Local
public interface EmailDAO {
    public List<Email> getEmailsOfUser(User user);
    
    public void saveEmail(Email email);
    
    public Email getEmailOfUser(Integer emailId, User user);
    
    public List<Email> getFilteredEmails(User user, EmailListFilter filter);
}
