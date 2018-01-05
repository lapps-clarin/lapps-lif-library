/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.library.annotation.xb.AnnotationLayersStored;
import de.tuebingen.uni.sfs.lapps.library.utils.ProcessLifDataModel;
import java.io.File;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author felahi
 */
public class LibraryApplicationTest {

    private String CORFERENCE_EXAMPLE = "data/lif-corferenceLayer.json";
    private String CONTSTITUENT_EXAMPLE = "data/lif-constituentLayer.json";
    private String DEPENDENCY_EXAMPLE = "data/lif-dependencyLayer.json";
    private String MULTILAYER_EXAMPLE = "data/lif-multipleLayers.json";
    private String NAMEENTITY_EXAMPLE = "data/lif-nameEntittyLayer.json";
    private String SENTENCE_EXAMPLE = "data/lif-sentenceLayer.json";
    private String POS_EXAMPLE = "data/lif-posLayer.json";
    private String TEXT_EXAMPLE = "data/lif-textLayer.json";
    private String TOKEN_EXAMPLE = "data/lif-tokenLayer.json";
    private String FILE_LIF = "json";

    public LibraryApplicationTest() {
    }

    @Test
    public void testTextLayer() throws Exception {
        File inputFile = getFile(TEXT_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isTextLayer()) {
                Assert.assertEquals(tool.isTextLayer(), true);
                System.out.println("TextLayer exists:" + tool.getText());
            }
        }
    }

    @Test
    public void testTokenLayer() throws Exception {
        File inputFile = getFile(TOKEN_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isTokenLayer()) {
                Assert.assertEquals(tool.isTokenLayer(), true);
                System.out.println("TokenLayer exists:" + tool.getLayers().toString());
            }
        }

    }

    @Test
    public void testPosLayer() throws Exception {
        File inputFile = getFile(POS_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isPosLayer()) {
                Assert.assertEquals(tool.isPosLayer(), true);
                System.out.println("PosLayer exists:" + tool.getLayers().toString());
            }
        }
    }

    @Test
    public void testSentenceLayer() throws Exception {
        File inputFile = getFile(SENTENCE_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isSenetenceLayer()) {
                Assert.assertEquals(tool.isSenetenceLayer(), true);
                System.out.println("SentenceLayer exists:" + tool.getLayers().toString());
            }
        }
    }

    @Test
    public void testNamedEntirtyLayer() throws Exception {
        File inputFile = getFile(NAMEENTITY_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isNamedEntityLayer()) {
                Assert.assertEquals(tool.isNamedEntityLayer(), true);
                System.out.println("NamedEntirtyLayer exists:" + tool.getLayers().toString());
            }
        }
    }

    @Test
    public void testDependencyLayer() throws Exception {
        File inputFile = getFile(DEPENDENCY_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isDependencyLayer()) {
                Assert.assertEquals(tool.isDependencyLayer(), true);
                System.out.println("DependencyLayer exists:" + tool.getLayers().toString());
            }
        }

    }

    @Test
    public void testConstituentLayer() throws Exception {
        File inputFile = getFile(CONTSTITUENT_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isConstituentLayer()) {
                Assert.assertEquals(tool.isConstituentLayer(), true);
                System.out.println("ConstituentLayer exists:" + tool.getLayers().toString());
            }
        }

    }

    @Test
    public void testCorferenceLayer() throws Exception {
        File inputFile = getFile(CORFERENCE_EXAMPLE);
        if (inputFile.getName().contains(FILE_LIF)) {
            AnnotationLayersStored tool = ProcessLifDataModel.fileProcessing(inputFile);
            if (tool.isCorferenceLayer()) {
                Assert.assertEquals(tool.isCorferenceLayer(), true);
                System.out.println("CorferenceLayer exists:" + tool.getLayers().toString());
            }
        }

    }
   
    public File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}