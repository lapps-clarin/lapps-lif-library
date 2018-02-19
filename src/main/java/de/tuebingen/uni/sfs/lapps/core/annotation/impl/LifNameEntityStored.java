/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.impl;

import de.tuebingen.uni.sfs.lapps.core.annotation.api.LifNameEntity;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
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

    public LifNameEntityStored(AnnotationInterpreter annotation) {
        super(annotation);
        this.setFeatures(annotation.getFeatures());
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
        return (String) this.features.get(Features.NamedEntity.CATEGORY);
    }

    @Override
    public long getStart() {
        return super.getStart();
    }

    @Override
    public long getEnd() {
        return super.getEnd();
    }

    public Map<Object, Object> getFeatures() {
        return features;
    }

}
