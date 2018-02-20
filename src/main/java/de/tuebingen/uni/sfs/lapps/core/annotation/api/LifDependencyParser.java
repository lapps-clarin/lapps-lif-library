/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.api;

import de.tuebingen.uni.sfs.lapps.utils.DependencyEntityInfo;
import java.util.List;
import java.util.Vector;
import de.tuebingen.uni.sfs.lapps.core.annotation.api.LifTokenLayer;


/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifDependencyParser extends LifTokenLayer{
    
    public Vector<Long> getParseIndexs() throws Exception;
    
    public  List<DependencyEntityInfo> getDependencyEntities(Long parseIndex) throws Exception;
    

}
