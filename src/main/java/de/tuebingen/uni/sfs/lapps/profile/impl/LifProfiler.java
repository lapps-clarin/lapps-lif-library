/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.constants.LifDocumentConnstant;
import de.tuebingen.uni.sfs.lapps.core.annotation.impl.LifTokenPosLemmaStored;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LIFAnnotationLayer;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LIFAnnotationLayers;
import de.tuebingen.uni.sfs.lapps.profile.api.LifProfile;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class LifProfiler implements LifProfile {

    private String fileString = null;
    private LifContainerMapper lifContainer = new LifContainerMapper();
    private Map<String, List<AnnotationInterpreter>> lifLayerAnnotationsMap = new HashMap<String, List<AnnotationInterpreter>>();
    private ValidityCheckerImpl lifValidityCheck;
    private List<View> views = new ArrayList<View>();
    private LIFAnnotationLayers lifAnnotationLayers; ;

    public LifProfiler(InputStream is) throws LifException, IOException, JsonValidityException {
        fileString = IOUtils.toString(is, LifDocumentConnstant.GeneralParameters.UNICODE);
        if (isValid()) {
            lifAnnotationLayers=new LIFAnnotationLayers(lifLayerAnnotationsMap);
        } else {
            throw new LifException(ValidityCheckerImpl.MESSAGE_INVALID_LIF);
        }
    }

    @Override
    public boolean isValid() throws JsonParseException, IOException, JsonValidityException, LifException {
        lifValidityCheck = new ValidityCheckerImpl(fileString);
        if (lifValidityCheck.isValid()) {
            lifContainer = new ObjectMapper().readValue(fileString, LifContainerMapper.class);
        } else {
            throw new JsonValidityException(ValidityCheckerImpl.INVALID_JSON_FILE);
        }
        if (lifContainer != null) {
            List<View> views = lifContainer.getContainer().getViews();
            processViews(views);
            lifAnnotationLayers=new LIFAnnotationLayers(lifLayerAnnotationsMap);
        } else {
            throw new LifException(ValidityCheckerImpl.MESSAGE_INVALID_LIF);
        }
        return true;
    }

    private void processViews(List<View> views) throws LifException, IOException, JsonParseException, JsonValidityException {
        for (View view : views) {
            Set<String> layerInMetadata = findLayersFromMetadata(view.getMetadata());
            Map<String, List<AnnotationInterpreter>> lifLayers = findLayersAndAnnotations(view.getAnnotations());
            this.takeLast(lifLayers);
        }

        /* for(String layer:lifLayerAnnotationsMap.keySet()){
           System.out.println("annotation layer:......." + layer);
            System.out.println("layer:......." + lifLayerAnnotationsMap.get(layer));
            
        }*/
    }

    private Set<String> findLayersFromMetadata(Map metadataMap) {
        Set<String> layerInMetadata = new HashSet<String>();
        for (Object key : metadataMap.keySet()) {
            Map innerMap = (Map) metadataMap.get(key);
            for (Object attribute : innerMap.keySet()) {
                String url = attribute.toString();
                layerInMetadata.add(url);
            }
        }
        return layerInMetadata;
    }

    private Map<String, List<AnnotationInterpreter>> findLayersAndAnnotations(List<Annotation> annotations) {
        Map<String, List<AnnotationInterpreter>> lifLayers = new HashMap<String, List<AnnotationInterpreter>>();
        AnnotationInterpreter.elementIdMapper = new HashMap<String, AnnotationInterpreter>();
        List<AnnotationInterpreter> annotationInterpreterList = new ArrayList<AnnotationInterpreter>();
        for (Annotation annotation : annotations) {
            annotationInterpreterList = new ArrayList<AnnotationInterpreter>();
            String type = this.getType(annotation);
            AnnotationInterpreter annotationInterpreter = new AnnotationInterpreter(annotation);
            if (lifLayers.containsKey(type)) {
                annotationInterpreterList = lifLayers.get(type);
            }
            annotationInterpreterList.add(annotationInterpreter);
            lifLayers.put(type, annotationInterpreterList);
        }
        return lifLayers;
    }

    private String getType(Annotation annotation) {
        String type = annotation.getAtType();
        if (Discriminators.Uri.TOKEN.contains(type)) {
            LifTokenPosLemmaStored lifToken = new LifTokenPosLemmaStored(annotation.getFeatures());
            if (lifToken.getPos() != null) {
                return Discriminators.Uri.POS;
            } else if (lifToken.getLemma() != null) {
                return Discriminators.Uri.LEMMA;
            }
        }
        return type;
    }

    private void takeLast(Map<String, List<AnnotationInterpreter>> lifLayers) {
        for (String layer : lifLayers.keySet()) {
            this.lifLayerAnnotationsMap.put(layer, lifLayers.get(layer));
        }
    }

    @Override
    public String getLanguage() throws LifException {
        return lifContainer.getContainer().getLanguage();
    }

    @Override
    public String getText() throws LifException {
        return lifContainer.getContainer().getText();
    }

    @Override
    public String getFileString() {
        return this.fileString;
    }

    @Override
    public LIFAnnotationLayers getLifAnnotationLayers() {
        return lifAnnotationLayers;
    }
}
