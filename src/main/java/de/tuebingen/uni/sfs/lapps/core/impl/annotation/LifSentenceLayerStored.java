/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifTokenLayer;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifTokenPosLemma;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author felahi
 */
public class LifSentenceLayerStored implements LifSentenceLayer {

    private List<AnnotationInterpreter> sentenceList = new ArrayList<AnnotationInterpreter>();

    public LifSentenceLayerStored(List<AnnotationInterpreter> lifAnnotationList) throws LifException {
        for (AnnotationInterpreter annotationObject : lifAnnotationList) {
            if (annotationObject.getUrl().equals(Discriminators.Uri.SENTENCE)) {
                sentenceList.add(annotationObject);
            }
        }
        if (sentenceList.isEmpty()) {
            throw new LifException("No sentence annotation is found!!");
        }
        Collections.sort(sentenceList);
    }

    @Override
    public List<AnnotationInterpreter> getSentenceList() {
        return sentenceList;
    }

}
