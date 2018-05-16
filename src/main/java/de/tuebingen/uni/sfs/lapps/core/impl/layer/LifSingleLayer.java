/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.layer;

import de.tuebingen.uni.sfs.lapps.constants.LifConstants;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifSingleLayer implements Comparator<LifSingleLayer>{

    private String layer = null;
    private Integer layerIndex = -1;
    private List<AnnotationInterpreter> annotations;
    
    public LifSingleLayer() {
    }


    public LifSingleLayer(String layer, List<AnnotationInterpreter> annotations) {
        this.layer = layer;
        this.layerIndex = setLayerIndex();
        this.annotations = annotations;
    }

    public Integer setLayerIndex() {
        if (this.layer != null) {
            if (isValid()) {
                layerIndex = LifConstants.Annotation.Ordering.LIF_LAYER_ORDER.get(layer);
            }
        }
        return layerIndex;
    }

    public boolean isValid() {
        if (LifConstants.Annotation.Ordering.LIF_LAYER_ORDER.containsKey(layer)) {
            return true;
        }
        return false;
    }

    public String getLayer() {
        return this.layer;
    }

    public Integer getLayerIndex() {
        return layerIndex;
    }

    public List<AnnotationInterpreter> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationInterpreter> annotations) {
        this.annotations=new ArrayList<AnnotationInterpreter>();
        this.annotations = annotations;
    }

    @Override
    public int compare(LifSingleLayer o1, LifSingleLayer o2) {
        return o1.getLayerIndex()- o2.getLayerIndex();
    }

    @Override
    public String toString() {
        return "LIFAnnotationLayer{" + "layer=" + layer + ", layerIndex=" + layerIndex + ", annotations=" + annotations + '}';
    }

}
