/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.utils;

import de.tuebingen.uni.sfs.lapps.core.impl.layer.LifSingleLayer;
import java.util.Comparator;

/**
 *
 * @author felahi
 */
public class AnnotationLayerOrdering implements Comparator<LifSingleLayer> {

    @Override
    public int compare(LifSingleLayer o1, LifSingleLayer o2) {
        return o1.getLayerIndex() - o2.getLayerIndex();
    }

}
