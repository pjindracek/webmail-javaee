package cz.vse.webmail.web;

import cz.vse.webmail.ContactDAO;
import cz.vse.webmail.ContactDAOBean;
import cz.vse.webmail.ContactGateway;
import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Tests of the ContactController
 * @author Petr
 */
public class ContactControllerTest {
    
    private ContactController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private User user;
    private ContactDAO contactDAO;
    private ContactGateway contactGateway;
    
    /**
     * Initializing helper objects
     */
    @Before
    public void setUp() {
        controller = new ContactControllerStub();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    /**
     * Test of the DEFAULT action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_DEFAULT() throws Exception {
        request.setPathInfo("/" + ContactController.ACTION_DEFAULT);
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + ContactController.ACTION_DEFAULT + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the CREATE action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_CREATE() throws Exception {
        request.setPathInfo("/" + ContactController.ACTION_CREATE);
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + ContactController.ACTION_CREATE + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the PUT action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_PUT() throws Exception {
        request.setPathInfo("/" + ContactController.ACTION_PUT);
        request.setParameter("email", "test");
        request.setParameter("name", "test");
        request.setParameter("surname", "test");
        controller.doPost(request, response);        
        assertEquals(302, response.getStatus());
    }
    
    /**
     * Test of the UPDATE action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_UPDATE() throws Exception {
        request.setPathInfo("/" + ContactController.ACTION_UPDATE);
        request.setParameter("id", "1");
        controller.doPost(request, response);
        assertEquals("/WEB-INF/" + ContactController.ACTION_UPDATE + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the POST action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_POST() throws Exception {
        request.setPathInfo("/" + ContactController.ACTION_POST);
        request.setParameter("id", "1");
        request.setParameter("email", "test");
        request.setParameter("name", "test");
        request.setParameter("surname", "test");
        controller.doPost(request, response);        
        assertEquals(302, response.getStatus());
    }

    /**
     * Stub object of the ContactController class. It is necessary to mock certain
     * dependencies inside the class
     */
    public class ContactControllerStub extends ContactController {
    
        public ContactControllerStub() {
            user = new User();
            user.setEmail("test@test.cz");
            Email email1 = new Email();
            email1.setId(1);
            Email email2 = new Email();
            user.getEmails().add(email1);
            user.getEmails().add(email2);
            Contact contact1 = new Contact();
            contact1.setId(1);
            Contact contact2 = new Contact();
            user.getContacts().add(contact1);
            user.getContacts().add(contact2);
            contactDAO = mock(ContactDAOBean.class);
            when(contactDAO.getContactsOfUser(user)).thenReturn(new ArrayList<Contact>(user.getContacts()));
            contactGateway = mock(ContactGateway.class);
            when(contactGateway.findContact(1)).thenReturn(contact1);
        }
        
        @Override
        public User getCurrentUser(HttpServletRequest request) {
            return user;
        }
        
        @Override
        public ContactDAO getContactDAO() {
            return contactDAO;
        }
        
        @Override
        public ContactGateway retrieveContactGateway() {
            return contactGateway;
        }
    }
    
}
