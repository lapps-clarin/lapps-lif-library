/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifNameEntity;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifReference;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifNameEntityLayerStored implements LifNameEntityLayer {

    private List<AnnotationInterpreter> tokenList = new ArrayList<AnnotationInterpreter>();
    private Map<String, Long> tokenIdStartIdMapper = new HashMap<String, Long>();
    private List<LifNameEntity> nameEntityList = new ArrayList<LifNameEntity>();

    public LifNameEntityLayerStored(List<AnnotationInterpreter> lifAnnotations) throws LifException {
        try {
            extractAnnotations(lifAnnotations);
        } catch (LifException exp) {
            throw new LifException(exp.getMessage());
        }
    }

    public void extractAnnotations(List<AnnotationInterpreter> lifAnnotationList) throws LifException {

        for (AnnotationInterpreter annotationObject : lifAnnotationList) {
            if (annotationObject.getUrl().equals(Discriminators.Uri.TOKEN)) {
                this.tokenList.add(annotationObject);
                this.tokenIdStartIdMapper.put(annotationObject.getId(), annotationObject.getStart());
            }
            if (annotationObject.getUrl().equals(Discriminators.Uri.NE)) {
                LifNameEntityStored lifNameEntity = new LifNameEntityStored(annotationObject);
                this.nameEntityList.add(lifNameEntity);
            }
        }
        if (nameEntityList.isEmpty()) {
            throw new LifException("No name entity annotations found inside the view of Name Entity layer!!");
        }
        if (tokenList.isEmpty()) {
            throw new LifException("No token annotations found!!");
        }
    }

    @Override
    public List<LifNameEntity> getNameEntityList() {
        return nameEntityList;
    }

    @Override
    public List<AnnotationInterpreter> getTokenList() {
        return tokenList;
    }

}
