package de.tuebingen.uni.sfs.lapps.core.converter.impl;

import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.exceptions.ConversionException;
import de.tuebingen.uni.sfs.lapps.exceptions.VocabularyMappingException;
import eu.clarin.weblicht.wlfxb.io.WLDObjector;
import eu.clarin.weblicht.wlfxb.io.WLFormatException;
import eu.clarin.weblicht.wlfxb.xb.WLData;
import java.io.IOException;
import java.io.OutputStream;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler.api.LifFormat;
import eu.clarin.weblicht.wlfxb.tc.xb.TextCorpusStored;
import de.tuebingen.uni.sfs.lapps.core.converter.api.FormatConverter;
import de.tuebingen.uni.sfs.lapps.core.converter.api.LayersConverter;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler.impl.LifFormatImpl;
import de.tuebingen.uni.sfs.lapps.utils.Rename;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;

public class LifToTcfFormatConverter implements FormatConverter {

    private TextCorpusStored tcfFormat;

    private static final String TEMP_FILE_PREFIX = "ne-output-temp";
    private static final String TEMP_FILE_SUFFIX = ".xml";

    public LifToTcfFormatConverter() {

    }

    @Override
    public File convertLifToTcf(InputStream input) throws LifException, VocabularyMappingException, ConversionException, IOException, JsonValidityException {
        File tcfFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
        OutputStream fos = new FileOutputStream(tcfFile);
        LifFormatImpl lifFormat = new LifFormatImpl(input);
        tcfFormat = convertLifToTcf(lifFormat);
        write(fos);
        return tcfFile;
    }

    @Override
    public TextCorpusStored convertLifToTcf(LifFormat lifFormat) throws LifException, VocabularyMappingException, ConversionException, IOException, JsonValidityException {
        LayersConverter lifToTCFLayersConverter = new LifToTcfAnnoLayerConverter(lifFormat);
        tcfFormat = lifToTCFLayersConverter.getTextCorpusStored();
        return tcfFormat;
    }

    @Override
    public void write(OutputStream os) throws ConversionException {
        WLData wlData = new WLData(tcfFormat);
        wlData.setVersion(WLData.XML_VERSION_04);
        try {
            WLDObjector.write(wlData, os);
        } catch (WLFormatException ex) {
            throw new ConversionException(ex);
        }
    }

    public TextCorpusStored getTcfFormat() {
        return tcfFormat;
    }

}
