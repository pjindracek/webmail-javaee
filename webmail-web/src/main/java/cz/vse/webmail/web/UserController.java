package cz.vse.webmail.web;

import cz.vse.webmail.UserDAO;
import cz.vse.webmail.domain.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Controller managing requests considering user
 * @author Petr
 */
public class UserController extends AbstractController {
    
    public static final String ACTION_DEFAULT = "login";
    public static final String ACTION_SEND = "sendLogin";
    public static final String ACTION_LOGOUT = "logout";
    public static final String ACTION_SIGNUP = "signup";
    public static final String ACTION_PUT = "putUser";
    public static final String ACTION_UPDATE = "updateUser";
    public static final String ACTION_POST = "postUser";
    
    public static final String CONTROLLER_PATH = "/user/";
    
    @EJB(beanName = "UserDAOBean")
    private UserDAO userDAO;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultAction() {
        return ACTION_DEFAULT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        if (ACTION_DEFAULT.equals(action)) {
            dispatch(request, response, action);
        } else if (ACTION_SEND.equals(action)) {
            loginUser(request, response);
        } else if (ACTION_LOGOUT.equals(action)) {
            logoutUser(request);
            response.sendRedirect(request.getContextPath() + UserController.CONTROLLER_PATH + ACTION_DEFAULT);
        } else if (ACTION_SIGNUP.equals(action)) {
            dispatch(request, response, ACTION_SIGNUP);
        } else if (ACTION_PUT.equals(action)) {
            processPutOrPost(request, response, assembleUser(request), ACTION_SIGNUP);
        } else if (ACTION_UPDATE.equals(action)) {
            request.setAttribute("user", getCurrentUser(request));
            dispatch(request, response, action);
        } else if (ACTION_POST.equals(action)) {
            processPutOrPost(request, response, assembleUser(request, getCurrentUser(request)), ACTION_UPDATE);
        }
    }
    
    private void processPutOrPost(HttpServletRequest request, HttpServletResponse response, User user, String failureAction) throws IOException, ServletException {
        request.setAttribute("user", user);
        if (isValid(user)) {
            try {
                getUserDAO().addOrUpdateUser(user);
                refreshUserInSession(request, user);
                response.sendRedirect(request.getContextPath() + EmailController.CONTROLLER_PATH + EmailController.ACTION_DEFAULT);
            } catch (Exception e) {
                request.setAttribute("error", "Such email already exists.");
                dispatch(request, response, failureAction);
            }
        } else {
            request.setAttribute("error", "All fields have to be filled in");
            dispatch(request, response, failureAction);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(processUserAuthentication(request)) {
            response.sendRedirect(request.getContextPath() + EmailController.CONTROLLER_PATH + EmailController.ACTION_DEFAULT);
        } else {
            request.setAttribute("error", "Access denied!");
            dispatch(request, response, ACTION_DEFAULT);
        }
    }
    
    private User assembleUser(HttpServletRequest request) {
        return assembleUser(request, new User());
    }
    
    private User assembleUser(HttpServletRequest request, User user) {
        user.setEmail(request.getParameter("email"));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        if (StringUtils.isNotBlank(request.getParameter("password"))
                && request.getParameter("password").equals(request.getParameter("passwordconf"))) {
            user.setPassword(request.getParameter("password"));
        }
        return user;
    }
    
    private boolean processUserAuthentication(HttpServletRequest request) {
        User user = getUserDAO().findUser(request.getParameter("email"), request.getParameter("password"));
        if (user != null) {
            request.getSession().setAttribute("user", user);
        }
        return user != null;
    }
    
    private void logoutUser(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }
    
    private void refreshUserInSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }
    
    private boolean isValid(User user) {
        return StringUtils.isNotBlank(user.getEmail()) 
                && StringUtils.isNotBlank(user.getName())
                && StringUtils.isNotBlank(user.getSurname())
                && StringUtils.isNotBlank(user.getPassword());
    }
    
    /**
     * Return user DAO bean
     * @return user DAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
