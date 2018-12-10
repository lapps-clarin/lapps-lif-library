/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifConstants;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifConstituentParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifDependencyParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifReferenceLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifTokenLayer;
import de.tuebingen.uni.sfs.lapps.utils.LifAnnotationMapper;
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
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.View;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifConstituentParserStored;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifDependencyParserStored;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifNameEntityLayerStored;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifRefererenceLayerStored;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifSentenceLayerStored;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifTokenLayerStored;
import org.lappsgrid.discriminator.Discriminators;
import de.tuebingen.uni.sfs.lapps.core.converter.api.ErrorMessage;

/**
 *
 * @author felahi
 */
public class LifFormatImpl implements LifFormat {

    private String fileString = null;
    private String text = null;
    private String language = null;
    private LifTokenLayer lifTokenLayer = null;
    private LifSentenceLayer lifSentenceLayer = null;
    private LifNameEntityLayer lifNameEntityLayer = null;
    private LifDependencyParser lifDependencyParser = null;
    private LifConstituentParser lifConstituentParser = null;
    private LifReferenceLayer lifRefererenceLayer = null;
    private Map<String, List<LifAnnotationMapper>> lifLayerAnnotationsMap = new HashMap<String, List<LifAnnotationMapper>>();

    public LifFormatImpl(InputStream is) throws LifException {
        try {
            fileString = IOUtils.toString(is, LifConstants.GeneralParameters.UNICODE);
            if (isValid()) {
                layerOrderingCombining();
            } else {
                throw new LifException(ErrorMessage.Lif.MESSAGE_INVALID_LIF);
            }
        } catch (JsonValidityException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            throw new LifException(ErrorMessage.Lif.MESSAGE_JSON_ERROR);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            throw new LifException(ErrorMessage.Lif.MESSAGE_File_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            throw new LifException(ErrorMessage.Lif.MESSAGE_UNKNOWN_ERROR);
        }

    }

    @Override
    public boolean isValid() throws JsonParseException, IOException, JsonValidityException, LifException {
        LifContainerMapper lifContainer = null;
        ValidityCheckerImpl lifValidityCheck = new ValidityCheckerImpl(fileString);
        if (lifValidityCheck.isValid()) {
            lifContainer = new ObjectMapper().readValue(fileString, LifContainerMapper.class);
            this.text = lifContainer.getContainer().getText();
            this.language = lifContainer.getContainer().getLanguage();
        } else {
            throw new JsonValidityException(ErrorMessage.Lif.INVALID_JSON_FILE);
        }
        if (lifContainer != null) {
            processViews(lifContainer.getContainer().getViews());
        } else {
            throw new LifException(ErrorMessage.Lif.MESSAGE_INVALID_LIF);
        }
        return true;
    }

    private void processViews(List<View> views) throws LifException, IOException, JsonParseException, JsonValidityException {
        LifAnnotationMapper.elementIdMapper = new HashMap<String, LifAnnotationMapper>();
        for (View view : views) {
            Set<String> layerInMetadata = findLayersFromMetadata(view.getMetadata());
            Map<String, List<LifAnnotationMapper>> lifLayers = findLayersAndAnnotations(view.getAnnotations());
            this.takeLast(lifLayers);
        }
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

    private Map<String, List<LifAnnotationMapper>> findLayersAndAnnotations(List<Annotation> annotations) {
        Map<String, List<LifAnnotationMapper>> lifLayers = new HashMap<String, List<LifAnnotationMapper>>();
        List<LifAnnotationMapper> annotationInterpreterList = new ArrayList<LifAnnotationMapper>();
        for (Annotation annotation : annotations) {
            annotationInterpreterList = new ArrayList<LifAnnotationMapper>();
            String type = annotation.getAtType();
            LifAnnotationMapper annotationInterpreter = new LifAnnotationMapper(annotation);
            if (lifLayers.containsKey(type)) {
                annotationInterpreterList = lifLayers.get(type);
            }
            annotationInterpreterList.add(annotationInterpreter);
            lifLayers.put(type, annotationInterpreterList);
        }
        return lifLayers;
    }

    private void takeLast(Map<String, List<LifAnnotationMapper>> lifLayers) {
        for (String layer : lifLayers.keySet()) {
            this.lifLayerAnnotationsMap.put(layer, lifLayers.get(layer));
        }
    }

    private void layerOrderingCombining() throws LifException {
        for (String layer : LifConstants.Annotation.Ordering.LIF_LAYER_ORDER.keySet()) {
            if (lifLayerAnnotationsMap.containsKey(layer)) {
                if (layer.contains(Discriminators.Uri.TOKEN)) {
                    lifTokenLayer = new LifTokenLayerStored(lifLayerAnnotationsMap.get(layer));
                }
                if (layer.contains(Discriminators.Uri.SENTENCE)) {
                    lifSentenceLayer = new LifSentenceLayerStored(lifLayerAnnotationsMap.get(layer));
                }
                if (layer.contains(Discriminators.Uri.NE)) {
                    lifNameEntityLayer = new LifNameEntityLayerStored(lifLayerAnnotationsMap.get(layer));
                }
                if (layer.contains(Discriminators.Uri.DEPENDENCY_STRUCTURE)) {
                    lifDependencyParser = new LifDependencyParserStored(lifLayerAnnotationsMap.get(layer));
                }
                if (layer.contains(Discriminators.Uri.PHRASE_STRUCTURE)) {
                    lifConstituentParser = new LifConstituentParserStored(lifLayerAnnotationsMap.get(layer));

                }
                if (layer.contains(Discriminators.Uri.COREF)) {
                    lifRefererenceLayer = new LifRefererenceLayerStored(lifLayerAnnotationsMap.get(layer));
                }
            }
        }
    }

    @Override
    public String getLanguage() throws LifException {
        return this.language;
    }

    @Override
    public String getText() throws LifException {
        return this.text;
    }

    @Override
    public String getFileString() {
        return this.fileString;
    }

    @Override
    public LifTokenLayer getLifTokenLayer() {
        return lifTokenLayer;
    }

    @Override
    public LifSentenceLayer getLifSentenceLayer() {
        return lifSentenceLayer;
    }

    @Override
    public LifNameEntityLayer getLifNameEntityLayer() {
        return lifNameEntityLayer;
    }

    @Override
    public LifDependencyParser getLifDependencyParser() {
        return lifDependencyParser;
    }

    @Override
    public LifConstituentParser getLifConstituentParser() {
        return lifConstituentParser;
    }

    @Override
    public LifReferenceLayer getLifRefererenceLayer() {
        return lifRefererenceLayer;
    }

}
