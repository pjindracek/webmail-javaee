/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.utils.EmailListFilter;
import cz.vse.webmail.EmailBean;
import cz.vse.webmail.domain.Email;
import java.util.List;

/**
 *
 * @author Petr
 */
public class EmailPageBean {
    
    private final EmailBean emailBean;
    private EmailListFilter filter;
    
    public EmailPageBean(EmailBean emailBean) {
        this.emailBean = emailBean;
    }
    
    public Email getEmail() {
        return emailBean.getEmail();
    }
    
    public void setEmail(Email email) {
        emailBean.setEmail(email);
    }
    
    public EmailBean getEmailBean() {
        return emailBean;
    }
    
    public void setFilter(EmailListFilter filter) {
        this.filter = filter;
    }
    
    public List<Email> getFilteredEmails() {
        return emailBean.getFilteredEmails(filter);
    }
    
    public List<Email> getEmailsOfUser() {
        return emailBean.getEmailsOfUser();
    }
    
} 
