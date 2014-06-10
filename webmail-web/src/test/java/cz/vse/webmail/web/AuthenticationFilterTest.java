/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.domain.User;
import javax.servlet.FilterChain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author Petr
 */
public class AuthenticationFilterTest {
    
    private AuthenticationFilter filter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain chain;
      
    @Before
    public void setUp() {
        filter = new AuthenticationFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        chain = new MockFilterChain();
    }
    
    /**
     * Test of doFilter method, of class AuthenticationFilter.
     */
    @Test
    public void testDoFilterFail() throws Exception {
        filter.doFilter(request, response, chain);
        assertEquals(302, response.getStatus());
    }
    
    @Test
    public void testDoFilterSuccess() throws Exception {
        request.getSession().setAttribute("user", new User());
        filter.doFilter(request, response, chain);
        assertEquals(200, response.getStatus());
    }
}
