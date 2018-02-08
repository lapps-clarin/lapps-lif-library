/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifAnnotationLayerFinderStored;
import de.tuebingen.uni.sfs.lapps.utils.LifFileProcess;
import java.io.File;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author felahi
 */
public class AllLayersInOneFileTest {

    private String ALL_LAYER_EXAMPLE = "karen-all.json";
    private String ALL_LAYER_OTHER_EXAMPLE_1 = "other/karen-all.json";
    private String ALL_LAYER_OTHER_EXAMPLE_2 = "other/karen-most.json";
    private String FILE_LIF = "lif";
    private String FILE_JSON = "json";
    AllLayersInOneFileTest instance;
    private ClassLoader classLoader = getClass().getClassLoader();

    public AllLayersInOneFileTest() {
    }

    /**
     * Test of toText method, of class ConvertToTCFAnnotations for all layers in one file.
     */
    @Test
    public void testAllLayer() throws Exception {
        File inputFile = new File(classLoader.getResource(ALL_LAYER_EXAMPLE).getFile());
        if (inputFile.getName().contains(FILE_LIF)||inputFile.getName().contains(FILE_JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            assertTrue("TextLayer exists in the file", tool.isTextLayer());
            assertTrue("TokenLayer exists in the file", tool.isTokenLayer());
            assertTrue("SenetenceLayer exists in the file", tool.isSenetenceLayer());
            assertTrue("PosLayer exists in the file", tool.isPosLayer());
            //no name entriy layer currently exist!
            assertFalse("NameEntity layer does not exists in the file!!!", tool.isNamedEntityLayer());
            assertTrue("ConstituentLayer exists in the file", tool.isConstituentLayer());
            assertTrue("DependencyLayer exists in the file", tool.isDependencyLayer());
            assertTrue("CorferenceLayer exists in the file", tool.isCorferenceLayer());
        } else {
            throw new Exception("The file extension should be .json");
        }
    }
    
    @Test
    public void testAllLayerOthers1() throws Exception {
        File inputFile = new File(classLoader.getResource(ALL_LAYER_OTHER_EXAMPLE_1).getFile());
        if (inputFile.getName().contains(FILE_LIF)||inputFile.getName().contains(FILE_JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            assertTrue("TextLayer exists in the file", tool.isTextLayer());
            assertTrue("TokenLayer exists in the file", tool.isTokenLayer());
            assertTrue("SenetenceLayer exists in the file", tool.isSenetenceLayer());
            assertTrue("PosLayer exists in the file", tool.isPosLayer());
            assertTrue("NameEntity layer does not exists in the file!!!", tool.isNamedEntityLayer());
            assertTrue("ConstituentLayer exists in the file", tool.isConstituentLayer());
            assertTrue("DependencyLayer exists in the file", tool.isDependencyLayer());
            assertTrue("CorferenceLayer exists in the file", tool.isCorferenceLayer());
        } else {
            throw new Exception("The file extension should be .json");
        }
    }
    
     @Test
    public void testAllLayerOthers2() throws Exception {
        File inputFile = new File(classLoader.getResource(ALL_LAYER_OTHER_EXAMPLE_2).getFile());
        if (inputFile.getName().contains(FILE_LIF)||inputFile.getName().contains(FILE_JSON)) {
            LifAnnotationLayerFinderStored tool = LifFileProcess.fileProcessing(inputFile);
            assertTrue("TextLayer exists in the file", tool.isTextLayer());
            assertTrue("TokenLayer exists in the file", tool.isTokenLayer());
            assertTrue("SenetenceLayer exists in the file", tool.isSenetenceLayer());
            assertTrue("PosLayer exists in the file", tool.isPosLayer());
            assertTrue("NameEntity layer does not exists in the file!!!", tool.isNamedEntityLayer());
        } else {
            throw new Exception("The file extension should be .json");
        }
    }
}
