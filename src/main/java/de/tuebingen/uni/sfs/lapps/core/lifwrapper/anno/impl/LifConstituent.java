/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl;

import de.tuebingen.uni.sfs.lapps.constants.LifConstants;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.util.ArrayList;
import java.util.List;
import org.lappsgrid.discriminator.Discriminators;

import org.lappsgrid.vocabulary.Features;

/**
 *
 * @author felahi
 */
public class LifConstituent {

    private List<String> childrenList = new ArrayList<String>();
    private String constituentId = null;
    private String catFunction = null;
    private String parentId = null;
    private boolean root = false;

    //currently there is inconsistency in constituent annotation in lif
    // the field lable sometimes inside feature dictionary and sometime outside
    //temporarily both are considered now
    public LifConstituent(LifAnnotationMapper constAnnotationInterpreter) throws LifException {
        try {
            this.constituentId = constAnnotationInterpreter.getId();
            if (constAnnotationInterpreter.getLabel() != null) {
                this.catFunction = constAnnotationInterpreter.getLabel();
            } else {
                this.catFunction = (String) constAnnotationInterpreter.getFeatures().get(Features.Constituent.LABEL);
            }
            this.childrenList = (List<String>) constAnnotationInterpreter.getFeatures().get(Features.Constituent.CHILDREN);
            this.parentId = (String) constAnnotationInterpreter.getFeatures().get(Features.Constituent.PARENT);

            if (catFunction.contains(LifConstants.Annotation.TreeSets.ROOT)) {
                root = true;
            }
        } catch (Exception ex) {
            throw new LifException("INVALID LIF: the constitution annotation is not correct!!");

        }
    }

    public List<String> getChildrenList() {
        return childrenList;
    }

    public String getConstituentId() {
        return constituentId;
    }

    public String getCatFunction() {
        return catFunction;
    }

    public String getParentId() {
        return parentId;
    }

    public boolean isRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "LifConstituent{" + "childrenList=" + childrenList + ", constituentId=" + constituentId + ", catFunction=" + catFunction + ", parentId=" + parentId + ", root=" + root + '}';
    }
}
