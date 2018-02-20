/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.api;

import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifTokenLayer {
    public List<AnnotationInterpreter> getTokenList(); 
}
