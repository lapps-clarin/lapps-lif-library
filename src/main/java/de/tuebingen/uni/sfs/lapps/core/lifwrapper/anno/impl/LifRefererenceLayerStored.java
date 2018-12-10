/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifMarkable;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifReference;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifReferenceLayer;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifRefererenceLayerStored implements LifReferenceLayer {

    private Map<String, LifReference> corferenceAnnotations = new HashMap<String, LifReference>();
    private Map<String, LifMarkable> markableAnnotations = new HashMap<String, LifMarkable>();

    public LifRefererenceLayerStored(List<LifAnnotationMapper> lifAnnotations) throws LifException {
        extract(lifAnnotations);
    }

    private void extract(List<LifAnnotationMapper> lifAnnotations) {
        for (LifAnnotationMapper annotationObject : lifAnnotations) {
            LifReference lifReference = new LifReferenceStored(annotationObject.getFeatures());
            corferenceAnnotations.put(annotationObject.getId(), lifReference);
            Map<Object, Object> markable = LifAnnotationMapper.elementIdMapper.get(annotationObject.getId()).getFeatures();
            if (!markable.isEmpty()) {
                LifReference LifReference = new LifReferenceStored(markable);
                for (String markableId : LifReference.getMentions()) {
                    LifAnnotationMapper annotationInterpreter = LifAnnotationMapper.elementIdMapper.get(markableId);
                    LifMarkable lifMarkable = new LifMarkableStored(annotationInterpreter.getFeatures());
                    markableAnnotations.put(markableId, lifMarkable);
                }
            }

        }
    }

    public Map<String, LifReference> getCorferenceAnnotations() {
        return this.corferenceAnnotations;
    }

    public Map<String, LifMarkable> getMarkableAnnotations() {
        return this.markableAnnotations;
    }

}
