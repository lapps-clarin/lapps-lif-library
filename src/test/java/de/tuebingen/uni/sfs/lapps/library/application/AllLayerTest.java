/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.application;

import de.tuebingen.uni.sfs.lapps.library.layer.xb.LifAnnotationLayerFinderStored;
import de.tuebingen.uni.sfs.lapps.library.utils.xb.ProcessUtils;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author felahi
 */
public class AllLayerTest {

    private String ALL_EXAMPLE = "data/karen-all.json";
    private String FILE_LIF = "json";
    private ClassLoader classLoader = getClass().getClassLoader();

    public AllLayerTest() {
    }

    @Test
    public void testAllLayers() throws Exception {
        File inputFile = new File(classLoader.getResource(ALL_EXAMPLE).getFile());
        if (inputFile.getName().contains(FILE_LIF)) {
            LifAnnotationLayerFinderStored tool = ProcessUtils.fileProcessing(inputFile);
            if (tool.isTextLayer()) {
                Assert.assertEquals(tool.isTextLayer(), true);
                System.out.println("TextLayer exists:" + tool.getText());
            }
        }
    }
}
