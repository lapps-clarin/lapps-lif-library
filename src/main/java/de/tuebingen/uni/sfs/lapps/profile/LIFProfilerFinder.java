/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile;

import de.tuebingen.uni.sfs.lapps.exceptions.LifValidityCheckerStored;
import de.tuebingen.uni.sfs.lapps.profile.JSONProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import java.io.IOException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifValidityChecker;

/**
 *
 * @author felahi
 */
public class LIFProfilerFinder {

    private LIFProfiler mascDocument = new LIFProfiler();

    public LIFProfilerFinder(String jsonString) throws LifException, IOException, JSONValidityException {
        JSONProfile jsonObject = new JSONProfile(jsonString);
        if (jsonObject.isInputValid()) {
            jsonToLifObjectMapping(jsonObject);
        } else {
            throw new JSONValidityException(LifValidityCheckerStored.INVALID_JSON_FILE);
        }

    }

    private void jsonToLifObjectMapping(JSONProfile jsonObject) throws LifException, IOException {
        LifValidityChecker lifDocumentValidityCheck = new LifValidityCheckerStored(jsonObject);
        ObjectMapper mapper = new ObjectMapper();
        if (lifDocumentValidityCheck.isValid()) {
            mascDocument = mapper.readValue(jsonObject.getJsonString(), LIFProfiler.class);
        }

    }

    public LIFProfiler getMascDocument() {
        return mascDocument;
    }

}
