/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail;

import cz.vse.webmail.domain.User;
import cz.vse.webmail.utils.DuplicateEmailException;
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
public class UserDAOTest {
    
    public UserDAOTest() {
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
     * Test of addUser method, of class UserDAO.
     */
    @Test
    public void testAddUser() {
//        System.out.println("addUser");
//        User user = null;
//        UserDAO instance = new UserDAOImpl();
//        instance.addUser(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findUser method, of class UserDAO.
     */
    @Test
    public void testFindUser_String() {
//        System.out.println("findUser");
//        String email = "";
//        UserDAO instance = new UserDAOImpl();
//        User expResult = null;
//        User result = instance.findUser(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findUser method, of class UserDAO.
     */
    @Test
    public void testFindUser_String_String() {
//        System.out.println("findUser");
//        String email = "";
//        String password = "";
//        UserDAO instance = new UserDAOImpl();
//        User expResult = null;
//        User result = instance.findUser(email, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    public class UserDAOImpl implements UserDAO {

        public void addUser(User user) throws DuplicateEmailException {
        }

        public User findUser(String email) {
            return null;
        }

        public User findUser(String email, String password) {
            return null;
        }
        
        public void updateUser(User user) {
            
        }
    }
    
}
