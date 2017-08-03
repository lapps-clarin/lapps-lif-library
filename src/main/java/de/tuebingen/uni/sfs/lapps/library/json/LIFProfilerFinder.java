/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.json;

import de.tuebingen.uni.sfs.lapps.library.validity.ValidityCheckStored;
import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
import de.tuebingen.uni.sfs.lapps.library.validity.ValidityCheck;
import de.tuebingen.uni.sfs.lapps.library.json.JsonProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author felahi
 */
public class LIFProfilerFinder {

    private LIFProfiler mascDocument = new LIFProfiler();

    public LIFProfilerFinder(String jsonString) throws LifException, IOException {
        JsonProcessor jsonObject = new JsonProcessor(jsonString);
        if (jsonObject.isInputValid()) {
            jsonToLifObjectMapping(jsonObject);
        } else {
            throw new LifException(ValidityCheckStored.INVALID_JSON_FILE);
        }

    }

    private void jsonToLifObjectMapping(JsonProcessor jsonObject) throws LifException, IOException {
        ValidityCheck lifDocumentValidityCheck = new ValidityCheckStored(jsonObject);
        ObjectMapper mapper = new ObjectMapper();
        if (lifDocumentValidityCheck.isValid()) {
            mascDocument = mapper.readValue(jsonObject.getJsonString(), LIFProfiler.class);
        }

    }

    public LIFProfiler getMascDocument() {
        return mascDocument;
    }

}
