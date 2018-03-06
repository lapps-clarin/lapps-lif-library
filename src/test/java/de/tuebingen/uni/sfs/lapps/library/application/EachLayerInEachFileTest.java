/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifAnnotationLayerFinderStored;
import de.tuebingen.uni.sfs.lapps.utils.LifFileProcess;
import java.io.File;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author felahi
 */
public class EachLayerInEachFileTest {

    private String CORFERENCE_EXAMPLE = "lif-corferenceLayer.json";
    private String CONTSTITUENT_EXAMPLE = "lif-constituentLayer.json";
    private String DEPENDENCY_EXAMPLE = "lif-dependencyLayer.json";
    private String MULTILAYER_EXAMPLE = "lif-multipleLayers.json";
    private String NAMEENTITY_EXAMPLE = "lif-nameEntittyLayer.json";
    private String SENTENCE_EXAMPLE = "lif-sentenceLayer.json";
    private String POS_EXAMPLE1 = "lif-posLayer.json";
    private String POS_EXAMPLE2 = "other/lif-posLayer-tcfToLifCon.json";
    private String TEXT_EXAMPLE = "lif-textLayer.json";
    private String TOKEN_EXAMPLE = "lif-tokenLayer.json";
    private ClassLoader classLoader = getClass().getClassLoader();

    public EachLayerInEachFileTest() {
    }

    @Test
    public void testTextLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(TEXT_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("TextLayer exists:", tool.isTextLayer());
            System.out.println("TextLayer exists:" + tool.getText());
        }
    }

    @Test
    public void testTokenLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(TOKEN_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("TextLayer exists:", tool.isTokenLayer());
            System.out.println("TokenLayer exists:" + tool.getLayers().toString());
        }
    }

    @Test
    public void testPosLayer1() throws Exception {

        File inputFile = new File(classLoader.getResource(POS_EXAMPLE1).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("PosLayer exists:", tool.isPosLayer());
            System.out.println("PosLayer exists:" + tool.getLayers().toString());
        }
    }

    @Test
    public void testPosLayerLIFtoTcfConverter() throws Exception {

        File inputFile = new File(classLoader.getResource(POS_EXAMPLE2).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("PosLayer exists:", tool.isPosLayer());
            System.out.println("PosLayer exists:" + tool.getLayers().toString());
        }
    }

    @Test
    public void testSentenceLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(SENTENCE_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("SentenceLayer exists:", tool.isSenetenceLayer());
            System.out.println("SentenceLayer exists:" + tool.getLayers().toString());
        }
    }

    @Test
    public void testNamedEntirtyLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(NAMEENTITY_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("NamedEntirtyLayer exists:", tool.isNamedEntityLayer());
            System.out.println("NamedEntirtyLayer exists:" + tool.getLayers().toString());
        }
    }

    @Test
    public void testDependencyLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(DEPENDENCY_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("DependencyLayer exists:", tool.isDependencyLayer());
            System.out.println("DependencyLayer exists:" + tool.getLayers().toString());
        }

    }

    @Test
    public void testConstituentLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(CONTSTITUENT_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("ConstituentLayer exists:", tool.isConstituentLayer());
            System.out.println("ConstituentLayer exists:" + tool.getLayers().toString());
        }

    }

    @Test
    public void testCorferenceLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(CORFERENCE_EXAMPLE).getFile());
        if (inputFile.getName().contains(Discriminators.Alias.JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            Assert.assertTrue("CorferenceLayer exists:", tool.isCorferenceLayer());
            System.out.println("CorferenceLayer exists:" + tool.getLayers().toString());
        }

    }

}
