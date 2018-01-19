/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.layer.api;

import de.tuebingen.uni.sfs.lapps.exceptions.LifException;

/**
 *
 * @author This interface finds the annotation layer from a file
 */
public interface AnnotationLayerFinder {

    public boolean isLayerExists() throws LifException;

    public boolean isToolExists(String tool) throws LifException;

    public String getLayer();

    public String getTool() throws LifException;

    public String getProducer() throws LifException;

    public String getTagSetName(String tool) throws LifException;

    public String getVocabularies(String tool, String key) throws LifException;

    public void getLayerFromSingleUrl();

    public void getLayerFromMultipleUrls();

}
