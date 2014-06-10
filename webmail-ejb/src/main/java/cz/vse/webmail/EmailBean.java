package cz.vse.webmail;

import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.EmailListFilter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Operations over Email entity connected with the given User.
 * @author Petr
 */
@Stateful
@LocalBean
public class EmailBean {

    @EJB(beanName="EmailDAOBean")
    private EmailDAO emailDAO;
    @EJB
    private MailSessionFactoryBean mailSessionFactoryBean;
    private Email email;
    private User user;

    /**
     * Sends email which was previously added to this bean.
     */
    public void sendEmail() {
        try {
            Message message = new MimeMessage(mailSessionFactoryBean.getMailSession());
            message.setFrom(new InternetAddress(email.getFrom(), email.getOwner().getFullName()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo(), false));
            if (StringUtils.isNotBlank(email.getCc())) {
                message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(email.getCc(), false));
            }
            if (StringUtils.isNotBlank(email.getBcc())) {
                message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(email.getBcc(), false));
            }
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());
            message.setHeader("X-Mailer", "My Mailer");
            message.setSentDate(email.getCreatedAt());
            Transport.send(message);
        } catch (UnsupportedEncodingException exception) {
            throw new RuntimeException("Invalid email address of user", exception);
        } catch (MessagingException exception) {
            throw new RuntimeException("Error during sending email", exception);
        }      
    }
    
    /**
     * Returns found emails of set user based on the settings in the filter
     * @param filter given email filter
     * @return found emails
     */
    public List<Email> getFilteredEmails(EmailListFilter filter) {
        return filter != null ? emailDAO.getFilteredEmails(user, filter) : null;
    }
    
    /**
     * Gets all emails of current user
     * @return emails of user
     */
    public List<Email> getEmailsOfUser() {
        return emailDAO.getEmailsOfUser(user);
    }

    /**
     * Returns User of this bean
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user to this bean.
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Returns email set to this group
     * @return 
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets email to this bean
     * @param email 
     */
    public void setEmail(Email email) {
        this.email = email;
    }
    
    /**
     * Cleans up resources before removal of this bean
     */
    @Remove
    public void close() {
        emailDAO.saveEmail(email);
        email = null;
        user = null;
    }
    
}
