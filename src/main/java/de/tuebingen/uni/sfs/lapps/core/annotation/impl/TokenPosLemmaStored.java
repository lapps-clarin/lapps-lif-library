/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.impl;

import de.tuebingen.uni.sfs.lapps.core.annotation.api.LifTokenPosLemma;
import java.util.HashMap;
import java.util.Map;
import static org.lappsgrid.vocabulary.Features.Token.LEMMA;
import static org.lappsgrid.vocabulary.Features.Token.POS;
import static org.lappsgrid.vocabulary.Features.Token.WORD;

/**
 *
 * @author felahi
 */
public class TokenPosLemmaStored implements LifTokenPosLemma {

    private Map<Object, Object> features = new HashMap<Object, Object>();

    public TokenPosLemmaStored(Map<Object, Object> features) {
        if(features.containsKey(WORD)||features.containsKey(POS)||features.containsKey(LEMMA))
         this.setFeatures(features);
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

    public Map<Object, Object> getFeatures() {
        return features;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getStart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getEnd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
