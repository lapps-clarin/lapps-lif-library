/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile.api;

import com.fasterxml.jackson.core.JsonParseException;
import de.tuebingen.uni.sfs.lapps.constants.ErrorMessage;
import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import java.util.Set;


/**
 *
 * @author felahi
 */
public interface ValidityChecker extends ErrorMessage{
    
    public boolean isValid() throws JsonParseException, IOException,JsonValidityException,LifException  ;

}
