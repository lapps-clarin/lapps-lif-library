package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.library.annotation.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.library.model.DataModelLif;
import de.tuebingen.uni.sfs.lapps.library.exception.VocabularyMappingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lappsgrid.discriminator.Discriminators;

public class AnnotationLayersStored implements AnnotationLayers {

    private DataModelLif givenDataModel = null;
    private String LANG_EN = "en";
    private String text = null;
    private List<String> layers = new ArrayList<String>();

    public AnnotationLayersStored() throws VocabularyMappingException {
    }
    
    public AnnotationLayersStored(DataModelLif lifDataModel) throws VocabularyMappingException, Exception {
        this.convertModel(lifDataModel);
    }

    public void convertModel(DataModelLif lifDataModel) throws Exception {
        givenDataModel = lifDataModel;
        try {
            findAnnotationLayers();
        } catch (VocabularyMappingException conExp) {
            Logger.getLogger(AnnotationLayersStored.class.getName()).log(Level.SEVERE, null, conExp);
        } catch (Exception vocExp) {
            Logger.getLogger(AnnotationLayersStored.class.getName()).log(Level.SEVERE, null, vocExp);
        }
    }

    protected void findAnnotationLayers() throws VocabularyMappingException, Exception {
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

    public String getText() {
        return givenDataModel.getText();
    }

    public List<String> getLayers() {
        return layers;
    }

    @Override
    public boolean isLanguage() {
        return true;
    }

    @Override
    public boolean isTextLayer() {
        if (givenDataModel.getText() != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTokenLayer() {
        if (layers.contains(Discriminators.Uri.TOKEN)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPosLayer() {
        if (layers.contains(Discriminators.Uri.POS)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLemmaLayer() {
        if (layers.contains(Discriminators.Uri.LEMMA)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDependencyLayer() {
        if (layers.contains(Discriminators.Uri.DEPENDENCY)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isConstituentLayer() {
        if (layers.contains(Discriminators.Uri.CONSTITUENT)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSenetenceLayer() {
        if (layers.contains(Discriminators.Uri.SENTENCE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTokenPosLayer() {
        if (layers.contains(Discriminators.Uri.POS)) {
            layers.add(Discriminators.Uri.TOKEN);
            return true;
        }
        return false;
    }

}
