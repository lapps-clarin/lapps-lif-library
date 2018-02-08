/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.exceptions;

/**
 *
 * @author felahi
 */
public class JsonValidityException extends Exception {

    public JsonValidityException() {
    }

    public JsonValidityException(String message) {
        super(message);
    }

    public JsonValidityException(Throwable cause) {
        super(cause);
    }

    public JsonValidityException(String message, Throwable cause) {
        super(message, cause);
    }

}
