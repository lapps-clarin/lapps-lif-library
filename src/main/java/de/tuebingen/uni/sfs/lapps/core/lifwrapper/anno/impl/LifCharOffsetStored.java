/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifCharOffSet;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifCharOffsetStored implements LifCharOffSet {

    private Long start = null;
    private Long end = null;
    private String id = null;

    public LifCharOffsetStored(LifAnnotationMapper annotation) {
        this.start = annotation.getStart();
        this.end = annotation.getEnd();
        this.id = annotation.getId();
    }

    @Override
    public Long getStart() {
        return start;
    }

    @Override
    public Long getEnd() {
        return end;
    }

    @Override
    public String getId() {
        return id;
    }

}
