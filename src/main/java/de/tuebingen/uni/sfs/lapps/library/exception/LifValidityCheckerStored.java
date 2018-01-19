/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.exception;

import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
import de.tuebingen.uni.sfs.lapps.library.constants.LifVocabularies;
import de.tuebingen.uni.sfs.lapps.library.profile.JsonProfile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class LifValidityCheckerStored implements LifValidityChecker {

    private JsonProfile jsonObject = null;
    private List<View> views = new ArrayList<View>();
    private Annotation annotation = null;

    public LifValidityCheckerStored() {

    }

    public LifValidityCheckerStored(JsonProfile jsonObject) {
        this.jsonObject = jsonObject;
    }

    public LifValidityCheckerStored(Annotation annotation) {
        this.annotation = annotation;
    }

    public boolean isValid() throws LifException {
        return (isNonEmptyDocument()
                && isDocumentStructureValid()
                && isToplevelAnnotationValid());
    }

    public boolean isNonEmptyDocument() throws LifException {
        if (jsonObject.getJsonMap().keySet().isEmpty()) {
            throw new LifException(MESSAGE_INVALID_JSON);
        }
        return true;
    }

    public boolean isDocumentStructureValid() throws LifException {
        Set<String> annotationSet = jsonObject.getJsonMap().keySet();
        if (annotationSet.contains(LifVocabularies.LIF.Document.PAYLOAD_KEY_JSON)
                && annotationSet.contains(LifVocabularies.LIF.Document.DISCRIMINATOR_KEY_JSON)) {
            return true;
        } else if (!annotationSet.contains(LifVocabularies.LIF.Document.PAYLOAD_KEY_JSON)
                && annotationSet.contains(LifVocabularies.LIF.Document.DISCRIMINATOR_KEY_JSON)) {
            throw new LifException(MESSAGE_INVALID_LIF_PAYLOAD_DOCUMENT);
        } else if (annotationSet.contains(LifVocabularies.LIF.Document.PAYLOAD_KEY_JSON)
                && !annotationSet.contains(LifVocabularies.LIF.Document.DISCRIMINATOR_KEY_JSON)) {
            throw new LifException(MESSAGE_INVALID_LIF_DISCRIMINATOR_DOCUMENT);
        } else {
            throw new LifException(MESSAGE_INVALID_LIF_DISCRIMINATOR_DOCUMENT);
        }
    }

    public boolean isToplevelAnnotationValid() throws LifException {
        Set<String> topLevelAnnotationSet = new HashSet<String>();
        LinkedHashMap linkedHashMap = null;

        for (String key : jsonObject.getJsonMap().keySet()) {
            if (key.contains(LifVocabularies.LIF.Document.PAYLOAD_KEY_JSON)) {
                Object payLoadContent = jsonObject.getJsonMap().get(key);
                if (payLoadContent instanceof LinkedHashMap) {
                    linkedHashMap = (LinkedHashMap) jsonObject.getJsonMap().get(key);
                    for (Object topLevelAnnoKey : linkedHashMap.keySet()) {
                        topLevelAnnotationSet.add(topLevelAnnoKey.toString());
                    }
                }
                if (payLoadContent instanceof String) {
                    throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL);
                }
            }

        }

        if (topLevelAnnotationCheck(topLevelAnnotationSet)) {
            return true;
        }
        return false;
    }

    private boolean topLevelAnnotationCheck(Set<String> annotationSet) throws LifException {
        if (!annotationSet.contains(LifVocabularies.LIF.Document.LifDocumentTopLevel.CONTEXT_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_CONTEXT_MISSING);
        } else if (!annotationSet.contains(LifVocabularies.LIF.Document.LifDocumentTopLevel.METADATA_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_METADATA_MISSING);
        } else if (!annotationSet.contains(LifVocabularies.LIF.Document.LifDocumentTopLevel.VIEWS_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_VIEWS_MISSING);
        } else if (!annotationSet.contains(LifVocabularies.LIF.Document.LifDocumentTopLevel.TEXT_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_TEXT_MISSING);
        } else {
            return true;
        }
    }

    public boolean isAnnotationLayerValid() throws LifException {
        if (this.views.isEmpty()) {
            throw new LifException(NO_ANNOTATION_FOUND);
        }
        return true;
    }

    public boolean isAnnotationValid() throws LifException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isMetadataLayerValid(String layer, Set<String> metadataInfoInLayers, Set<String> annotationInfoInLayers) throws LifException {
        if (layer.contains(Discriminators.Uri.NE)) {
            return isNamedEntityValid(annotationInfoInLayers);
        } else if (annotationInfoInLayers.contains(layer)) {
            return true;
        } else {
            throw new LifException("The metadata (or @type) informatiom." + " Please look into the " + layer + " layer!!");
        }
    }

    public boolean isMetadataLayerValid(String lifLayer, Set<String> annotationTypes) throws LifException {
        if (!annotationTypes.contains(lifLayer)) {
            throw new LifException(NO_ANNOTATION_IN_METADATA);
        }
        return true;
    }

    public boolean isTextValid(String text) {
        return false;
    }

    public boolean isLanguageValid(String language) {
        return false;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    private boolean isNamedEntityValid(Set<String> annotationInfoInLayers) {
        for (String layer : annotationInfoInLayers) {
            if (layer.equals(Discriminators.Uri.PERSON))
                ; else if (layer.equals(Discriminators.Uri.LOCATION)) 
                 ; else if (layer.equals(Discriminators.Uri.DATE)) 
                     ; else if (layer.equals(Discriminators.Uri.ORGANIZATION)) 
                         ; else {
                return false;
            }
        }
        return true;
    }

}
