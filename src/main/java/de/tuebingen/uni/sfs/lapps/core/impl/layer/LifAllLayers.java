package de.tuebingen.uni.sfs.lapps.core.impl.layer;

import de.tuebingen.uni.sfs.lapps.utils.AnnotationLayerOrdering;
import de.tuebingen.uni.sfs.lapps.constants.LifConstants;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import org.lappsgrid.discriminator.Discriminators;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifAllLayers {

    private Map<String, List<AnnotationInterpreter>> seperateLayers = new HashMap<String, List<AnnotationInterpreter>>();
    private List<LifSingleLayer> lifLayers = new ArrayList<LifSingleLayer>();

    public LifAllLayers(Map<String, List<AnnotationInterpreter>> seperateLayers) throws LifException {
        this.seperateLayers = seperateLayers;
        this.combineAnnotations();
    }

    public void combineAnnotations() throws LifException {
        for (String layer : LifConstants.Annotation.Ordering.LIF_LAYER_ORDER.keySet()) {
            if (seperateLayers.containsKey(layer)) {
                LifSingleLayer lifLayer = this.findLayer(layer);
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

    public LifSingleLayer findLayer(String layer) {
        if (layer.equals(Discriminators.Uri.TOKEN)) {
            return new LifSingleLayer(layer, seperateLayers.get(layer));
        } else if (layer.contains(Discriminators.Uri.POS)) {
            return new LifSingleLayer(layer, seperateLayers.get(layer));
        } else if (layer.contains(Discriminators.Uri.LEMMA)) {
            return new LifSingleLayer(layer, seperateLayers.get(layer));
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

    public LifSingleLayer isDependencyLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.DEPENDENCY_STRUCTURE)
                && seperateLayers.containsKey(Discriminators.Uri.DEPENDENCY)
                && (seperateLayers.containsKey(Discriminators.Uri.POS))) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.DEPENDENCY_STRUCTURE));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.DEPENDENCY));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.POS));
            return new LifSingleLayer(Discriminators.Uri.DEPENDENCY_STRUCTURE, mergeAnnotations);
        }
        return null;
    }

    public LifSingleLayer isConstituentLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.PHRASE_STRUCTURE)
                && seperateLayers.containsKey(Discriminators.Uri.CONSTITUENT)
                && (seperateLayers.containsKey(Discriminators.Uri.POS))) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.PHRASE_STRUCTURE));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.CONSTITUENT));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.POS));
            return new LifSingleLayer(Discriminators.Uri.PHRASE_STRUCTURE, mergeAnnotations);
        }
        return null;
    }

    public LifSingleLayer isCorferenceLayer(String layer) {
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
            return new LifSingleLayer(Discriminators.Uri.COREF, mergeAnnotations);
        }
        return null;
    }

    public LifSingleLayer isSenetenceLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.SENTENCE)
                && seperateLayers.containsKey(Discriminators.Uri.TOKEN)) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.SENTENCE));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.TOKEN));
            return new LifSingleLayer(Discriminators.Uri.SENTENCE, mergeAnnotations);
        }
        return null;
    }

    public LifSingleLayer isNamedEntityLayer(String layer) {
        List<AnnotationInterpreter> mergeAnnotations = new ArrayList<AnnotationInterpreter>();
        if (seperateLayers.containsKey(Discriminators.Uri.NE)) {
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.TOKEN));
            mergeAnnotations.addAll(seperateLayers.get(Discriminators.Uri.NE));
            return new LifSingleLayer(Discriminators.Uri.NE, mergeAnnotations);
        }
        return null;
    }

    public List<LifSingleLayer> getLayers() {
        return lifLayers;
    }
}
