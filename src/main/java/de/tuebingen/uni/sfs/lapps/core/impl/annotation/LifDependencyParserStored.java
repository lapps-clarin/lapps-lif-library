/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifDependencyParser;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import de.tuebingen.uni.sfs.lapps.utils.DependencyEntityInfo;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author felahi
 */
public class LifDependencyParserStored implements LifDependencyParser {

    private Map<Long, List<DependencyEntityInfo>> dependencyParses = new TreeMap<Long, List<DependencyEntityInfo>>();
    private LifSentenceLayer lifSentenceLayer = null;
    private List<AnnotationInterpreter> sentenceList = new ArrayList<AnnotationInterpreter>();
    private List<DependencyEntityInfo> dependencyEntities = new ArrayList<DependencyEntityInfo>();

    public LifDependencyParserStored(List<AnnotationInterpreter> lifAnnotationList) throws LifException {
        try {
            extractParses(lifAnnotationList);
        } catch (Exception ex) {
            Logger.getLogger(LifDependencyParserStored.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void extractParses(List<AnnotationInterpreter> lifAnnotationList) throws LifException {  
        Long parseIndex = new Long(0);
        for (AnnotationInterpreter annotationObject : lifAnnotationList) {
            if (annotationObject.getUrl().equals(Discriminators.Uri.DEPENDENCY_STRUCTURE)) {
                parseIndex = parseIndex + 1;
                try {
                    this.seperateStructures(annotationObject);
                    if (annotationObject.getStart() != -1 || annotationObject.getEnd() != -1) {
                        sentenceList.add(annotationObject);
                    }
                    this.dependencyParses.put(parseIndex, dependencyEntities);
                } catch (Exception ex) {
                    Logger.getLogger(LifDependencyParserStored.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!sentenceList.isEmpty()) {
            lifSentenceLayer = new LifSentenceLayerStored(sentenceList);
        }
            
    }

    public void seperateStructures(AnnotationInterpreter annotationObject) throws LifException {
        dependencyEntities = new ArrayList<DependencyEntityInfo>();
        Map<Object, Object> dependencyStructureFeatures = AnnotationInterpreter.elementIdMapper.get(annotationObject.getId()).getFeatures();
        if (!dependencyStructureFeatures.isEmpty()) {
            LifDependencyStructure lifDepStructureFeature = new LifDependencyStructure(dependencyStructureFeatures);
            List<String> dependencies = lifDepStructureFeature.getDependencies();
            for (String dependencyId : dependencies) {
                try {
                    if (AnnotationInterpreter.elementIdMapper.containsKey(dependencyId)) {
                        AnnotationInterpreter depAnnotationInterpreter = AnnotationInterpreter.elementIdMapper.get(dependencyId);
                        Map<Object, Object> dependencyFeatures = depAnnotationInterpreter.getFeatures();
                        LifDependency lifDepFeature = new LifDependency(dependencyFeatures, depAnnotationInterpreter.getLabel());
                        DependencyEntityInfo dependencyTcfEntity = new DependencyEntityInfo(lifDepFeature.getDependency_function());
                        if (lifDepFeature.isDependantExist()) {
                            dependencyTcfEntity.setDepIDs(lifDepFeature.getDependent());
                        }
                        if (lifDepFeature.isGovonorExist()) {
                            dependencyTcfEntity.setGovIDs(lifDepFeature.getGovernor());
                        }
                        dependencyEntities.add(dependencyTcfEntity);

                    } else {
                        throw new Exception("no lif token found from dependency id!!");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LifDependencyParserStored.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    public Map<Long, List<DependencyEntityInfo>> getDependencyEntities() {
        return dependencyParses;
    }

    public List<DependencyEntityInfo> getDependencyEntities(Long parseIndex) throws LifException {
        if (this.dependencyParses.containsKey(parseIndex)) {
            return this.dependencyParses.get(parseIndex);
        } else {
            throw new LifException("No Dependency list found for in Parse" + parseIndex);
        }
    }

    public Vector<Long> getParseIndexs() {
        Vector<Long> parseIndexsSort = new Vector<Long>(this.dependencyParses.keySet());
        Collections.sort(parseIndexsSort);
        return parseIndexsSort;
    }

    @Override
    public LifSentenceLayer getSentenceLayer() {
        return lifSentenceLayer;
    }

}
