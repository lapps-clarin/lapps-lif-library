/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import java.util.Comparator;

/**
 *
 * @author felahi
 */
public class AnnotationLayerOrdering implements Comparator<LIFAnnotationLayer> {

    @Override
    public int compare(LIFAnnotationLayer o1, LIFAnnotationLayer o2) {
        return o1.getLayerIndex() - o2.getLayerIndex();
    }

}
