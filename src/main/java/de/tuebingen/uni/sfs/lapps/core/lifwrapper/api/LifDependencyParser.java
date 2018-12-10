/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.api;

import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifDependencyInfo;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifDependencyParser {
    
    public Vector<Long> getParseIndexs()throws LifException;
    
    public LifSentenceLayer getSentenceLayer() throws LifException;  
    
    public  List<LifDependencyInfo> getDependencyEntities(Long parseIndex) throws LifException; 
    
    public Boolean getRoot(Long parseIndex) throws LifException;
}
