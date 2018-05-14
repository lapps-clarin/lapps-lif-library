/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.core.layer.impl.LIFAnnotationLayer;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LIFAnnotationLayers;
import de.tuebingen.uni.sfs.lapps.profile.api.LifProfile;
import de.tuebingen.uni.sfs.lapps.profile.impl.LifProfiler;
import java.io.InputStream;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author Mohammad Fazleh Elahi
 * This is a test when all lif layers in one file.
 */
public class EachViewEachLayerTest {

    private static final String ALL_LAYER_EXAMPLE = "/data/karen-all.json";
    private LifProfile lifProfile;

    public EachViewEachLayerTest() throws Exception {
        InputStream input = this.getClass().getResourceAsStream(ALL_LAYER_EXAMPLE);
        lifProfile = new LifProfiler(input);
    }

    /**
     * This is a test when language is given in lif
     */
    @Test
    public void testAllLayersInOneFile_Language() throws Exception {
        System.out.println("testTextLayer");
        assertTrue("TextLayer exists in the file", lifProfile.getLanguage() != null);
    }

    /**
     * This is a test when when text is given in lif
     */
    @Test
    public void testAllLayersInOneFile_TextLayer() throws Exception {
        System.out.println("testTextLayer");
        assertTrue("TextLayer exists in the file", lifProfile.getText() != null);
    }

    /**
     * This is a test of file string.
     */
    @Test
    public void testAllLayersInOneFile_TextSource() throws Exception {
        System.out.println("testTextLayer");
        assertTrue("TextLayer exists in the file", lifProfile.getFileString() != null);
    }

    /**
     * This is a test when a view contains token layer
     */
    @Test
    public void testAllLayersInOneFile_TokenLayer() throws Exception {
        System.out.println("testTokenLayer");
        LIFAnnotationLayers lifAnnotationLayers = lifProfile.getLifAnnotationLayers();
        assertTrue("TokenLayer exists in the file", lifAnnotationLayers.findLayer(Discriminators.Uri.TOKEN).isValid());
    }

    /**
     * This is a test when a view contains pos layer
     */
    @Test
    public void testAllLayersInOneFile_PosLayer() throws Exception {
        System.out.println("testPosLayer");
        LIFAnnotationLayer lifAnnotationLayer = lifProfile.getLifAnnotationLayers().findLayer(Discriminators.Uri.POS);
        assertTrue("PosLayer exists in the file", lifAnnotationLayer.isValid());
    }

    /**
     * This is a test when a view contains sentence layer
     */
    @Test
    public void testAllLayersInOneFile_SentenceLayer() throws Exception {
        System.out.println("testSentenceLayer");
        LIFAnnotationLayer lifAnnotationLayer = lifProfile.getLifAnnotationLayers().findLayer(Discriminators.Uri.SENTENCE);
        assertTrue("SenetenceLayer exists in the file", lifAnnotationLayer.isValid());
    }

    /**
     * This is a test when a view contains NamedEntity layer
     */
    @Test
    public void testAllLayersInOneFile_NamedEntityLayer() throws Exception {
        System.out.println("testNamedEntityLayer");
        LIFAnnotationLayer lifAnnotationLayer = lifProfile.getLifAnnotationLayers().findLayer(Discriminators.Uri.NE);
        assertTrue("NameEntity layer does not exists in the file!!!", lifAnnotationLayer.isValid());
    }

    /**
     * This is a test when a view contains ConstituentParser layer
     */
    @Test
    public void testAllLayersInOneFile_ConstituentParserLayer() throws Exception {
        System.out.println("testConstituentParserLayer");
        LIFAnnotationLayer lifAnnotationLayer = lifProfile.getLifAnnotationLayers().findLayer(Discriminators.Uri.PHRASE_STRUCTURE);
        assertTrue("ConstituentLayer exists in the file", lifAnnotationLayer.isValid());
    }

    /**
     * This is a test when a view contains DependencyLayer
     */
    @Test
    public void testAllLayersInOneFile_DependencyLayer() throws Exception {
        System.out.println("testDependencyLayer");
        LIFAnnotationLayer lifAnnotationLayer = lifProfile.getLifAnnotationLayers().findLayer(Discriminators.Uri.DEPENDENCY_STRUCTURE);
        assertTrue("DependencyLayer exists in the file", lifAnnotationLayer.isValid());
    }

    /**
     * This is a test when a view contains CorferenceLayer
     */
    @Test
    public void testAllLayersInOneFile_CorferenceLayer() throws Exception {
        System.out.println("testCorferenceLayer");
        LIFAnnotationLayer lifAnnotationLayer = lifProfile.getLifAnnotationLayers().findLayer(Discriminators.Uri.COREF);
        assertTrue("CorferenceLayer exists in the file", lifAnnotationLayer.isValid());
    }
}
