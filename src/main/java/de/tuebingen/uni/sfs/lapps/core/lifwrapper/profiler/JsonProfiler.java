/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author felahi
 */
public class JsonProfiler implements ValidityChecker {

    private String jsonString = null;
    private Map<String, Object> jsonMap = new HashMap<String, Object>();

    public JsonProfiler(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public boolean isValid() throws JsonParseException, IOException, JsonValidityException, LifException {
        this.jsonMap = conversionJSONMapToString();
        return true;
    }

    private Map<String, Object> conversionJSONMapToString() throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        return mapper.readValue(jsonString, typeRef);
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

}
