package cz.vse.webmail.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Contact entity represents contact of particular user.
 * @author Petr
 */
@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    @ManyToOne
    private User owner;

    /**
     * Gets ID of the user.
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID of user.
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * Gets Surname of the user
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
     * Gets email of the user
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email of the user. Has to be unique
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets owning user of this email
     * @return owner/user
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owning user of this email
     * @param owner user
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Hashcode
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Equals
     * @param object
     * @return true if same ID
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * To string
     * @return string representation
     */
    @Override
    public String toString() {
        return "cz.vse.webmail.domain.Contact[ id=" + id + " ]";
    }
    
}
