/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.utils;

/**
 *
 * @author felahi
 */
public class DependencyEntityInfo {

    private String  depIDs = null;
    private String govIDs = null;
    private String func = null;

    public DependencyEntityInfo(String func) {
        this.func = func;
    }

    public DependencyEntityInfo(String depIDs, String govIDs, String func) {
        this.depIDs = depIDs;
        this.govIDs = govIDs;
        this.func = func;
    }

    public String getDepIDs() {
        return depIDs;
    }

    public String getGovIDs() {
        return govIDs;
    }

    public String getFunc() {
        return func;
    }

    public void setDepIDs(String depIDs) {
        this.depIDs = depIDs;
    }

    public void setGovIDs(String govIDs) {
        this.govIDs = govIDs;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    @Override
    public String toString() {
        return "DependencyEntity{" + "depIDs=" + depIDs + ", govIDs=" + govIDs + ", func=" + func + '}';
    }

}
