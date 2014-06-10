package cz.vse.webmail.web;

import cz.vse.webmail.ContactGateway;
import cz.vse.webmail.domain.Contact;

/**
 * Represents page of the contact. Encapsulates ContactGateway
 * @author Petr
 */
public class ContactPageBean {
    private ContactGateway contactGateway;

    /**
     * Constructor requires instance of contact gateway
     * @param contactGateway contact gateway stateful bean
     */
    public ContactPageBean(ContactGateway contactGateway) {
        this.contactGateway = contactGateway;
    }
    
    /**
     * Gets contact gateway of this bean
     * @return contat gateway
     */
    public ContactGateway getContactGateway() {
        return contactGateway;
    }
    
    /**
     * Returns currently processed contact
     * @return contact
     */
    public Contact getContact() {
        return contactGateway.getCurrentContact();
    }
    
    /**
     * Cleans up the resources
     * @return ID of the currently processed contact in the bean
     */
    public Integer close() {
        return contactGateway.close();
    }
}
