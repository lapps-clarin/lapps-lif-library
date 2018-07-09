/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifMarkable;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifReference;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifReferenceLayer;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
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

    public LifRefererenceLayerStored(List<AnnotationInterpreter> lifAnnotations) throws LifException {
        extract(lifAnnotations);
    }

    private void extract(List<AnnotationInterpreter> lifAnnotations) {
        for (AnnotationInterpreter annotationObject : lifAnnotations) {
            LifReference lifReference = new LifReferenceStored(annotationObject.getFeatures());
            corferenceAnnotations.put(annotationObject.getId(), lifReference);
            Map<Object, Object> markable = AnnotationInterpreter.elementIdMapper.get(annotationObject.getId()).getFeatures();
            if (!markable.isEmpty()) {
                LifReference LifReference = new LifReferenceStored(markable);
                for (String markableId : LifReference.getMentions()) {
                    AnnotationInterpreter annotationInterpreter = AnnotationInterpreter.elementIdMapper.get(markableId);
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
