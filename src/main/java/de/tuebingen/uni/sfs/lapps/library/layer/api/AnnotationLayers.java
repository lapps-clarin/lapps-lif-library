/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.layer.api;

import de.tuebingen.uni.sfs.lapps.library.exception.LifException;

/**
 *
 * @author felahi
 */
public interface AnnotationLayers {

    public boolean isLanguage() throws LifException ;

    public boolean isTextLayer() throws LifException ;

    public boolean isTokenLayer() throws LifException;

    public boolean isSenetenceLayer() throws LifException;

    public boolean isPosLayer() throws LifException;

    public boolean isLemmaLayer() throws LifException;
    
    public boolean isNamedEntityLayer() throws LifException;

    public boolean isDependencyLayer() throws LifException;

    public boolean isConstituentLayer() throws LifException;

    public boolean isTokenPosLayer() throws LifException;
    
    public boolean isCorferenceLayer() throws LifException;
}
