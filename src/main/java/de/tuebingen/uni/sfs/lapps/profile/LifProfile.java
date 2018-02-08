/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile;

import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author felahi
 */
public interface LifProfile {
    
     public String getLanguage() throws LifException;

    public String getText() throws LifException;

    public String getFileString();

    public boolean isValid();

    public Map<Integer, List<AnnotationInterpreter>> getAnnotationLayerData();

    public List<AnnotationInterpreter> getAnnotationLayerData(Integer index);

    public Map<Integer, AnnotationLayerFinder> getIndexAnnotationLayer();

    public AnnotationLayerFinder getIndexAnnotationLayer(Integer index);

    public Vector<Integer> getSortedLayer();

}
