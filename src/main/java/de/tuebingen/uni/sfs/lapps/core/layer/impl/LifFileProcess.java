/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.layer.impl;

import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifAnnotationLayerFinderStored;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifAnnotationProcess;
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
public class LifFileProcess {

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
            LifAnnotationProcess dataModelLif = new LifAnnotationProcess(input);
            tool.convertModel(dataModelLif);
        }  catch (IOException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(LifFileProcess.class.getName()).log(Level.SEVERE, null, "File to String failes!!");
        } catch (JSONValidityException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(LifFileProcess.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (LifException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(LifFileProcess.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(LifFileProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(LifFileProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return tool;
    }

}
