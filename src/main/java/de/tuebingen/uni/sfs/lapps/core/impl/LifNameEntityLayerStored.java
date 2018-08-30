/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl;

import de.tuebingen.uni.sfs.lapps.core.api.LifNameEntity;
import de.tuebingen.uni.sfs.lapps.core.api.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.api.LifReference;
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
            if (annotationObject.getUrl().equals(Discriminators.Uri.NE)) {
                LifNameEntityStored lifNameEntity = new LifNameEntityStored(annotationObject);
                this.nameEntityList.add(lifNameEntity);
            }
        }
        if (nameEntityList.isEmpty()) {
            throw new LifException("No name entity annotations found inside the view of Name Entity layer!!");
        }
    }

    @Override
    public List<LifNameEntity> getNameEntityList() {
        return nameEntityList;
    }

}
