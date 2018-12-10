/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifNameEntity;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.lappsgrid.vocabulary.Features;
import static org.lappsgrid.vocabulary.Features.Token.LEMMA;
import static org.lappsgrid.vocabulary.Features.Token.POS;
import static org.lappsgrid.vocabulary.Features.Token.WORD;

/**
 *
 * @author felahi
 */
public class LifNameEntityStored extends LifCharOffsetStored implements LifNameEntity {

    private Map<Object, Object> features = new HashMap<Object, Object>();
    private String defaultCategory = null;

    public LifNameEntityStored(LifAnnotationMapper annotation) {
        super(annotation);
        this.setFeatures(annotation.getFeatures());
        // this is a temporary code since the lapps toor produces both old and new annotations.
        //this code works for old annotations
        Path urlPath = Paths.get(annotation.getUrl());
        Path lastSegment = urlPath.getName(urlPath.getNameCount() - 1);
        this.defaultCategory = lastSegment.toString();
    }

    public void setFeatures(Map<Object, Object> features) {
        this.features = features;
    }

    @Override
    public String getWord() {
        return (String) this.features.get(WORD);
    }

    @Override
    public String getPos() {
        return (String) this.features.get(POS);
    }

    @Override
    public String getLemma() {
        return (String) this.features.get(LEMMA);
    }

    @Override
    public String getCategory() {
        String category = (String) this.features.get(Features.NamedEntity.CATEGORY);
        if (category != null) {
            return category;
        }
        return this.defaultCategory;
    }

    @Override
    public Long getStart() {
        return super.getStart();
    }

    @Override
    public Long getEnd() {
        return super.getEnd();
    }

    public Map<Object, Object> getFeatures() {
        return features;
    }

}
