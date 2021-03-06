/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import java.util.HashMap;
import java.util.Map;
import org.lappsgrid.serialization.lif.Annotation;

/**
 *
 * @author felahi
 */
public class LifAnnotationMapper implements Comparable<LifAnnotationMapper> {

    private long start = -1;
    private long end = -1;
    private String id = null;
    private String url = null;
    private String label = null;
    private Map<Object, Object> features = new HashMap<Object, Object>();
    public static Map<String, LifAnnotationMapper> elementIdMapper = new HashMap<String, LifAnnotationMapper>();

    public LifAnnotationMapper(Annotation annotation) {
        if (annotation.getStart() != null) {
            this.start = annotation.getStart();
        }
        if (annotation.getEnd() != null) {
            this.end = annotation.getEnd();
        }
        if (annotation.getAtType() != null) {
            this.url = annotation.getAtType();
        }
        this.id = annotation.getId();
        this.label = annotation.getLabel();
        this.features = annotation.getFeatures();
        elementIdMapper.put(annotation.getId(), this);
    }

    public int compareTo(LifAnnotationMapper o) {
        if (start == o.start) {
            return 0;
        } else if (start > o.start) {
            return 1;
        } else {
            return -1;
        }
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public boolean isProcessed() {
        return false;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Map<Object, Object> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return  "url=" + url + ", label=" + label + ", features=" + features + '}';
    }

}
