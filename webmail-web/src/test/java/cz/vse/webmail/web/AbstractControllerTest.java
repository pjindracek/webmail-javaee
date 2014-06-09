/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletResponse;
/**
 *
 * @author Petr
 */
public class AbstractControllerTest {
    
    private AbstractController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    @Before
    public void setUp() {
        controller = new AbstractControllerImpl();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    
    /**
     * Test of getAction method, of class AbstractController.
     */
    @Test
    public void testGetAction() {
        request.setPathInfo("");
        assertEquals("default", controller.getAction(request));
        request.setPathInfo("/");
        assertEquals("default", controller.getAction(request));
        request.setPathInfo("/emailList");
        assertEquals("emailList", controller.getAction(request));
    }

    /**
     * Test of dispatch method, of class AbstractController.
     */
    @Test
    public void testDispatch() throws Exception {
        request.setPathInfo("/emailList");
        controller.dispatch(request, response, controller.getAction(request));
        assertEquals("/WEB-INF/emailList.jsp", response.getForwardedUrl());
    }

    /**
     * Test of getDefaultAction method, of class AbstractController.
     */
    @Test
    public void testGetDefaultAction() {
        assertEquals("default", controller.getDefaultAction());
    }

    /**
     * Test of getCurrentUser method, of class AbstractController.
     */
    @Test
    public void testGetCurrentUser() {
        User user = new User();
        user.setId(1);
        request.getSession().setAttribute("user", user);
        assertEquals(controller.getCurrentUser(request), user);
    }

    public class AbstractControllerImpl extends AbstractController {

        public String getDefaultAction() {
            return "default";
        }

        public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        }
    }
    
}
