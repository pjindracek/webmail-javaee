/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
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
public class ContactDAOTest {
    
    public ContactDAOTest() {
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
     * Test of getContactsOfUser method, of class ContactDAO.
     */
    @org.junit.Test
    public void testGetContactsOfUser() {
        
    }

    /**
     * Test of findContact method, of class ContactDAO.
     */
    @org.junit.Test
    public void testFindContact_User_String() {
       
    }

    /**
     * Test of findContact method, of class ContactDAO.
     */
    @org.junit.Test
    public void testFindContact_User_Integer() {
        
    }

    public class ContactDAOImpl implements ContactDAO {

        public List<Contact> getContactsOfUser(User user) {
            return null;
        }

        public Contact findContact(User user, String email) {
            return null;
        }

        public Contact findContact(User user, Integer contactId) {
            return null;
        }
    }
    
}
