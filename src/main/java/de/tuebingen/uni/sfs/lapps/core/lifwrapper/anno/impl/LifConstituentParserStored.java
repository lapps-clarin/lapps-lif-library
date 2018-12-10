/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifConstituentParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifSentenceLayer;
import java.util.ArrayList;
import java.util.TreeSet;
import org.lappsgrid.discriminator.Discriminators;
import de.tuebingen.uni.sfs.lapps.constants.ErrorMessage;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifConstituentParserStored implements LifConstituentParser {

    private Map<Long, LifConstituent> roots = new HashMap<Long, LifConstituent>();
    private List<LifAnnotationMapper> sentenceList = new ArrayList<LifAnnotationMapper>();
    private LifSentenceLayer lifSentenceLayer = null;
    private Map<Long, Map<String, LifConstituent>> constituentParses = new HashMap<Long, Map<String, LifConstituent>>();

    public LifConstituentParserStored(List<LifAnnotationMapper> lifAnnotations) throws LifException {
        extract(lifAnnotations);
    }

    private void extract(List<LifAnnotationMapper> lifAnnotationList) throws LifException {
        Long parseIndex = new Long(0);

        try {
            for (LifAnnotationMapper parseAnnotation : lifAnnotationList) {
                if (parseAnnotation.getUrl().equals(Discriminators.Uri.PHRASE_STRUCTURE)) {
                    parseIndex = parseIndex + 1;
                    if (parseAnnotation.getStart() != -1 || parseAnnotation.getEnd() != -1) {
                        sentenceList.add(parseAnnotation);
                    }
                    Map<String, LifConstituent> idConstituents = new HashMap<String, LifConstituent>();
                    Map<Object, Object> parseFeatures = LifAnnotationMapper.elementIdMapper.get(parseAnnotation.getId()).getFeatures();
                    if (!parseFeatures.isEmpty()) {
                        LifConstituentStructure lifConstituentStructure = new LifConstituentStructure(parseFeatures);
                        for (String constituentId : lifConstituentStructure.getConstituents()) {
                            LifAnnotationMapper constAnnotation = LifAnnotationMapper.elementIdMapper.get(constituentId);
                            if (constAnnotation.getUrl().equals(Discriminators.Uri.CONSTITUENT)) {
                                LifConstituent lifConstituent = new LifConstituent(constAnnotation);
                                idConstituents.put(constituentId, lifConstituent);
                                if (lifConstituent.isRoot()) {
                                    roots.put(parseIndex, lifConstituent);
                                }
                            }
                        }
                        constituentParses.put(parseIndex, idConstituents);
                    }
                }
            }
        } catch (NullPointerException ex) {
            throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR_CONSTITUENT_PARSER_MISSING_ANNOATAIONS);
        } catch (Exception ex) {
            throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR);
        }
        if (!sentenceList.isEmpty()) {
            lifSentenceLayer = new LifSentenceLayerStored(sentenceList);
        }
    }

    @Override
    public TreeSet<Long> getParseIndexs() throws LifException {
        return new TreeSet<Long>(this.constituentParses.keySet());
    }

    @Override
    public LifConstituent getRoot(Long parseIndex) throws LifException {
        return roots.get(parseIndex);
    }

    @Override
    public LifSentenceLayer getSentenceLayer() throws LifException {
        return lifSentenceLayer;
    }

    @Override
    public Map<String, LifConstituent> getConstituentEntities(Long parseIndex) throws LifException {
        if (this.constituentParses.containsKey(parseIndex)) {
            return this.constituentParses.get(parseIndex);
        } else {
            throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR_CONSTITUENT_PARSER_MISSING_ANNOATAIONS);
        }
    }

}
