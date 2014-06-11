package cz.vse.webmail;

import cz.vse.webmail.domain.Contact;
import cz.vse.webmail.domain.User;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests of ContactGateway class.
 * @author Petr
 */
public class ContactGatewayTest {
   
    private ContactGateway contactGateway;
    private EntityManager entityManager;
    private List<Contact> contacts;
    private ContactDAO contactDAO;
    private User user1;
    private User user2;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    
    /**
     * Initializing before each test.
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    @Before
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        prepareContacts();
        prepareGateway();
    }
    
    /**
     * Cleans up after each test.
     */
    @After
    public void tearDown() {
        entityManager = null;
        contacts = null;
        contactDAO = null;
        user1 = null;
        user2 = null;
        contact1 = null;
        contact2 = null;
        contact3 = null;
    }

    /**
     * Test of findContact method, of class ContactGateway.
     */
    @Test
    public void testFindContact() throws Exception {
        assertEquals(contact1, contactGateway.findContact(contact1.getId()));
    }

    /**
     * Test of addContact method, of class ContactGateway.
     */
    @Test
    public void testAddOrUpdateContact() throws Exception {
        contactGateway.addOrUpdateContact(contact1);
        assertEquals(contact1, contactGateway.getCurrentContact());
        contactGateway.addOrUpdateContact(contact3);
        assertEquals(contact3, contactGateway.getCurrentContact());
    }
    
    /**
     * Test of getUser, setUser and get current user method, of class ContactGateway.
     */
    @Test
    public void testGetUser() throws Exception {
        contactGateway.setUser(user1);
        assertEquals(user1, contactGateway.getUser());
    }

    /**
     * Test of close method, of class ContactGateway.
     */
    @Test
    public void testClose() throws Exception {
        contactGateway.addOrUpdateContact(contact1);
        assertEquals(contact1.getId(), contactGateway.close());
        assertNull(contactGateway.getCurrentContact());
    }
    
    private void prepareContacts() {
        user1 = new User();
        user2 = new User();
        contact1 = new Contact();
        contact1.setId(1);
        contact1.setOwner(user1);
        contact2 = new Contact();
        contact2.setId(2);
        contact2.setOwner(user1);
        contacts = new ArrayList<Contact>();
        contacts.add(contact1);
        contacts.add(contact2);
        contact3 = new Contact();
    }
    
    private void prepareGateway() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        entityManager = mock(EntityManager.class);
        when(entityManager.merge(contact1)).thenReturn(contact1);
        when(entityManager.merge(contact2)).thenReturn(contact2);
        
        contactDAO = new ContactDAOStub(contacts);
        
        contactGateway = new ContactGateway();
        contactGateway.setUser(user1);
        
        Field f1 = contactGateway.getClass().getDeclaredField("entityManager");
        f1.setAccessible(true);
        f1.set(contactGateway, entityManager);
        
        Field f2 = contactGateway.getClass().getDeclaredField("contactDAO");
        f2.setAccessible(true);
        f2.set(contactGateway, contactDAO);
    }
}
