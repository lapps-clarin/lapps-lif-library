/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.exception;

/**
 *
 * @author felahi
 */
public class JSONValidityException extends Exception {

    public JSONValidityException() {
    }

    public JSONValidityException(String message) {
        super(message);
    }

    public JSONValidityException(Throwable cause) {
        super(cause);
    }

    public JSONValidityException(String message, Throwable cause) {
        super(message, cause);
    }

}
