/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.annotation.api;

import de.tuebingen.uni.sfs.lapps.library.annotation.api.LifToken;
import de.tuebingen.uni.sfs.lapps.library.annotation.xb.DependencyEntityInfo;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifDependencyParser extends LifToken{
    
    public Vector<Long> getParseIndexs() throws Exception;
    
    public  List<DependencyEntityInfo> getDependencyEntities(Long parseIndex) throws Exception;
    

}
