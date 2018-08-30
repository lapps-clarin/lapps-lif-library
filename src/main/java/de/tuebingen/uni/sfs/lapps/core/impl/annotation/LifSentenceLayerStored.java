/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author felahi
 */
public class LifSentenceLayerStored implements LifSentenceLayer {

    private List<AnnotationInterpreter> sentenceList = new ArrayList<AnnotationInterpreter>();

    public LifSentenceLayerStored(List<AnnotationInterpreter> lifAnnotationList) throws LifException {
        this.sentenceList = lifAnnotationList;
        if (!sentenceList.isEmpty()) {
            Collections.sort(sentenceList);

        }
        // else
        //throw new LifException("No sentence annotation is found!!"); 
    }

    @Override
    public List<AnnotationInterpreter> getSentenceList() {
        return sentenceList;
    }

}
