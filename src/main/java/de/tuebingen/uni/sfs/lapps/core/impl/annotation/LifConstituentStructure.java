/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.impl.annotation;

import de.tuebingen.uni.sfs.lapps.constants.LifConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author felahi
 */
public class LifConstituentStructure {

    private String text = null;
    private String tree = null;
    private List<String> constituents = new ArrayList<String>();

    public LifConstituentStructure(Map<Object, Object> features) throws NullPointerException {
        this.text = (String) features.get(Discriminators.Alias.SENTENCE);
        this.tree = (String) features.get(LifConstants.Annotation.DiscriminitorsExtended.PENN_TREE);
         System.out.println(features.get(LifConstants.Annotation.DiscriminitorsExtended.CONSTITUENTS));
        this.constituents = (List) features.get(LifConstants.Annotation.DiscriminitorsExtended.CONSTITUENTS);
               
    }

    public String getText() {
        return text;
    }

    public List<String> getConstituents() {
        return constituents;
    }

    public String getTree() {
        return tree;
    }

}
