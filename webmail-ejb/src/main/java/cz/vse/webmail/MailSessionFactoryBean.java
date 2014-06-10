package cz.vse.webmail;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Bean which is focusing on creating Mail Session based on the configuration in ejb-jar.xml
 * @author Petr
 */
@Stateless
@LocalBean
public class MailSessionFactoryBean {
    
    private Session session;
    
    @Resource(name = "username")
    private String username;
    @Resource(name = "password")
    private String password;
    @Resource(name = "host")
    private String host;
    @Resource(name = "socketFactoryPort")
    private String socketFactoryPort;
    @Resource(name = "socketFactoryClass")
    private String socketFactoryClass;
    @Resource(name = "auth")
    private String auth;
    @Resource(name = "port")
    private String port;
    
    @PostConstruct
    private void init() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.socketFactory.port", socketFactoryPort);
        properties.put("mail.smtp.socketFactory.class", socketFactoryClass);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.port", port);
        session = Session.getDefaultInstance(properties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
            });
    }
    
    /**
     * Cleans resource before destroying this bean
     */
    @PreDestroy
    public void close() {
        session = null;
    }

    /** 
     * Returns ready to use Mail Session
     * @return mail session
     */
    public Session getMailSession() {
        return session;
    }
}
