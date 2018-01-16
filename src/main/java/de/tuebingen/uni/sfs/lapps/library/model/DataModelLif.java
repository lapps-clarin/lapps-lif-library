/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.model;

import de.tuebingen.uni.sfs.lapps.library.profile.LIFProfilerFinder;
import de.tuebingen.uni.sfs.lapps.library.model.DataModel;
import de.tuebingen.uni.sfs.lapps.library.annotation.layer.xb.AnnotationLayerFinderStored;
import de.tuebingen.uni.sfs.lapps.library.annotation.layer.xb.AnnotationInterpreter;
import de.tuebingen.uni.sfs.lapps.library.utils.xb.ValidityCheckerStored;
import de.tuebingen.uni.sfs.lapps.library.annotation.layer.api.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
import de.tuebingen.uni.sfs.lapps.library.constants.LifVocabularies;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class DataModelLif extends DataModel {

    private Map<Integer, List<AnnotationInterpreter>> annotationLayerData = new HashMap<Integer, List<AnnotationInterpreter>>();
    private Map<Integer, AnnotationLayerFinder> indexAnnotationLayer = new HashMap<Integer, AnnotationLayerFinder>();
    private Vector<Integer> sortedLayer = new Vector<Integer>();
    public Container lifContainer = null;
    private String fileString = null;
    private boolean modelValidity = false;
    private ValidityCheckerStored lifValidityCheck = new ValidityCheckerStored();

    public DataModelLif(InputStream input) throws LifException, IOException {
        this.inputDataProcessing(input);
    }

    @Override
    public void inputDataProcessing(InputStream is) {
        try {
            fileString = IOUtils.toString(is, LifVocabularies.GeneralParameters.UNICODE);
            LIFProfilerFinder lifContainerFinder = lifContainerFinder = new LIFProfilerFinder(fileString);
            lifContainer = lifContainerFinder.getMascDocument().getContainer();
            extractAndSortViews();
            modelValidity = true;
        } catch (LifException ex) {
            modelValidity = false;
            Logger.getLogger(DataModelLif.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (IOException ex) {
            modelValidity = false;
            Logger.getLogger(DataModelLif.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (Exception ex) {
            modelValidity = false;
            Logger.getLogger(DataModelLif.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
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
                AnnotationLayerFinderStored lifLayer = new AnnotationLayerFinderStored(view.getMetadata());
                List<AnnotationInterpreter> lifCharOffsetObjectList = lifLayer.processAnnotations(view.getAnnotations());
                if(!lifLayer.isLayerValid()){
                     throw new LifException("The annotation layer is not valid!!"); 
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
            AnnotationLayerFinderStored lifLayer = new AnnotationLayerFinderStored(view.getMetadata());
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
        return modelValidity;
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

