/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.api.profiler;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifConstituentParser;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifDependencyParser;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifReferenceLayer;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifTokenLayer;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
/**
 *
 * @author felahi
 */
public interface LifFormat extends ValidityChecker {

    public String getLanguage() throws LifException;

    public String getText() throws LifException;

    public String getFileString();

    public LifTokenLayer getLifTokenLayer();

    public LifSentenceLayer getLifSentenceLayer();

    public LifNameEntityLayer getLifNameEntityLayer();

    public LifDependencyParser getLifDependencyParser();

    public LifConstituentParser getLifConstituentParser();

    public LifReferenceLayer getLifRefererenceLayer();
}
