package cz.vse.webmail.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter which solely purpose is to redirect not authenticated user to login page
 * @author Petr
 */
public class AuthenticationFilter implements Filter {

    /**
     * Initilization method
     * @param filterConfig filter configuration
     * @throws ServletException 
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Methods filtering request and response
     * @param request HTTP request
     * @param response HTTP response
     * @param chain given chain of the filters
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (!isUserAuthenticated(httpRequest)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + UserController.CONTROLLER_PATH);
        } else {
            chain.doFilter(request, response);
        }

    }
    
    private boolean isUserAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    /**
     * Method clean ups the filter after filtering.
     */
    @Override
    public void destroy() {
    }
    
}
