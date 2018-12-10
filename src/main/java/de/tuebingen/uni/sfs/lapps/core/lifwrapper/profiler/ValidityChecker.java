/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler;

import com.fasterxml.jackson.core.JsonParseException;
import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;


/**
 *
 * @author felahi
 */
public interface ValidityChecker{
    
    public boolean isValid() throws JsonParseException, IOException,JsonValidityException,LifException  ;

}
