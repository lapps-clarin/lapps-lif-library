/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile;

import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifToolProducerStored;
import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class LIFProfilerImpl extends LIFTopLevelProfiler implements LIFProfile {

    private Map<Integer, List<AnnotationInterpreter>> annotationLayerData = new HashMap<Integer, List<AnnotationInterpreter>>();
    private Map<Integer, AnnotationLayerFinder> indexAnnotationLayer = new HashMap<Integer, AnnotationLayerFinder>();
    private Vector<Integer> sortedLayer = new Vector<Integer>();
    private LifValidityCheckerStored lifValidityCheck = new LifValidityCheckerStored();
    private List<View> views=new ArrayList<View>();

    public LIFProfilerImpl(InputStream is) throws LifException, IOException, JSONValidityException{
         super(is);
         this.views=super.getViews();
         extractAndSortViews();
    }

    public void extractAndSortViews() throws LifException {
        processViews();
        sortedLayer = new Vector(this.indexAnnotationLayer.keySet());
        Collections.sort(sortedLayer);
    }

    private void processViews() throws LifException {
        Integer index = 0;

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

    @Override
    public Map<Integer, AnnotationLayerFinder> getIndexAnnotationLayer() {
        return indexAnnotationLayer;
    }

    @Override
    public AnnotationLayerFinder getIndexAnnotationLayer(Integer index) {
        return this.indexAnnotationLayer.get(index);
    }

    @Override
    public Vector<Integer> getSortedLayer() {
        return sortedLayer;
    }

    @Override
    public String toString() {
        return "DataModelLif{" + "indexAnnotationLayer=" + indexAnnotationLayer + ", sortedLayer=" + sortedLayer + '}';
    }

    @Override
    public String getLanguage() throws LifException {
        return super.getLifContainer().getContainer().getLanguage();
    }

    @Override
    public String getText() throws LifException {
        return super.getLifContainer().getContainer().getText();
    }

    @Override
    public String getFileString() {
       return super.getFileString();
    }


    @Override
    public boolean isValid() {
        return true;
    }

    

}
