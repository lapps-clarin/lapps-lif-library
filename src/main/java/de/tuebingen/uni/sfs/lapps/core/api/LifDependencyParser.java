/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.api;

import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.DependencyEntityInfo;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifDependencyParser {
    
    public Vector<Long> getParseIndexs();
    
    public LifSentenceLayer getSentenceLayer() throws LifException;  
    
    public  List<DependencyEntityInfo> getDependencyEntities(Long parseIndex) throws LifException;    
}
