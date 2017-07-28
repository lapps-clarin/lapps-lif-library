/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.library.model.DataModelLif;
import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
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
public class Application {

    private FormatConverterTool tool;
    private String baseDir = "/Users/felahi/repository/lapps-lif-library/src/main/resources/data/";
    private String workingDir = baseDir + "working/";
    private String textlayerDir = workingDir + "textlayer/";
    private String tokenlayerDir = workingDir + "tokenlayer/";
    private String poslayerDir = workingDir + "poslayer/";
    private String sentenceDir = workingDir + "senetenceLayer/";
    private String nameEntityDir = workingDir + "nameEntityLayer/";
    private String constituentParserDir = workingDir + "constituentParser/";
    //private String dir[] = {tokenlayerDir, poslayerDir, sentenceDir, nameEntityDir,constituentParserDir};
    private String dir[] = {baseDir};
    private String File_Type = "json";

    //private String dir[] = {tokenlayerDir,poslayerDir,sentenceDir,nameEntityDir};
    //private String args[]={"/Users/felahi/repository/converter/service-lapps-converter/src/main/resources/TestInput/", "/Users/felahi/repository/converter/service-lapps-converter/src/main/resources/"};
    ///Users/felahi/repository/LifToTcfConverter/src/main/resources/input/ /Users/felahi/repository/LifToTcfConverter/src/main/resources/ tcf
    ///Users/felahi/repository/service-lapps-converter/src/main/resources/inputTest/
    //Users/felahi/repository/service-lapps-converter/src/main/resources/outputTest/
    private static final String TEXT_TCF_XML = "text/tcf+xml";
    private static final String TEXT_LIF_JSON = "text/lif+json";
    private static final String FALL_BACK_MESSAGE = "Data processing failed";
    private static final String TEMP_FILE_PREFIX = "ne-output-temp";
    private static final String TEMP_FILE_SUFFIX = ".xml";

    public Application() throws Exception {
        try {
            tool = new FormatConverterTool();
        } catch (Exception ex) {
            throw new Exception("conversion is not working well");
        }

    }

    public static void main(String[] args) throws java.io.IOException, Exception {

        /*if (args.length != 2) {
            System.out.println("Provide args:");
            System.out.println("PATH_TO_INPUT PATH_TO_OUTPUT");
            return;
        }*/
        Application mainTest = new Application();
        mainTest.conversionSeclection();
    }

    public void conversionSeclection() throws IOException, Exception {

        for (int i = 0; i < dir.length; i++) {
            File inputDirectory = new File(dir[i]);
            File[] fileList = inputDirectory.listFiles();
            System.out.println("dir " + dir[i]);

            for (File inputFile : fileList) {
                System.out.println("++++++++++++++++++++++++++++++" + inputFile.getName() + "++++++++++++++++++++++++++++++++++++++");
                if (inputFile.getName().contains(File_Type)) {
                    condersionFileProcessing(inputFile);
                }
            }
        }

    }

    public void condersionFileProcessing(File inputFile) throws IOException, Exception {
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
            process(input, tempOutputData);
        } finally {
            if (input != null) {
                input.close();
            }
            if (tempOutputData != null) {
                tempOutputData.close();
            }
        }
    }

    private void process(final InputStream input, OutputStream output) throws Exception {
        try {
            DataModelLif dataModelLif = new DataModelLif(input);
            if (dataModelLif.isValid()) {
                tool.convertModel(dataModelLif);
                display();
            } else {
                throw new LifException("The lif file is in correct!!");
            }

        } catch (LifException exlIF) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, exlIF);
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void display() {
        if (tool.isTextLayer()) {
            System.out.println("Text layer exists:" + tool.getText());
        }
        if (tool.isTokenLayer()) {
            System.out.println("Token layer exists:" + tool.getLayers().toString());
        }
         if (tool.isPosLayer()) {
            System.out.println("POS layer exists:" + tool.getLayers().toString());
        }
          if (tool.isLemmaLayer()) {
            System.out.println("Lemma layer exists:" + tool.getLayers().toString());
        }
          if (tool.isConstituentLayer()) {
            System.out.println("Constituent layer exists:" + tool.getLayers().toString());
        }
          if (tool.isDependencyLayer()) {
            System.out.println("Dependency layer exists:" + tool.getLayers().toString());
        }

        System.out.println("text:" + tool.getText());
        System.out.println("layer:" + tool.getLayers().toString());
    }

}
