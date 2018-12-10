/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifDependencyParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.utils.LifAnnotationMapper;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import org.lappsgrid.discriminator.Discriminators;
import de.tuebingen.uni.sfs.lapps.core.converter.api.ErrorMessage;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.api.LifConstants;
import java.util.HashMap;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifDependencyParserStored implements LifDependencyParser {

    private Map<Long, Boolean> roots = new HashMap<Long, Boolean>();
    private Map<Long, List<LifDependencyInfo>> dependencyParses = new TreeMap<Long, List<LifDependencyInfo>>();
    private LifSentenceLayer lifSentenceLayer = null;
    private List<LifAnnotationMapper> sentenceList = new ArrayList<LifAnnotationMapper>();
    private List<LifDependencyInfo> dependencyEntities = new ArrayList<LifDependencyInfo>();

    public LifDependencyParserStored(List<LifAnnotationMapper> lifAnnotationList) throws LifException {
        extractParses(lifAnnotationList);
    }

    public void extractParses(List<LifAnnotationMapper> lifAnnotationList) throws LifException {
        Long parseIndex = new Long(0);
        for (LifAnnotationMapper annotationObject : lifAnnotationList) {
            if (annotationObject.getUrl().equals(Discriminators.Uri.DEPENDENCY_STRUCTURE)) {
                parseIndex = parseIndex + 1;
                try {
                    this.seperateStructures(annotationObject, parseIndex);
                    if (annotationObject.getStart() != -1 || annotationObject.getEnd() != -1) {
                        sentenceList.add(annotationObject);
                    }
                    this.dependencyParses.put(parseIndex, dependencyEntities);
                } catch (Exception ex) {
                    throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR_DEPENDENCY_PARSER_MISSING_ANNOATAIONS);
                }
            }
        }
        if (!sentenceList.isEmpty()) {
            lifSentenceLayer = new LifSentenceLayerStored(sentenceList);
        }

    }

    public void seperateStructures(LifAnnotationMapper annotationObject, Long parseIndex) throws LifException {
        dependencyEntities = new ArrayList<LifDependencyInfo>();
        Map<Object, Object> dependencyStructureFeatures = LifAnnotationMapper.elementIdMapper.get(annotationObject.getId()).getFeatures();
        if (!dependencyStructureFeatures.isEmpty()) {
            LifDependencyStructure lifDepStructureFeature = new LifDependencyStructure(dependencyStructureFeatures);
            List<String> dependencies = lifDepStructureFeature.getDependencies();
            for (String dependencyId : dependencies) {
                try {
                    if (LifAnnotationMapper.elementIdMapper.containsKey(dependencyId)) {
                        LifAnnotationMapper depAnnotationInterpreter = LifAnnotationMapper.elementIdMapper.get(dependencyId);
                        Map<Object, Object> dependencyFeatures = depAnnotationInterpreter.getFeatures();
                        LifDependency lifDepFeature = new LifDependency(dependencyFeatures, depAnnotationInterpreter.getLabel());
                        LifDependencyInfo dependencyTcfEntity = new LifDependencyInfo(lifDepFeature.getDependencyFunction());
                        if (lifDepFeature.isDependantExist()) {
                            dependencyTcfEntity.setDepIDs(lifDepFeature.getDependent());
                        }
                        if (lifDepFeature.isGovonorExist()) {
                            dependencyTcfEntity.setGovIDs(lifDepFeature.getGovernor());
                        }
                        if (lifDepFeature.getDependencyFunction().contains(LifConstants.Annotation.TreeSets.ROOT)) {
                            dependencyTcfEntity.setRoot(Boolean.TRUE);
                            roots.put(parseIndex, Boolean.TRUE);
                        }
                        dependencyEntities.add(dependencyTcfEntity);

                    } else {
                        throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR_DEPENDENCY_PARSER_TOKEN_MISSING);
                    }
                } catch (Exception ex) {
                    throw new LifException(ErrorMessage.Lif.MESSAGE_INVALID_LIF);
                }

            }
        }

    }

    @Override
    public Vector<Long> getParseIndexs() throws LifException {
        Vector<Long> parseIndexsSort = new Vector<Long>(this.dependencyParses.keySet());
        Collections.sort(parseIndexsSort);
        return parseIndexsSort;
    }

    @Override
    public LifSentenceLayer getSentenceLayer() throws LifException {
        return lifSentenceLayer;
    }

    @Override
    public List<LifDependencyInfo> getDependencyEntities(Long parseIndex) throws LifException {
        if (this.dependencyParses.containsKey(parseIndex)) {
            return this.dependencyParses.get(parseIndex);
        } else {
            throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR_DEPENDENCIES_MISSING + parseIndex);
        }
    }

    @Override
    public Boolean getRoot(Long parseIndex) throws LifException {
        return roots.get(parseIndex);
    }

}
