/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.api;

import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifTokenLayer {
    public List<LifTokenPosLemma> getTokenList(); 

    public boolean isTokenLayer();
    
    public boolean isPosLayer();
    
     public boolean isLemmaLayer();
}
