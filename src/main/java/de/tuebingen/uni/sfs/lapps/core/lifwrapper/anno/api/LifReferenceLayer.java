/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api;

import java.util.Map;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifReferenceLayer{

    public Map<String, LifReference> getCorferenceAnnotations();

    public Map<String, LifMarkable> getMarkableAnnotations();

}
