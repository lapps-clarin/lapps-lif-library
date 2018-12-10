/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author felahi
 */
public class LifSentenceLayerStored implements LifSentenceLayer {

    private List<LifAnnotationMapper> sentenceList = new ArrayList<LifAnnotationMapper>();

    public LifSentenceLayerStored(List<LifAnnotationMapper> lifAnnotationList) throws LifException {
        this.sentenceList = lifAnnotationList;
        if (!sentenceList.isEmpty()) {
            Collections.sort(sentenceList);

        }
        // else
        //throw new LifException("No sentence annotation is found!!"); 
    }

    @Override
    public List<LifAnnotationMapper> getSentenceList() {
        return sentenceList;
    }

}
