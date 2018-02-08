/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.json;

import de.tuebingen.uni.sfs.lapps.profile.impl.JsonProfile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felahi
 */
public class JsonProcessorTest {

    private String jsonString = "{\"age\":\"29\",\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"],\"name\":\"mkyong\"}";

    public JsonProcessorTest() {
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
     * Test of isInputValid method, of class JsonProfile.
     */
    @Test
    public void testIsInputValid() throws Exception {
        System.out.println("isInputValid");
        JsonProfile instance = new JsonProfile(jsonString);
        boolean expResult = true;
        boolean result = instance.isInputValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJsonString method, of class JsonProfile.
     */
    @Test
    public void testGetJsonString() throws IOException {
        System.out.println("getJsonString");
        JsonProfile instance = new JsonProfile(jsonString);
        String expResult = jsonString;
        String result = instance.getJsonString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJsonMap method, of class JsonProfile.
     */
    @Test
    public void testGetJsonMap() throws IOException {
        System.out.println("getJsonMap");
        JsonProfile instance = new JsonProfile(jsonString);
        Map<String, Object> expResult = new HashMap<String, Object>();
        expResult.put("age", "29");
        Map<String, Object> result = instance.getJsonMap();
        assertEquals(expResult.get("age"), result.get("age"));
        assertEquals(expResult.containsValue("29"), result.containsValue("29"));
    }

    /**
     * Test of isOutputValid method, of class JsonProfile.
     */
    @Test
    public void testIsOutputValid() throws Exception {
        System.out.println("isOutputValid");
         JsonProfile instance = new JsonProfile(jsonString);
        boolean expResult = true;
        boolean result = instance.isOutputValid();
        assertEquals(expResult, result);
    }

}
