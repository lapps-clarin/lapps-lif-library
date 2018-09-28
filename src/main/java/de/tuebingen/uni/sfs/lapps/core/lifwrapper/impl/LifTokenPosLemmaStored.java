/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifTokenPosLemma;
import de.tuebingen.uni.sfs.lapps.utils.LifAnnotationMapper;
import java.util.HashMap;
import java.util.Map;
import static org.lappsgrid.vocabulary.Features.Token.LEMMA;
import static org.lappsgrid.vocabulary.Features.Token.POS;
import static org.lappsgrid.vocabulary.Features.Token.WORD;

/**
 *
 * @author felahi
 */
public class LifTokenPosLemmaStored extends LifCharOffsetStored implements LifTokenPosLemma {

    private Map<Object, Object> features = new HashMap<Object, Object>();

    public LifTokenPosLemmaStored(LifAnnotationMapper annotationInterpreter) {
        super(annotationInterpreter);
        this.features = annotationInterpreter.getFeatures();
    }

    public String getWord() {
        return (String) this.features.get(WORD);
    }

    public String getPos() {
        return (String) this.features.get(POS);
    }

    public String getLemma() {
        return (String) this.features.get(LEMMA);
    }

    @Override
    public Map<Object, Object> getFeatures() {
        return features;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public Long getStart() {
        return super.getStart();
    }

    @Override
    public Long getEnd() {
        return super.getEnd();
    }

}
