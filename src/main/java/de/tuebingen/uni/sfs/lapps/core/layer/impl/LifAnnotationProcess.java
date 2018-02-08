/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import de.tuebingen.uni.sfs.lapps.core.layer.api.Process;
import de.tuebingen.uni.sfs.lapps.profile.LIFProfilerFinder;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifToolProducerStored;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.AnnotationInterpreter;
import de.tuebingen.uni.sfs.lapps.exceptions.LifValidityCheckerStored;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.constants.LifConnstant;
import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.apache.commons.io.IOUtils;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class LifAnnotationProcess extends Process {

    private Map<Integer, List<AnnotationInterpreter>> annotationLayerData = new HashMap<Integer, List<AnnotationInterpreter>>();
    private Map<Integer, AnnotationLayerFinder> indexAnnotationLayer = new HashMap<Integer, AnnotationLayerFinder>();
    private Vector<Integer> sortedLayer = new Vector<Integer>();
    public Container lifContainer = null;
    private String fileString = null;
    private LifValidityCheckerStored lifValidityCheck = new LifValidityCheckerStored();

    public LifAnnotationProcess(InputStream input) throws IOException, JSONValidityException, LifException {
        this.inputDataProcessing(input);
    }

    @Override
    public void inputDataProcessing(InputStream is) throws IOException, JSONValidityException, LifException {
        fileString = IOUtils.toString(is, LifConnstant.GeneralParameters.UNICODE);
        LIFProfilerFinder lifContainerFinder = lifContainerFinder = new LIFProfilerFinder(fileString);
        lifContainer = lifContainerFinder.getMascDocument().getContainer();
        extractAndSortViews();
    }

    @Override
    public void process(OutputStream os) {
    }

    public void extractAndSortViews() throws LifException {
        processViews();
        sortedLayer = new Vector(this.indexAnnotationLayer.keySet());
        Collections.sort(sortedLayer);
    }

    private void processViews() throws LifException {
        Integer index = 0;
        List<View> views = lifContainer.getViews();

        /*if (!isViewValid(views)) {
            return;
        }*/
        Set<Integer> ignoreViewsIndex = this.dealWithMultipleLayers(views);

        for (View view : views) {
            if (!ignoreViewsIndex.contains(index)) {
                LifToolProducerStored lifLayer = new LifToolProducerStored(view.getMetadata());
                List<AnnotationInterpreter> lifCharOffsetObjectList = lifLayer.processAnnotations(view.getAnnotations());
                if (!lifLayer.isLayerValid()) {
                    throw new LifException("The annotation layer "+lifLayer.getLayer()+"is not valid!!");
                }
                annotationLayerData.put(index, lifCharOffsetObjectList);
                indexAnnotationLayer.put(index, lifLayer);
            }
            index = index + 1;
        }

    }

    private Set<Integer> dealWithMultipleLayers(List<View> views) throws LifException {
        Map<String, Integer> annotationLayersToConsider = new HashMap<String, Integer>();
        Set<Integer> ignoreIndexSet = new HashSet<Integer>();
        for (Integer index = 0; index < views.size(); index++) {
            View view = views.get(index);
            LifToolProducerStored lifLayer = new LifToolProducerStored(view.getMetadata());
            if (annotationLayersToConsider.containsKey(lifLayer.getLayer())) {
                Integer ignoreIndex = annotationLayersToConsider.get(lifLayer.getLayer());
                ignoreIndexSet.add(ignoreIndex);
            }
            annotationLayersToConsider.put(lifLayer.getLayer(), index);
        }
        return ignoreIndexSet;
    }

    private boolean isViewValid(List<View> views) throws LifException {
        lifValidityCheck.setViews(views);
        if (!lifValidityCheck.isAnnotationLayerValid()) {
            return false;
        }
        return true;
    }

    public Map<Integer, List<AnnotationInterpreter>> getAnnotationLayerData() {
        return annotationLayerData;
    }

    public List<AnnotationInterpreter> getAnnotationLayerData(Integer index) {
        return annotationLayerData.get(index);
    }

    public Map<Integer, AnnotationLayerFinder> getIndexAnnotationLayer() {
        return indexAnnotationLayer;
    }

    public AnnotationLayerFinder getIndexAnnotationLayer(Integer index) {
        return this.indexAnnotationLayer.get(index);
    }

    public Vector<Integer> getSortedLayer() {
        return sortedLayer;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public String getLanguage() {
        return lifContainer.getLanguage();
    }

    public String getText() {
        return lifContainer.getText();
    }

    public String getFileString() {
        return fileString;
    }

    public Container getLifContainer() {
        return lifContainer;
    }

    @Override
    public String toString() {
        return "DataModelLif{" + "indexAnnotationLayer=" + indexAnnotationLayer + ", sortedLayer=" + sortedLayer + '}';
    }

}
