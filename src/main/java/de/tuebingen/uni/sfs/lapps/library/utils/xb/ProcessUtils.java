/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.utils.xb;

import de.tuebingen.uni.sfs.lapps.library.layer.xb.LifAnnotationLayerFinderStored;
import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
import de.tuebingen.uni.sfs.lapps.library.model.DataModelLif;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felahi
 */
public class ProcessUtils {
    
    private static final String TEMP_FILE_PREFIX = "ne-output-temp";
    private static final String TEMP_FILE_SUFFIX = ".xml";

    public static LifAnnotationLayerFinderStored fileProcessing(File inputFile) throws IOException, Exception {
        FileInputStream input = null;
        OutputStream tempOutputData = null;
        File tempOutputFile = null;
        try {
            tempOutputFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
            tempOutputData = new BufferedOutputStream(new FileOutputStream(tempOutputFile));
        } catch (IOException ex) {
            if (tempOutputData != null) {
                try {
                    tempOutputData.close();
                } catch (IOException e) {
                    throw new Exception("Output file creation problem!");
                }
            }
            if (tempOutputFile != null) {
                tempOutputFile.delete();
            }
            throw new Exception("Output file creation problem!");
        }

        try {
            input = new FileInputStream(inputFile);
            return process(input, tempOutputData);
        } finally {
            if (input != null) {
                input.close();
            }
            if (tempOutputData != null) {
                tempOutputData.close();
            }
        }
    }

    private static LifAnnotationLayerFinderStored process(final InputStream input, OutputStream output) throws Exception {
        LifAnnotationLayerFinderStored tool = new LifAnnotationLayerFinderStored();
        try {
            DataModelLif dataModelLif = new DataModelLif(input);
            if (dataModelLif.isValid()) {
                tool.convertModel(dataModelLif);
            } else {
                throw new LifException("The lif file is in correct!!");
            }

        } catch (LifException exlIF) {
            Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, exlIF);
        } catch (Exception ex) {
            Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProcessUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return tool;
    }
    
}
