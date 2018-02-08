/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.constants.LifConnstant;
import de.tuebingen.uni.sfs.lapps.exceptions.JsonValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.profile.api.LifValidityChecker;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class LifTopLevelProfiler {

    private LifContainer lifContainer = new LifContainer();
    private String fileString = null;
    private List<View> views = new ArrayList<View>();

    public LifTopLevelProfiler(InputStream is) throws LifException, IOException, JsonValidityException {
        fileString = IOUtils.toString(is, LifConnstant.GeneralParameters.UNICODE);
        JsonProfile jsonObject = new JsonProfile(fileString);
        if (jsonObject.isInputValid()) {
            jsonToLifObjectMapping(jsonObject);
        } else {
            throw new JsonValidityException(LifValidityCheckerStored.INVALID_JSON_FILE);
        }

        if (lifContainer != null) {
            this.views = lifContainer.getContainer().getViews();
        } else {
            throw new LifException(LifValidityCheckerStored.MESSAGE_INVALID_LIF);
        }

    }

    private void jsonToLifObjectMapping(JsonProfile jsonObject) throws LifException, IOException {
        LifValidityChecker lifDocumentValidityCheck = new LifValidityCheckerStored(jsonObject);
        ObjectMapper mapper = new ObjectMapper();
        if (lifDocumentValidityCheck.isValid()) {
            lifContainer = mapper.readValue(jsonObject.getJsonString(), LifContainer.class);
        }

    }

    public LifContainer getLifContainer() {
        return lifContainer;
    }

    public List<View> getViews() {
        return views;
    }

    public String getFileString() {
        return fileString;
    }

}
