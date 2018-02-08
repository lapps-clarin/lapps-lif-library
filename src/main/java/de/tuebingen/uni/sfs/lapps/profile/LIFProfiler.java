/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile;

import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.List;
import java.util.Vector;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LIFProfiler {

    public String getLanguage() throws LifException;

    public String getText() throws LifException;

    public String getFileString();

    public Container getLifContainer() throws LifException;
    
    public LIFViewProfile getLifViewProfile();

    public boolean isValid();

}
