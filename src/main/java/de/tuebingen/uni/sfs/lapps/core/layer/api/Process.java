/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.layer.api;

import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author felahi
 */
public abstract class Process {

    public abstract void inputDataProcessing(InputStream is) throws IOException,JSONValidityException,LifException;

    public abstract void process(OutputStream os);

    public abstract boolean isValid();
}
