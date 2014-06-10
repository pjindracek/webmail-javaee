package cz.vse.webmail.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents user of the application
 * @author Petr
 */
@Entity
@Table(name = "user_webmail")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique=true)
    private String email;
    private String name;
    private String surname;
    @OneToMany(orphanRemoval = true, mappedBy="owner")
    private Set<Email> emails = new HashSet<Email>();
    @OneToMany(orphanRemoval = true, mappedBy="owner")
    private Set<Contact> contacts = new HashSet<Contact>();
    private String password;

    /**
     * Gets ID of the user.
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID of the user
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns email address of the user
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email address of the user
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets name of the user
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the user
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname of the user
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname of the user
     * @param surname surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets emails of the user
     * @return user's emails
     */
    public Set<Email> getEmails() {
        return emails;
    }

    /**
     * Sets emails of the user
     * @param emails emails
     */
    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    /**
     * Gets contacts of the user
     * @return contacts
     */
    public Set<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets contacts of the user
     * @param contacts contacts
     */
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    
    /**
     * Gets full name consistin of name and surname
     * @return name and surname
     */
    public String getFullName() {
        return name + " " + surname;
    }

    /**
     * Gets password of user
     * @return password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets password of user
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * Equals
     * @param object other object
     * @return true if IDs are the same 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * String representation of the object
     * @return string representation
     */
    @Override
    public String toString() {
        return "cz.vse.webmail.domain.User[ id=" + id + " ]";
    }
    
}
