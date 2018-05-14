/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile.api;

import de.tuebingen.uni.sfs.lapps.core.layer.impl.LIFAnnotationLayers;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
/**
 *
 * @author felahi
 */
public interface LifProfile extends ValidityChecker {

    public String getLanguage() throws LifException;

    public String getText() throws LifException;

    public String getFileString();

    public LIFAnnotationLayers getLifAnnotationLayers();
}
