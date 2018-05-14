package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import de.tuebingen.uni.sfs.lapps.constants.LifDocumentConnstant;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import org.lappsgrid.discriminator.Discriminators;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LIFAnnotationLayers {

    private Map<String, List<AnnotationInterpreter>> seperateLayers = new HashMap<String, List<AnnotationInterpreter>>();
    private List<LIFAnnotationLayer> lifLayers = new ArrayList<LIFAnnotationLayer>();

    public LIFAnnotationLayers(Map<String, List<AnnotationInterpreter>> seperateLayers) throws LifException {
        this.seperateLayers = seperateLayers;
        this.combineAnnotations();
    }

    public void combineAnnotations() throws LifException {
        for (String layer : LifDocumentConnstant.Annotation.Ordering.LIF_LAYER_ORDER.keySet()) {
            if (seperateLayers.containsKey(layer)) {
                LIFAnnotationLayer lifLayer = this.findLayer(layer);
                if (lifLayer != null) {
                    lifLayers.add(lifLayer);
                }
            }
        }
         Collections.sort(lifLayers, new AnnotationLayerOrdering());
         /*for(LIFAnnotationLayer lifLayer:lifLayers){
             System.out.println(lifLayer);
         }*/
        
    }

    public LIFAnnotationLayer findLayer(String layer) {
        if (layer.equals(Discriminators.Uri.TOKEN)) {
            return new LIFAnnotationLayer(layer, seperateLayers.get(layer));
        } else if (layer.contains(Discriminators.Uri.POS)) {
            return new LIFAnnotationLayer(layer, seperateLayers.get(layer));
        } else if (layer.contains(Discriminators.Uri.LEMMA)) {
            return new LIFAnnotationLayer(layer, seperateLayers.get(layer));
        } else if (layer.equals(Discriminators.Uri.COREF)) {
            return this.isCorferenceLayer(layer);
        } else if (layer.equals(Discriminators.Uri.NE)) {
            return this.isNamedEntityLayer(layer);
        } else if (layer.equals(Discriminators.Uri.SENTENCE)) {
            return this.isSenetenceLayer(layer);
        } else if (layer.equals(Discriminators.Uri.PHRASE_STRUCTURE)) {
            return this.isConstituentLayer(layer);
        } else if (layer.equals(Discriminators.Uri.DEPENDENCY_STRUCTURE)) {
            return this.isDependencyLayer(layer);
        }
        return null;
    }

    public LIFAnnotationLayer isDependencyLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.DEPENDENCY_STRUCTURE)
                && seperateLayers.containsKey(Discriminators.Uri.DEPENDENCY)
                && (seperateLayers.containsKey(Discriminators.Uri.POS))) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.DEPENDENCY_STRUCTURE));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.DEPENDENCY));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.POS));
            return new LIFAnnotationLayer(Discriminators.Uri.DEPENDENCY_STRUCTURE, mergeAnnotations);
        }
        return null;
    }

    public LIFAnnotationLayer isConstituentLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.PHRASE_STRUCTURE)
                && seperateLayers.containsKey(Discriminators.Uri.CONSTITUENT)
                && (seperateLayers.containsKey(Discriminators.Uri.POS))) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.PHRASE_STRUCTURE));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.CONSTITUENT));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.POS));
            return new LIFAnnotationLayer(Discriminators.Uri.PHRASE_STRUCTURE, mergeAnnotations);
        }
        return null;
    }

    public LIFAnnotationLayer isCorferenceLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        System.out.println(seperateLayers.keySet().toString());
        if (seperateLayers.containsKey(Discriminators.Uri.COREF)
                && seperateLayers.containsKey(Discriminators.Uri.MARKABLE)
                && (seperateLayers.containsKey(Discriminators.Uri.TOKEN)||seperateLayers.containsKey(Discriminators.Uri.POS))) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.COREF));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.MARKABLE));
            if(seperateLayers.containsKey(Discriminators.Uri.TOKEN))
              mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.TOKEN));
            if(seperateLayers.containsKey(Discriminators.Uri.POS))
                mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.POS));
            return new LIFAnnotationLayer(Discriminators.Uri.COREF, mergeAnnotations);
        }
        return null;
    }

    public LIFAnnotationLayer isSenetenceLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.SENTENCE)
                && seperateLayers.containsKey(Discriminators.Uri.TOKEN)) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.SENTENCE));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.TOKEN));
            return new LIFAnnotationLayer(Discriminators.Uri.SENTENCE, mergeAnnotations);
        }
        return null;
    }

    public LIFAnnotationLayer isNamedEntityLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.NE)) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.TOKEN));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.NE));
            return new LIFAnnotationLayer(Discriminators.Uri.NE, mergeAnnotations);
        }
        return null;
    }

    public List<LIFAnnotationLayer> getLayers() {
        return lifLayers;
    }
}
