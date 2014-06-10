package cz.vse.webmail.web;

import cz.vse.webmail.UserDAO;
import cz.vse.webmail.UserDAOBean;
import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Tests of the UserController
 * @author Petr
 */
public class UserControllerTest {
    
    private UserController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    private User user;
    private UserDAO userDAO;
    
    /**
     * Initializes the helper objects
     */
    @Before
    public void setUp() {
        controller = new UserControllerStub();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    /**
     * Test of the DEFAULT action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_DEFAULT() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_DEFAULT);
        controller.doGet(request, response);
        assertEquals("/WEB-INF/" + UserController.ACTION_DEFAULT + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the SEND action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_SEND() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_SEND);
        request.setParameter("email", "test@test.cz");
        request.setParameter("password", "password");
        controller.doPost(request, response);        
        assertEquals(302, response.getStatus());
        assertFalse(request.getSession().getAttribute("user") == null);
    }
    
    /**
     * Test of the LOGOUT action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_LOGOUT() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_LOGOUT);
        controller.doPost(request, response);
        assertTrue(request.getSession().getAttribute("user") == null);
        assertEquals(302, response.getStatus());
    }
    
    /**
     * Test of the SIGNUP action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_SIGNUP() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_SIGNUP);
        controller.doPost(request, response);
        assertEquals("/WEB-INF/" + UserController.ACTION_SIGNUP + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the PUT action with valid data
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_PUT_success() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_PUT);
        request.setParameter("email", "test");
        request.setParameter("name", "test");
        request.setParameter("surname", "test");
        request.setParameter("password", "test");
        request.setParameter("passwordconf", "test");
        controller.doPost(request, response);        
        assertEquals(302, response.getStatus());
    }
    
    /**
     * Test of the PUT action with invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_PUT_fail() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_PUT);
        request.setParameter("email", "test");
        request.setParameter("name", "test");
        request.setParameter("surname", "test");
        request.setParameter("password", "test");
        request.setParameter("passwordconf", "");
        controller.doPost(request, response);        
        assertEquals("/WEB-INF/" + UserController.ACTION_SIGNUP + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the UPDATE action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_UPDATE() throws Exception {
        request.setPathInfo("/" + UserController.ACTION_UPDATE);
        controller.doPost(request, response);
        assertEquals("/WEB-INF/" + UserController.ACTION_UPDATE + ".jsp", response.getForwardedUrl());
    }
    
    /**
     * Test of the POST action
     * @throws Exception 
     */
    @Test
    public void testProcessRequest_POST() throws Exception {
        request.getSession().setAttribute("user", user);
        request.setPathInfo("/" + UserController.ACTION_POST);
        request.setParameter("email", "test");
        request.setParameter("name", "newtest");
        request.setParameter("surname", "newtest");
        request.setParameter("password", "test");
        request.setParameter("passwordconf", "test");
        controller.doPost(request, response);        
        User userResult = (User) request.getSession().getAttribute("user");
        assertEquals("newtest", userResult.getName());
        assertEquals(302, response.getStatus());
    }
 
     /**
     * Stub object of the UserController class. It is necessary to mock certain
     * dependencies inside the class
     */
    public class UserControllerStub extends UserController {
        
        public UserControllerStub() {
            user = new User();
            user.setEmail("test@test.cz");
            user.setPassword("password");
            Email email1 = new Email();
            email1.setId(1);
            Email email2 = new Email();
            user.getEmails().add(email1);
            user.getEmails().add(email2);
            Contact contact1 = new Contact();
            Contact contact2 = new Contact();
            user.getContacts().add(contact1);
            user.getContacts().add(contact2);
            userDAO = mock(UserDAOBean.class);
            when(userDAO.findUser(user.getEmail(), user.getPassword())).thenReturn(user);
        }
        
        @Override
        public User getCurrentUser(HttpServletRequest request) {
            return user;
        }
        
        @Override
        public UserDAO getUserDAO() {
            return userDAO;
        }
    }
}
