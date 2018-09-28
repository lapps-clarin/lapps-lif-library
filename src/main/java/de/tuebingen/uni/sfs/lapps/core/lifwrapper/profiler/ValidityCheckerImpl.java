/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler.JsonProfiler;
import com.fasterxml.jackson.core.JsonParseException;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifConstants;
import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.View;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler.ValidityChecker;

/**
 *
 * @author felahi
 */
public class ValidityCheckerImpl extends JsonProfiler implements ValidityChecker {

    private List<View> views = new ArrayList<View>();
    private Annotation annotation = null;

    public ValidityCheckerImpl(String fileString) {
        super(fileString);
    }

    public ValidityCheckerImpl() {
        super(null);
       
    }


    /*public ValidityCheckerImpl(Annotation annotation) {
        this.annotation = annotation;
    }*/
    
    //Lapps given validity will be used
    public boolean isValid() throws JsonParseException, IOException, JsonValidityException, LifException {
        return (super.isValid()
                && isSchemaValid());
    }
    
    private boolean isSchemaValid() {
        return true;
    }

     //Internal validity check is  closed..
   /* public boolean isJsonValid() throws LifException {
        return (isNonEmptyDocument()
                && isDocumentStructureValid()
                && isToplevelAnnotationValid());
    }*/

    public boolean isNonEmptyDocument() throws LifException {
        if (super.getJsonMap().keySet().isEmpty()) {
            throw new LifException(MESSAGE_INVALID_JSON);
        }
        return true;
    }

    public boolean isDocumentStructureValid() throws LifException {
        Set<String> annotationSet = super.getJsonMap().keySet();
        if (annotationSet.contains(LifConstants.DocumentStructure.PAYLOAD_KEY_JSON)
                && annotationSet.contains(LifConstants.DocumentStructure.DISCRIMINATOR_KEY_JSON)) {
            return true;
        } else if (!annotationSet.contains(LifConstants.DocumentStructure.PAYLOAD_KEY_JSON)
                && annotationSet.contains(LifConstants.DocumentStructure.DISCRIMINATOR_KEY_JSON)) {
            throw new LifException(MESSAGE_INVALID_LIF_PAYLOAD_DOCUMENT);
        } else if (annotationSet.contains(LifConstants.DocumentStructure.PAYLOAD_KEY_JSON)
                && !annotationSet.contains(LifConstants.DocumentStructure.DISCRIMINATOR_KEY_JSON)) {
            throw new LifException(MESSAGE_INVALID_LIF_DISCRIMINATOR_DOCUMENT);
        } else {
            throw new LifException(MESSAGE_INVALID_LIF_DISCRIMINATOR_DOCUMENT);
        }
    }

    public boolean isToplevelAnnotationValid() throws LifException {
        Set<String> topLevelAnnotationSet = new HashSet<String>();
        LinkedHashMap linkedHashMap = null;

        for (String key : super.getJsonMap().keySet()) {
            if (key.contains(LifConstants.DocumentStructure.PAYLOAD_KEY_JSON)) {
                Object payLoadContent = super.getJsonMap().get(key);
                if (payLoadContent instanceof LinkedHashMap) {
                    linkedHashMap = (LinkedHashMap) super.getJsonMap().get(key);
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
        /*if (!annotationSet.contains(LifDocumentConnstant.Annotation.Document.LifDocumentTopLevel.CONTEXT_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_CONTEXT_MISSING);
        } else*/ 
        //temporary closed...
        if (!annotationSet.contains(LifConstants.DocumentStructure.TopLevel.METADATA_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_METADATA_MISSING);
        } else if (!annotationSet.contains(LifConstants.DocumentStructure.TopLevel.VIEWS_KEY_LIF)) {
            throw new LifException(MESSAGE_INVALID_LIF_TOPLEVEL_VIEWS_MISSING);
        } else if (!annotationSet.contains(Discriminators.Alias.TEXT)) {
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
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isMetadataVsAnnotationValid(String layer, Set<String> metadataInfoInLayers, Set<String> annotationInfoInLayers) throws LifException{
        if (layer.contains(Discriminators.Uri.NE)) {
            return isNamedEntityValid(annotationInfoInLayers);
        }  if (layer.contains(Discriminators.Uri.POS)||layer.contains(Discriminators.Uri.LEMMA)) {
            return isPosLayerValid(annotationInfoInLayers);
        } else if (annotationInfoInLayers.contains(layer)) {
            return true;
        } else {
            throw new LifException(MESSAGE_INVALID_LIF_METADATA_ANNOTATION+ layer);
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
        //PERSON, DATE, and ORGANIZATION will be deleted later
        for (String layer : annotationInfoInLayers) {
            if (layer.equals(Discriminators.Uri.PERSON))
                ; else if (layer.equals(Discriminators.Uri.LOCATION)) 
                 ; else if (layer.equals(Discriminators.Uri.DATE)) 
                     ; else if (layer.equals(Discriminators.Uri.ORGANIZATION)) 
                         ;
                         else if (layer.equals(Discriminators.Uri.NE)) 
                         ;else {
                return false;
            }
        }
        return true;
    }

    private boolean isPosLayerValid(Set<String> annotationInfoInLayers) {
            for (String layer : annotationInfoInLayers) {
               if (!layer.contains(Discriminators.Uri.TOKEN))
                 return false;
        }
        return true;
    }

}
