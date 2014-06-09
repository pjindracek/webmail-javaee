/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.utils;

/**
 *
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
