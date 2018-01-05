/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.json;

import de.tuebingen.uni.sfs.lapps.library.utils.xb.ValidityCheckerStored;
import de.tuebingen.uni.sfs.lapps.library.json.JsonProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
import java.io.IOException;
import de.tuebingen.uni.sfs.lapps.library.utils.api.ValidityChecker;

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
            throw new LifException(ValidityCheckerStored.INVALID_JSON_FILE);
        }

    }

    private void jsonToLifObjectMapping(JsonProcessor jsonObject) throws LifException, IOException {
        ValidityChecker lifDocumentValidityCheck = new ValidityCheckerStored(jsonObject);
        ObjectMapper mapper = new ObjectMapper();
        if (lifDocumentValidityCheck.isValid()) {
            mascDocument = mapper.readValue(jsonObject.getJsonString(), LIFProfiler.class);
        }

    }

    public LIFProfiler getMascDocument() {
        return mascDocument;
    }

}
