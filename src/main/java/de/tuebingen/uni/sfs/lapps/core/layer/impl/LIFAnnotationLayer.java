/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import de.tuebingen.uni.sfs.lapps.constants.LifDocumentConnstant;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LIFAnnotationLayer implements Comparator<LIFAnnotationLayer>{

    private String layer = null;
    private Integer layerIndex = -1;
    private List<AnnotationInterpreter> annotations;
    
    public LIFAnnotationLayer() {
    }


    public LIFAnnotationLayer(String layer, List<AnnotationInterpreter> annotations) {
        this.layer = layer;
        this.layerIndex = setLayerIndex();
        this.annotations = annotations;
    }

    public Integer setLayerIndex() {
        if (this.layer != null) {
            if (isValid()) {
                layerIndex = LifDocumentConnstant.Annotation.Ordering.LIF_LAYER_ORDER.get(layer);
            }
        }
        return layerIndex;
    }

    public boolean isValid() {
        if (LifDocumentConnstant.Annotation.Ordering.LIF_LAYER_ORDER.containsKey(layer)) {
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
    public int compare(LIFAnnotationLayer o1, LIFAnnotationLayer o2) {
        return o1.getLayerIndex()- o2.getLayerIndex();
    }

    @Override
    public String toString() {
        return "LIFAnnotationLayer{" + "layer=" + layer + ", layerIndex=" + layerIndex + ", annotations=" + annotations + '}';
    }

}
