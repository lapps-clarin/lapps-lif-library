/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.api;

import de.tuebingen.uni.sfs.lapps.utils.LifAnnotationMapper;
import java.util.List;

/**
 *
 * @author felahi
 */
public interface LifSentenceLayer {

    public List<LifAnnotationMapper> getSentenceList();

}
