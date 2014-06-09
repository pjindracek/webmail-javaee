/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.ContactDAO;
import cz.vse.webmail.ContactDAOBean;
import cz.vse.webmail.EmailBean;
import cz.vse.webmail.EmailDAO;
import cz.vse.webmail.EmailDAOBean;
import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.EmailListFilter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author Petr
 */
public class EmailControllerTest {

    private EmailController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    @Before
    public void setUp() {
        controller = new EmailControllerStub();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testProcessRequest_DEFAULT() throws Exception {
        request.setPathInfo("/" + EmailController.ACTION_DEFAULT);
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + EmailController.ACTION_DEFAULT + ".jsp", response.getForwardedUrl());
    }
    
    @Test
    public void testProcessRequest_COMPOSE() throws Exception {
        request.setPathInfo("/" + EmailController.ACTION_COMPOSE);
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + EmailController.ACTION_COMPOSE + ".jsp", response.getForwardedUrl());
    }
    
    @Test
    public void testProcessRequest_DETAIL() throws Exception {
        request.setPathInfo("/" + EmailController.ACTION_DETAIL);
        request.setParameter("id", "1");
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + EmailController.ACTION_DETAIL + ".jsp", response.getForwardedUrl());
    }
    
    @Test
    public void testProcessRequest_SEND() throws Exception {
        request.setPathInfo("/" + EmailController.ACTION_SEND);
        request.setParameter("to", "test");
        request.setParameter("cc", "test");
        request.setParameter("bcc", "test");
        request.setParameter("subject", "test");
        request.setParameter("message", "test");
        controller.doGet(request, response);        
        assertEquals(302, response.getStatus());
    }
    
    @Test
    public void testProcessRequest_FILTER() throws Exception {
        request.setPathInfo("/" + EmailController.ACTION_FILTER);
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + EmailController.ACTION_DEFAULT + ".jsp", response.getForwardedUrl());
    }
 
    public class EmailControllerStub extends EmailController {
        
        private User user;
        private EmailBean emailBean;
        private ContactDAO contactDAO;
        private EmailDAO emailDAO;
        
        public EmailControllerStub() {
            user = new User();
            user.setEmail("test@test.cz");
            Email email1 = new Email();
            email1.setId(1);
            Email email2 = new Email();
            user.getEmails().add(email1);
            user.getEmails().add(email2);
            Contact contact1 = new Contact();
            Contact contact2 = new Contact();
            user.getContacts().add(contact1);
            user.getContacts().add(contact2);
            emailBean = mock(EmailBean.class);
            when(emailBean.getEmailsOfUser()).thenReturn(new ArrayList<Email>(user.getEmails()));
            when(emailBean.getFilteredEmails(new EmailListFilter())).thenReturn(new ArrayList<Email>(user.getEmails()));
            contactDAO = mock(ContactDAOBean.class);
            when(contactDAO.getContactsOfUser(user)).thenReturn(new ArrayList<Contact>(user.getContacts()));
            emailDAO = mock(EmailDAOBean.class);
        }
        
        @Override
        public User getCurrentUser(HttpServletRequest request) {
            return user;
        }
        
        @Override
        protected EmailBean retrieveEmailBean() {
            EmailBean emailBean = mock(EmailBean.class);
            when(emailBean.getEmailsOfUser()).thenReturn(new ArrayList<Email>(user.getEmails()));
            return emailBean;
        }
        
        @Override
        public ContactDAO getContactDAO() {
            return this.contactDAO;
        }
        
        @Override
        public EmailDAO getEmailDAO() {
            return this.emailDAO;
        }
    }
    
}
