/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.api;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl.LifConstituent;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifConstituentParser {

    public TreeSet<Long> getParseIndexs()throws LifException;

    public LifConstituent getRoot(Long parseIndex) throws LifException;

    public LifSentenceLayer getSentenceLayer() throws LifException;

    public Map<String, LifConstituent> getConstituentEntities(Long parseIndex) throws LifException;
}
