/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifMarkable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lappsgrid.vocabulary.Features;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifMarkableStored implements LifMarkable {

    private Map<Object, Object> features = new HashMap<Object, Object>();

    public LifMarkableStored(Map<Object, Object> features) {
        if (!features.isEmpty()) {
            this.setFeatures(features);
        }
    }

    public void setFeatures(Map<Object, Object> features) {
        this.features = features;
    }

    public List<String> getTargets() {
        return (List<String>) this.features.get(Features.Markable.TARGETS);
    }

}
