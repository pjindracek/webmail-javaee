package cz.vse.webmail.web;

import cz.vse.webmail.domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Common functionality for all controllers
 * @author Petr
 */
public abstract class AbstractController extends HttpServlet {
    
    /**
     * Extracts name of the action from URL without any slashes. 
     * @param request HTTP request
     * @return action name or default action name if none is present
     */
    public String getAction(HttpServletRequest request) {
        String path = request.getPathInfo(); //path from getPathInfo starts with slash
        return path != null && path.length() > 1 ? path.substring(1).split("/")[0] : getDefaultAction();
    }
    
    /**
     * Forwards the request to the right JSP page with given name similar to action name.
     * @param request HTTP request
     * @param response HTTP response
     * @param action name of the controller's action
     * @throws ServletException
     * @throws IOException 
     */
    public void dispatch(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/" + action + ".jsp").forward(request, response);
    }
    
    /**
     * Default name of the controller's action.
     * @return 
     */
    public abstract String getDefaultAction();
    
    /**
     * Request processing called inside doGet and doPost methods of the cotroller
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException
     * @throws IOException 
     */
    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * Retrieves currently logged user from session
     * @param request HTTP request
     * @return logged user
     */
    public User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
