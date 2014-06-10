package cz.vse.webmail.web;

import cz.vse.webmail.ContactDAO;
import cz.vse.webmail.EmailBean;
import cz.vse.webmail.EmailDAO;
import cz.vse.webmail.domain.Email;
import cz.vse.webmail.utils.EmailListFilter;
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
 * Controller processing requests considering Email entity
 * @author Petr
 */
@EJBs({
        @EJB(beanName = "EmailBean",
                name = "ejb/EmailBean",
                beanInterface = EmailBean.class)
})
public class EmailController extends AbstractController {
    public static final String ACTION_DEFAULT = "emailList";
    public static final String ACTION_COMPOSE = "composeEmail";
    public static final String ACTION_SEND = "send";
    public static final String ACTION_DETAIL = "emailDetail";
    public static final String ACTION_FILTER = "filter";
    public static final String CONTROLLER_PATH = "/auth/email/";
    
    private static final String SESSION_EMAIL_PAGE_BEAN = "emailPageBean";
    
    @EJB(beanName="EmailDAOBean")
    private EmailDAO emailDAO;
    @EJB(beanName="ContactDAOBean")
    private ContactDAO contactDAO;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        if (ACTION_DEFAULT.equals(action)) {
            EmailPageBean emailPageBean = getEmailPageBean(request);
            request.setAttribute("user", getCurrentUser(request));
            request.setAttribute("emails", emailPageBean.getEmailsOfUser());
            request.setAttribute("contacts", getContactDAO().getContactsOfUser(getCurrentUser(request)));
            dispatch(request, response, action);
        } else if (ACTION_COMPOSE.equals(action)) {
            request.setAttribute("contacts", getContactDAO().getContactsOfUser(getCurrentUser(request)));
            dispatch(request, response, action);
        } else if (ACTION_SEND.equals(action)) { 
            Email email = assembleEmail(request);
            if (isValid(email)) {
                EmailBean emailBean = getEmailBean(request);
                emailBean.setEmail(email);
                emailBean.sendEmail();
                emailBean.close();
                destroyEmailPageBean(request);
                response.sendRedirect(ACTION_DEFAULT);
            } else {
                request.setAttribute("error", "To, subject and message cannot be blank.");
                request.setAttribute("contacts", contactDAO.getContactsOfUser(getCurrentUser(request)));
                dispatch(request, response, ACTION_COMPOSE);
            }
        } else if (ACTION_DETAIL.equals(action)) {
            request.setAttribute("email", getEmailDAO().getEmailOfUser(getEmailId(request), getCurrentUser(request)));
            dispatch(request, response, action);
        } else if (ACTION_FILTER.equals(action)) {
            EmailPageBean emailPageBean = getEmailPageBean(request);
            emailPageBean.setFilter(createFilter(request));
            request.setAttribute("emails", emailPageBean.getFilteredEmails());
            request.setAttribute("contacts", getContactDAO().getContactsOfUser(getCurrentUser(request)));
            dispatch(request, response, ACTION_DEFAULT);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultAction() {
        return ACTION_DEFAULT;
    }
    
    private boolean isValid(Email email) {
        return StringUtils.isNoneBlank(email.getTo()) 
                && StringUtils.isNoneBlank(email.getSubject())
                && StringUtils.isNoneBlank(email.getMessage());
    }
    
    private EmailListFilter createFilter(HttpServletRequest request) {
        EmailListFilter filter = new EmailListFilter();
        String to = request.getParameter("to");
        if (StringUtils.isNoneBlank(to)) {
            filter.setTo(to);
        }
        return filter;
    }
    
    private Email assembleEmail(HttpServletRequest request) {
        Email email = new Email();
        email.setOwner(getCurrentUser(request));
        email.setFrom(getCurrentUser(request).getEmail());
        email.setTo(request.getParameter("to"));
        email.setCc(request.getParameter("cc"));
        email.setBcc(request.getParameter("bcc"));
        email.setSubject(request.getParameter("subject"));
        email.setMessage(request.getParameter("message"));
        return email;
    }
    
    private Integer getEmailId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
    
    private void destroyEmailPageBean(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_EMAIL_PAGE_BEAN);
    }
    
    /**
     * Creates EmailPageBean and puts it in session or retrieves one from the session
     * @param request HTTP request
     * @return initialized email page bean
     */
    protected EmailPageBean getEmailPageBean(HttpServletRequest request) {
        EmailPageBean emailPageBean = (EmailPageBean) request.getAttribute(SESSION_EMAIL_PAGE_BEAN);
        if (emailPageBean == null) {
            emailPageBean = initializeEmailPageBean(request);
        }
        return emailPageBean;
    }
    
    /**
     * Shortcut for getting email bean from email page bean
     * @param request
     * @return 
     */
    protected EmailBean getEmailBean(HttpServletRequest request) {
        return getEmailPageBean(request).getEmailBean();
    }
    
    /**
     * Retrieves email bean from EJB container
     * @return email bean
     */
    protected EmailBean retrieveEmailBean() {
        try {
            Context jndi = new InitialContext();
            return (EmailBean) jndi.lookup("java:comp/env/ejb/EmailBean");
        } catch (NamingException ex) {
            throw new RuntimeException("EmailBean lookup error", ex);
        }
    }
    
    private EmailPageBean initializeEmailPageBean(HttpServletRequest request) {
        EmailBean emailBean = retrieveEmailBean();
        emailBean.setUser(getCurrentUser(request));
        EmailPageBean emailPageBean = new EmailPageBean(emailBean);
        request.getSession().setAttribute(SESSION_EMAIL_PAGE_BEAN, emailPageBean);
        return emailPageBean;
    }
    
    /**
     * Returns contact DAO bean. Important method due to testing reasons
     * @return 
     */
    protected ContactDAO getContactDAO() {
        return contactDAO;
    }
    
    /**
     * Returns email DAO bean. Important for tests
     * @return 
     */
    protected EmailDAO getEmailDAO() {
        return emailDAO;
    }
    
}
