/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.ContactGateway;
import cz.vse.webmail.domain.Contact;

/**
 *
 * @author Petr
 */
public class ContactPageBean {
    private ContactGateway contactGateway;

    public ContactPageBean(ContactGateway contactGateway) {
        this.contactGateway = contactGateway;
    }
    
    public ContactGateway getContactGateway() {
        return contactGateway;
    }
    
    public Contact getContact() {
        return contactGateway.getCurrentContact();
    }
    
    public Integer close() {
        return contactGateway.close();
    }
}
