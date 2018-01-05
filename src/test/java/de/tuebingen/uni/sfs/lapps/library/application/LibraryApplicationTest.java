/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

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
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author felahi
 */
public class LibraryApplicationTest {

    private AnnotationLayersStored tool;
    private String CORFERENCE_EXAMPLE = "data/lif-corferenceLayer.json";
    private String CONTSTITUENT_EXAMPLE = "data/lif-constituentLayer.json";
    private String DEPENDENCY_EXAMPLE = "data/lif-dependencyLayer.json";
    private String MULTILAYER_EXAMPLE = "data/lif-multipleLayers.json";
    private String NAMEENTITY_EXAMPLE = "data/lif-nameEntittyLayer.json";
    private String SENTENCE_EXAMPLE = "data/lif-sentenceLayer.json";
    private String TEXT_EXAMPLE = "data/lif-textLayer.json";
    private String TOKEN_EXAMPLE = "data/lif-tokenLayer.json";
    private String[] EXAMPLES = {TEXT_EXAMPLE,
        TOKEN_EXAMPLE,
        SENTENCE_EXAMPLE,
        NAMEENTITY_EXAMPLE,
        MULTILAYER_EXAMPLE,
        DEPENDENCY_EXAMPLE,
        CONTSTITUENT_EXAMPLE,
        CORFERENCE_EXAMPLE};
    private String FILE_LIF = "json";
    private static final String TEMP_FILE_PREFIX = "ne-output-temp";
    private static final String TEMP_FILE_SUFFIX = ".xml";

    public LibraryApplicationTest() {
    }

    /**
     * Test of main method, of class LibraryApplication.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        LibraryApplicationTest mainTest = new LibraryApplicationTest();
        for (int i = 0; i < EXAMPLES.length; i++) {
            File inputFile = mainTest.getFile(EXAMPLES[i]);
            System.out.println("++++++++++++++++++++++++++++++" + inputFile.getName() + "++++++++++++++++++++++++++++++++++++++");
            if (inputFile.getName().contains(FILE_LIF)) {
                condersionFileProcessing(inputFile);
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
            tool = new AnnotationLayersStored();
            DataModelLif dataModelLif = new DataModelLif(input);
            if (dataModelLif.isValid()) {
                tool.convertModel(dataModelLif);
                display();
            } else {
                throw new LifException("The lif file is in correct!!");
            }

        } catch (LifException exlIF) {
            Logger.getLogger(LibraryApplicationTest.class.getName()).log(Level.SEVERE, null, exlIF);
        } catch (Exception ex) {
            Logger.getLogger(LibraryApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(LibraryApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(LibraryApplicationTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void display() {
        if (tool.isTextLayer()) {
            Assert.assertEquals(tool.isTextLayer(), true);
            System.out.println("Text layer exists:" + tool.getText());
        }
        if (tool.isTokenLayer()) {
            Assert.assertEquals(tool.isTokenLayer(), true);
            System.out.println("Token layer exists:" + tool.getLayers().toString());
        }
        if (tool.isPosLayer()) {
             Assert.assertEquals(tool.isPosLayer(), true);
            System.out.println("POS layer exists:" + tool.getLayers().toString());
        }
        if (tool.isLemmaLayer()) {
            Assert.assertEquals(tool.isLemmaLayer(), true);
            System.out.println("Lemma layer exists:" + tool.getLayers().toString());
        }
        if (tool.isConstituentLayer()) {
            Assert.assertEquals(tool.isConstituentLayer(), true);
            System.out.println("Constituent layer exists:" + tool.getLayers().toString());
        }
        if (tool.isDependencyLayer()) {
            Assert.assertEquals(tool.isDependencyLayer(), true);
            System.out.println("Dependency layer exists:" + tool.getLayers().toString());
        }
        if (tool.isCorferenceResolver()) {
            Assert.assertEquals(tool.isCorferenceResolver(), true);
            System.out.println("CorferenceResolver exists:" + tool.getLayers().toString());
        }

    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}
