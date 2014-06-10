package cz.vse.webmail.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents email of the user.
 * @author Petr
 */
@Entity
public class Email implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "received_from")
    private String from;
    @Column(name = "sent_to")
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    @ManyToOne
    private User owner;
        
    /**
     * Gets ID of email
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID of email
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets email of sender
     * @return email
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets email of sender
     * @param from email
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets receiver of email
     * @return email
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets receiver of email
     * @param to email
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets subject of email
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject of email
     * @param subject subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets message of email
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message of email
     * @param message message 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets date of creation of this entity
     * @return date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets date of creationg of this entity
     * @param createdAt date
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets public copy, multiple addresses are separated by single space
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets public copy, multiple addresses are separated by single space
     * @param cc 
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Gets private copy, multiple addresses are separated by single space
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets private copy, multiple addresses are separated by single space
     * @param bcc bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * Gets owner/user of this email
     * @return user
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owner/user of this email
     * @param owner user
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    /**
     * Gets formatted date of creation in pattern YYYY/MM/dd HH:mm
     * @return formated date
     */
    public String getCreatedAtFormatted() {
        return new SimpleDateFormat("YYYY/MM/dd HH:mm").format(createdAt);
    }
    
    /**
     * Hashcode
     * @return 
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
     * @return true if IDs are the same
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * String representation of the object
     * @return 
     */
    @Override
    public String toString() {
        return "cz.vse.webmail.domain.Email[ id=" + id + " ]";
    }
    
}
