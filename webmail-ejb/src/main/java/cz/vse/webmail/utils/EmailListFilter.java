package cz.vse.webmail.utils;

/**
 * Filter used for searching of emails
 * @author Petr
 */
public class EmailListFilter {
    
    private String to;

    /**
     * Gets receiver of an email
     * @return receiver's email address
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets receiver of an email
     * @param to receiver's email address
     */
    public void setTo(String to) {
        this.to = to;
    }
}
