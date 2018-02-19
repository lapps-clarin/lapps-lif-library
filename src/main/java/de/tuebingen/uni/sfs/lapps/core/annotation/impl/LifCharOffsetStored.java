/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.impl;

import de.tuebingen.uni.sfs.lapps.core.annotation.api.LifCharOffSet;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifCharOffsetStored implements LifCharOffSet {

    private long start = 0;
    private long end = 0;
    private String id = null;

    public LifCharOffsetStored(AnnotationInterpreter annotation) {
        this.start = annotation.getStart();
        this.end = annotation.getEnd();
        this.id = annotation.getId();
    }

    @Override
    public long getStart() {
        return start;
    }

    @Override
    public long getEnd() {
        return end;
    }

    @Override
    public String getId() {
        return id;
    }

}
