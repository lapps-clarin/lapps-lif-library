/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.converter.api;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifConstituentParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifDependencyParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifReferenceLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifTokenLayer;
import de.tuebingen.uni.sfs.lapps.exceptions.ConversionException;
import eu.clarin.weblicht.wlfxb.tc.api.ConstituentParsingLayer;
import eu.clarin.weblicht.wlfxb.tc.api.DependencyParsingLayer;
import eu.clarin.weblicht.wlfxb.tc.api.NamedEntitiesLayer;
import eu.clarin.weblicht.wlfxb.tc.api.ReferencesLayer;
import eu.clarin.weblicht.wlfxb.tc.api.SentencesLayer;
import eu.clarin.weblicht.wlfxb.tc.api.TokensLayer;
import eu.clarin.weblicht.wlfxb.tc.xb.TextCorpusStored;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LayersConverter {

    public String toTcfText(String text) throws ConversionException;

    public String toTcfLanguage(String language) throws Exception;

    public TokensLayer toTcfToken(LifTokenLayer lifTokenLayer) throws Exception;

    public SentencesLayer toTcfSentences(LifSentenceLayer lifSentenceLayer) throws Exception;

    public DependencyParsingLayer toTcfDependencyParser(LifDependencyParser lifDependencyParser) throws Exception;

    public ConstituentParsingLayer toTcfConstituentParser(LifConstituentParser lifConstituentParser) throws Exception;

    public NamedEntitiesLayer toTcfNameEntity(LifNameEntityLayer lifNameEntityLayer) throws Exception;

    public ReferencesLayer toTcfCoreferenceResolver(LifReferenceLayer lifRefererenceLayer) throws Exception;

    //closed temporarily..
    //public void toTcfTextSource(String fileString) throws Exception;

    public TextCorpusStored getTextCorpusStored();

}
