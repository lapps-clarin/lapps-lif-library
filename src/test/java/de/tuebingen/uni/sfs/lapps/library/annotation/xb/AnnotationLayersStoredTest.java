/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.annotation.xb;

import de.tuebingen.uni.sfs.lapps.library.exception.VocabularyMappingException;
import de.tuebingen.uni.sfs.lapps.library.model.DataModelLif;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author felahi
 */
public class AnnotationLayersStoredTest {
    
    public AnnotationLayersStoredTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convertModel method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testConvertModel() throws Exception {
        System.out.println("convertModel");
        DataModelLif lifDataModel = null;
        AnnotationLayersStored instance = new AnnotationLayersStored();
        instance.convertModel(lifDataModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAnnotationLayers method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testFindAnnotationLayers() throws Exception {
        System.out.println("findAnnotationLayers");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        instance.findAnnotationLayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLanguage method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testGetLanguage() throws VocabularyMappingException {
        System.out.println("getLanguage");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        String expResult = "";
        String result = instance.getLanguage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getText method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testGetText() throws VocabularyMappingException {
        System.out.println("getText");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        String expResult = "";
        String result = instance.getText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLayers method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testGetLayers() throws VocabularyMappingException {
        System.out.println("getLayers");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        List<String> expResult = null;
        List<String> result = instance.getLayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLanguage method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsLanguage() throws VocabularyMappingException {
        System.out.println("isLanguage");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isLanguage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTextLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsTextLayer() throws VocabularyMappingException {
        System.out.println("isTextLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isTextLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTokenLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsTokenLayer() throws VocabularyMappingException {
        System.out.println("isTokenLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isTokenLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPosLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsPosLayer() throws VocabularyMappingException {
        System.out.println("isPosLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isPosLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLemmaLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsLemmaLayer() throws VocabularyMappingException {
        System.out.println("isLemmaLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isLemmaLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDependencyLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsDependencyLayer() throws VocabularyMappingException {
        System.out.println("isDependencyLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isDependencyLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConstituentLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsConstituentLayer() throws VocabularyMappingException {
        System.out.println("isConstituentLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isConstituentLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSenetenceLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsSenetenceLayer() throws VocabularyMappingException {
        System.out.println("isSenetenceLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isSenetenceLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTokenPosLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsTokenPosLayer() throws VocabularyMappingException {
        System.out.println("isTokenPosLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isTokenPosLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCorferenceLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsCorferenceLayer() throws VocabularyMappingException {
        System.out.println("isCorferenceLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isCorferenceLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNamedEntityLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsNamedEntityLayer() throws VocabularyMappingException {
        System.out.println("isNamedEntityLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isNamedEntityLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChunkLayer method, of class AnnotationLayersStored.
     */
    @Ignore
    public void testIsChunkLayer() throws VocabularyMappingException {
        System.out.println("isChunkLayer");
        AnnotationLayersStored instance = new AnnotationLayersStored();
        boolean expResult = false;
        boolean result = instance.isChunkLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}