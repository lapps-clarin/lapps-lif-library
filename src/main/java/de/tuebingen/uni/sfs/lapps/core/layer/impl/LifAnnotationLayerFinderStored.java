package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayers;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lappsgrid.discriminator.Discriminators;

public class LifAnnotationLayerFinderStored implements AnnotationLayers {

    private LifAnnotationProcess givenDataModel = null;
    private String LANG_EN = "en";
    private String text = null;
    private List<String> layers = new ArrayList<String>();

    public LifAnnotationLayerFinderStored() throws LifException {
    }

    public LifAnnotationLayerFinderStored(LifAnnotationProcess lifDataModel) throws LifException {
        this.convertModel(lifDataModel);
    }

    public void convertModel(LifAnnotationProcess lifDataModel) throws LifException, LifException {
        givenDataModel = lifDataModel;
        try {
            findAnnotationLayers();
        } catch (LifException lifExp) {
            Logger.getLogger(LifAnnotationLayerFinderStored.class.getName()).log(Level.SEVERE, null, lifExp);
        }
    }

    public void findAnnotationLayers() throws LifException, LifException {
        for (Integer layerIndex : givenDataModel.getSortedLayer()) {
            AnnotationLayerFinder lifLayer = givenDataModel.getIndexAnnotationLayer(layerIndex);
            layers.add(lifLayer.getLayer());
        }
        if (layers.contains(Discriminators.Uri.POS)) {
            layers.add(Discriminators.Uri.TOKEN);
        }
    }

    public String getLanguage() {
        /*if (givenDataModel.getLanguage() != null) {
            return givenDataModel.getLanguage();
        }*/
        return LANG_EN;
    }

    public LifAnnotationProcess getGivenDataModel() {
        return givenDataModel;
    }

    public String getText() {
        return givenDataModel.getText();
    }

    public List<String> getLayers() {
        return layers;
    }

    @Override
    public boolean isLanguage() throws LifException {
        try {
            return true;
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
    }

    @Override
    public boolean isTextLayer() throws LifException {
        try {
            if (givenDataModel.getText() != null) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isTokenLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.TOKEN)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isPosLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.POS)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isLemmaLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.LEMMA)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isDependencyLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.DEPENDENCY_STRUCTURE)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isConstituentLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.PHRASE_STRUCTURE)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean isSenetenceLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.SENTENCE)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isTokenPosLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.POS)) {
                layers.add(Discriminators.Uri.TOKEN);
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isCorferenceLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.COREF)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

    @Override
    public boolean isNamedEntityLayer() throws LifException {
        try {
            if (layers.contains(Discriminators.Uri.NE)) {
                return true;
            }
        } catch (Exception ex) {
            throw new LifException("LIF file is not valid!");
        }
        return false;
    }

}
