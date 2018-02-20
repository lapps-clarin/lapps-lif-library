/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.api;

import de.tuebingen.uni.sfs.lapps.core.annotation.impl.LifConstituent;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import de.tuebingen.uni.sfs.lapps.core.annotation.api.LifTokenLayer;



/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifConstituentParser extends LifTokenLayer{
    
    public Vector<Long> getParseIndexs() ;

    public LifConstituent getRoot(Long parseIndex) throws LifException;

    public  List<LifConstituent> getConstituentEntities(Long parseIndex) throws LifException;

    public Map<String, Long> getTokenIdStartIdMapper();

}
