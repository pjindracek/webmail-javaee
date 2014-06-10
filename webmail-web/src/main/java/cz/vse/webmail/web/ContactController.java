/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.ContactDAO;
import cz.vse.webmail.ContactGateway;
import cz.vse.webmail.domain.Contact;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Petr
 */
@EJBs({@EJB(beanName = "ContactGateway",
                name = "ejb/ContactGateway",
                beanInterface = ContactGateway.class)
})
public class ContactController extends AbstractController {
    public static final String ACTION_DEFAULT = "contactList";
    public static final String ACTION_CREATE = "createContact";
    public static final String ACTION_PUT = "putContact";
    public static final String ACTION_UPDATE = "updateContact";
    public static final String ACTION_POST = "postContact";
    public static final String ACTION_DETAIL = "contactDetail";
    public static final String ACTION_DELETE = "deleteContact";
    
    private static final String SESSION_CONTACT_PAGE_BEAN = "contactPageBean";
    public static final String CONTROLLER_PATH = "/auth/contact/";
    
    @EJB(beanName="ContactDAOBean")
    private ContactDAO contactDAO;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        if (ACTION_DEFAULT.equals(action)) {
            request.setAttribute("contacts", getContactDAO().getContactsOfUser(getCurrentUser(request)));
            dispatch(request, response, action);
        } else if (ACTION_CREATE.equals(action)) {
            dispatch(request, response, action);
        } else if (ACTION_PUT.equals(action)) {
            processPutOrPost(request, response, assembleContact(request));
        } else if (ACTION_UPDATE.equals(action)) {
            request.setAttribute("contact", getContactPageBean(request).getContactGateway().findContact(getContactId(request)));
            dispatch(request, response, action);
        } else if (ACTION_POST.equals(action)) {
            Contact contact = assembleContact(request, getContactPageBean(request).getContactGateway().findContact(getContactId(request)));
            processPutOrPost(request, response, contact);
        } else if (ACTION_DELETE.equals(action)) {
            getContactPageBean(request).getContactGateway().deleteContact(getContactId(request));
            response.sendRedirect(ACTION_DEFAULT);
        } else if (ACTION_DETAIL.equals(action)) {
            request.setAttribute("contact", getContactPageBean(request).getContactGateway().findContact(getContactId(request)));
            dispatch(request, response, ACTION_DETAIL);
        }
    }
    
    @Override
    public String getDefaultAction() {
        return ACTION_DEFAULT;
    }
    
    private Contact assembleContact(HttpServletRequest request, Contact contact) {
        contact.setEmail(request.getParameter("email"));
        contact.setName(request.getParameter("name"));
        contact.setSurname(request.getParameter("surname"));
        contact.setOwner(getCurrentUser(request));
        return contact;
    }
    
    private Contact assembleContact(HttpServletRequest request) {
        return assembleContact(request, new Contact());
    }
    
    private boolean isValid(Contact contact) {
        return StringUtils.isNoneBlank(contact.getEmail()) 
                && StringUtils.isNoneBlank(contact.getName())
                && StringUtils.isNoneBlank(contact.getSurname());
    }
    
    private void destroyContactPageBean(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_CONTACT_PAGE_BEAN);
    }
    
    private ContactPageBean getContactPageBean(HttpServletRequest request) {
        ContactPageBean contactPageBean = (ContactPageBean) request.getSession().getAttribute(SESSION_CONTACT_PAGE_BEAN);
        if (contactPageBean == null) {
            contactPageBean = initializeContactPageBean(request);
        }
        return contactPageBean;
    }
    
    protected ContactGateway retrieveContactGateway() {
        try {
            Context jndi = new InitialContext();
            return (ContactGateway) jndi.lookup("java:comp/env/ejb/ContactGateway");
        } catch (NamingException ex) {
            throw new RuntimeException("ContactGateway lookup error", ex);
        }
    }
    
    private ContactPageBean initializeContactPageBean(HttpServletRequest request) {
        ContactGateway gw = retrieveContactGateway();
        gw.setUser(getCurrentUser(request));
        ContactPageBean contactPageBean = new ContactPageBean(gw);
        request.getSession().setAttribute(SESSION_CONTACT_PAGE_BEAN, contactPageBean);
        return contactPageBean;
    }
    
    protected Integer getContactId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
    
    public ContactDAO getContactDAO() {
        return contactDAO;
    }
    
    private void processPutOrPost(HttpServletRequest request, HttpServletResponse response, Contact contact) 
            throws IOException, ServletException {
        if (isValid(contact)) {
            getContactPageBean(request).getContactGateway().addOrUpdateContact(contact);
            Integer id = getContactPageBean(request).close();
            destroyContactPageBean(request);
            response.sendRedirect(ACTION_DETAIL + "?id=" + id);
        } else {
            request.setAttribute("contact", contact);
            dispatch(request, response, ACTION_CREATE);
        }
    }
}
