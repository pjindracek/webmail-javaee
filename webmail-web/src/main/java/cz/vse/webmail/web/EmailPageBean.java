package cz.vse.webmail.web;

import cz.vse.webmail.utils.EmailListFilter;
import cz.vse.webmail.EmailBean;
import cz.vse.webmail.domain.Email;
import java.util.List;

/**
 * Bean representing page of the email and email list with filter.
 * @author Petr
 */
public class EmailPageBean {
    
    private final EmailBean emailBean;
    private EmailListFilter filter;
    
    /**
     * Constructor requeires email bean from EJB container
     * @param emailBean email bean
     */
    public EmailPageBean(EmailBean emailBean) {
        this.emailBean = emailBean;
    }
    
    /**
     * Returns email of the current session
     * @return email object
     */
    public Email getEmail() {
        return emailBean.getEmail();
    }
    
    /**
     * Sets the current email object
     * @param email 
     */
    public void setEmail(Email email) {
        emailBean.setEmail(email);
    }
    
    /**
     * Gets the email bean in this bean
     * @return email bean
     */
    public EmailBean getEmailBean() {
        return emailBean;
    }
    
    /**
     * Sets the instance of the configured filter for searching emails
     * @param filter email filter
     */
    public void setFilter(EmailListFilter filter) {
        this.filter = filter;
    }
    
    /**
     * Returns emails search based on the set filter
     * @return 
     */
    public List<Email> getFilteredEmails() {
        return emailBean.getFilteredEmails(filter);
    }
    
    /**
     * Gets all emails of the current user
     * @return email of user
     */
    public List<Email> getEmailsOfUser() {
        return emailBean.getEmailsOfUser();
    }
    
} 
