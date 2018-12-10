/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.converter.api;

import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.exceptions.ConversionException;
import de.tuebingen.uni.sfs.lapps.exceptions.VocabularyMappingException;
import java.io.IOException;
import java.io.OutputStream;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler.api.LifFormat;
import eu.clarin.weblicht.wlfxb.tc.xb.TextCorpusStored;
import java.io.File;
/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface FormatConverter {
    
    public File convertLifToTcf(File lifFile) throws LifException, VocabularyMappingException, ConversionException, IOException, JsonValidityException;

    public TextCorpusStored convertLifToTcf(LifFormat lifFormat) throws LifException, VocabularyMappingException, ConversionException, IOException, JsonValidityException;
    
    public void write(OutputStream os) throws ConversionException;


}
