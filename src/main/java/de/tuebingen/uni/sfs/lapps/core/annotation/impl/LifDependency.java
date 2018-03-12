/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.annotation.impl;

import java.util.Map;
import org.lappsgrid.vocabulary.Features.Dependency;

/**
 *
 * @author felahi
 */
public class LifDependency  {

    public String governor = null;
    public String dependent = null;
    public String dependency_function = null;

    public LifDependency(Map<Object, Object> features, String dependency_function) {
        //temporary solution of null string problem of lif file. This code will be removed when lIF become stable
        if (features.get(Dependency.GOVERNOR) != null&& !features.get(Dependency.GOVERNOR).toString().contains("null")) {
            this.governor = (String) features.get(Dependency.GOVERNOR).toString().trim();
        }
        if (features.get(Dependency.DEPENDENT) != null) {
            this.dependent = (String) features.get(Dependency.DEPENDENT).toString().trim();
        }
        if (dependency_function != null) {
            this.dependency_function = dependency_function;
        }

    }

    public boolean isGovonorExist() {
        if (governor != null) {
            return true;
        }
        return false;
    }

    public boolean isDependantExist() {
        if (dependent != null) {
            return true;
        }
        return false;
    }

    public String getGovernor() {
        return governor;
    }

    public String getDependent() {
        return dependent;
    }

    public String getDependency_function() {
        return dependency_function;
    }

}
