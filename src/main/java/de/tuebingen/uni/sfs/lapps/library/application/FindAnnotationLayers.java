/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

/**
 *
 * @author felahi
 */
public interface FindAnnotationLayers {

    public boolean isLanguage();

    public boolean isTextLayer();

    public boolean isTokenLayer();

    public boolean isSenetenceLayer();

    public boolean isPosLayer();

    public boolean isLemmaLayer();

    public boolean isDependencyLayer();

    public boolean isConstituentLayer();

}
