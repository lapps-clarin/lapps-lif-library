/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifConstituentParser;
import java.util.TreeSet;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifConstituentParserStored implements LifConstituentParser {

    private Map<Long, LifConstituent> roots = new HashMap<Long, LifConstituent>();
    private Map<Long, Map<String, LifConstituent>> constituentParses = new HashMap<Long, Map<String, LifConstituent>>();

    public LifConstituentParserStored(List<AnnotationInterpreter> lifAnnotations) throws LifException {
        try {
            extract(lifAnnotations);
        } catch (LifException exp) {
            throw new LifException(exp.getMessage());
        }
    }

    private void extract(List<AnnotationInterpreter> lifAnnotationList) throws LifException {
        Long parseIndex = new Long(0);
        for (AnnotationInterpreter parseAnnotation : lifAnnotationList) {
            parseIndex = parseIndex + 1;
            Map<String, LifConstituent> idConstituents = new HashMap<String, LifConstituent>();
            Map<Object, Object> parseFeatures = AnnotationInterpreter.elementIdMapper.get(parseAnnotation.getId()).getFeatures();
            if (!parseFeatures.isEmpty()) {
                LifConstituentStructure lifConstituentStructure = new LifConstituentStructure(parseFeatures);
                for (String constituentId : lifConstituentStructure.getConstituents()) {
                    AnnotationInterpreter constAnnotation = AnnotationInterpreter.elementIdMapper.get(constituentId);
                    LifConstituent lifConstituent = new LifConstituent(constAnnotation);
                    idConstituents.put(constituentId, lifConstituent);
                    if (lifConstituent.isRoot()) {
                        roots.put(parseIndex, lifConstituent);
                    }
                   
                }
                constituentParses.put(parseIndex, idConstituents);
            }
        }

    }

    public LifConstituent getRoot(Long parseIndex) throws NullPointerException {
        return roots.get(parseIndex);
    }

    public Map<String, LifConstituent> getConstituentEntities(Long parseIndex) throws LifException {
        if (this.constituentParses.containsKey(parseIndex)) {
            return this.constituentParses.get(parseIndex);
        } else {
            throw new LifException("No Constituent list found for the tree in Parse" + parseIndex);
        }
    }

    public TreeSet<Long> getParseIndexs() {
        return new TreeSet<Long>(this.constituentParses.keySet());
    }

}
