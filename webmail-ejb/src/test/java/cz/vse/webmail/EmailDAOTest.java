/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Email;
import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.EmailListFilter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Petr
 */
public class EmailDAOTest {
    
    public EmailDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEmailsOfUser method, of class EmailDAO.
     */
    @org.junit.Test
    public void testGetEmailsOfUser() {
//        System.out.println("getEmailsOfUser");
//        User user = null;
//        EmailDAO instance = new EmailDAOImpl();
//        List<Email> expResult = null;
//        List<Email> result = instance.getEmailsOfUser(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of saveEmail method, of class EmailDAO.
     */
    @org.junit.Test
    public void testSaveEmail() {
//        System.out.println("saveEmail");
//        Email email = null;
//        EmailDAO instance = new EmailDAOImpl();
//        instance.saveEmail(email);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailOfUser method, of class EmailDAO.
     */
    @org.junit.Test
    public void testGetEmailOfUser() {
//        System.out.println("getEmailOfUser");
//        Integer emailId = null;
//        User user = null;
//        EmailDAO instance = new EmailDAOImpl();
//        Email expResult = null;
//        Email result = instance.getEmailOfUser(emailId, user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilteredEmails method, of class EmailDAO.
     */
    @org.junit.Test
    public void testGetFilteredEmails() {
//        System.out.println("getFilteredEmails");
//        User user = null;
//        EmailListFilter filter = null;
//        EmailDAO instance = new EmailDAOImpl();
//        List<Email> expResult = null;
//        List<Email> result = instance.getFilteredEmails(user, filter);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    public class EmailDAOImpl implements EmailDAO {

        public List<Email> getEmailsOfUser(User user) {
            return null;
        }

        public void saveEmail(Email email) {
        }

        public Email getEmailOfUser(Integer emailId, User user) {
            return null;
        }

        public List<Email> getFilteredEmails(User user, EmailListFilter filter) {
            return null;
        }
    }
    
}
