package cz.vse.webmail.utils;

/**
 * Exception indicating duplicate email in the DB
 * @author Petr
 */
public class DuplicateEmailException extends RuntimeException {

    /**
     * Creates a new instance of <code>DuplicateEmailException</code> without detail
     * message.
     */
    public DuplicateEmailException() {
    }

    /**
     * Constructs an instance of <code>DuplicateEmailException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateEmailException(String msg) {
        super(msg);
    }
}
