package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayers;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lappsgrid.discriminator.Discriminators;
import de.tuebingen.uni.sfs.lapps.profile.LIFProfile;

public class LifAnnotationLayerFinderStored implements AnnotationLayers {

    private LIFProfile lifProfile = null;
    private String LANG_EN = "en";
    private String text = null;
    private List<String> layers = new ArrayList<String>();

    public LifAnnotationLayerFinderStored() throws LifException {
    }

    public LifAnnotationLayerFinderStored(LIFProfile profile) throws LifException {
        this.findLayers(profile);
    }

    public void findLayers(LIFProfile profile) throws LifException, LifException {
        this.lifProfile = profile;
        try {
            findAnnotationLayers();
        } catch (LifException lifExp) {
            Logger.getLogger(LifAnnotationLayerFinderStored.class.getName()).log(Level.SEVERE, null, lifExp);
        }
    }

    public void findAnnotationLayers() throws LifException, LifException {
        for (Integer layerIndex : lifProfile.getSortedLayer()) {
            AnnotationLayerFinder lifLayer = lifProfile.getIndexAnnotationLayer(layerIndex);
            layers.add(lifLayer.getLayer());
        }
        if (layers.contains(Discriminators.Uri.POS)) {
            layers.add(Discriminators.Uri.TOKEN);
        }
    }

    public String getLanguage() {
        /*if (lifProfile.getLanguage() != null) {
            return lifProfile.getLanguage();
        }*/
        return LANG_EN;
    }

    public LIFProfile getGivenDataModel() {
        return lifProfile;
    }

    public String getText() throws LifException {
        return lifProfile.getText();
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
            if (lifProfile.getText() != null) {
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
