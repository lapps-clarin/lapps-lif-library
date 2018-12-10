/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifNameEntity;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifReference;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
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

    public LifNameEntityLayerStored(List<LifAnnotationMapper> lifAnnotations) throws LifException {
        try {
            extractAnnotations(lifAnnotations);
        } catch (LifException exp) {
            throw new LifException(exp.getMessage());
        }
    }

    public void extractAnnotations(List<LifAnnotationMapper> lifAnnotationList) throws LifException {
        for (LifAnnotationMapper annotationObject : lifAnnotationList) {
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
