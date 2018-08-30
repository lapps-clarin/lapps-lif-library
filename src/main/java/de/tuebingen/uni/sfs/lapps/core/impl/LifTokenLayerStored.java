/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl;

import de.tuebingen.uni.sfs.lapps.core.api.LifTokenLayer;
import de.tuebingen.uni.sfs.lapps.core.api.LifTokenPosLemma;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felahi
 */
public class LifTokenLayerStored implements LifTokenLayer {

    private List<LifTokenPosLemma> tokenList = new ArrayList<LifTokenPosLemma>();
    private boolean tokenFlag = false;
    private boolean posFlag = false;
    private boolean lemmaFlag = false;

    public LifTokenLayerStored() {

    }

    public LifTokenLayerStored(List<AnnotationInterpreter> annotations) {
        this.extract(annotations);
    }

    private void extract(List<AnnotationInterpreter> annotations) {
        boolean flag = true;
        for (AnnotationInterpreter annotationInterpreter : annotations) {
            LifTokenPosLemma lifTokenPosLemma = new LifTokenPosLemmaStored(annotationInterpreter);
            tokenList.add(lifTokenPosLemma);
            if (flag) {
                flag=this.findLayers(lifTokenPosLemma);
            }
        }
    }

    @Override
    public List<LifTokenPosLemma> getTokenList() {
        return tokenList;
    }

    private boolean findLayers(LifTokenPosLemma lifTokenPosLemma) {
        if (lifTokenPosLemma.getWord() != null) {
            this.tokenFlag = true;
        }
        if (lifTokenPosLemma.getPos() != null) {
            this.posFlag = true;
        }
        if (lifTokenPosLemma.getLemma() != null) {
            this.lemmaFlag = true;
        }
        return false;
    }

    @Override
    public boolean isTokenLayer() {
        return this.tokenFlag;
    }

    @Override
    public boolean isPosLayer() {
        return this.posFlag;
    }

    @Override
    public boolean isLemmaLayer() {
        return this.lemmaFlag;
    }

}
