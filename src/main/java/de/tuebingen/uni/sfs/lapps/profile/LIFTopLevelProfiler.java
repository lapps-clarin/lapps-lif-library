/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.constants.LifConnstant;
import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
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
public class LIFTopLevelProfiler {

    private LIFContainer lifContainer = new LIFContainer();
    private String fileString = null;
    private List<View> views = new ArrayList<View>();

    public LIFTopLevelProfiler(InputStream is) throws LifException, IOException, JSONValidityException {
        fileString = IOUtils.toString(is, LifConnstant.GeneralParameters.UNICODE);
        JSONProfile jsonObject = new JSONProfile(fileString);
        if (jsonObject.isInputValid()) {
            jsonToLifObjectMapping(jsonObject);
        } else {
            throw new JSONValidityException(LifValidityCheckerStored.INVALID_JSON_FILE);
        }

        if (lifContainer != null) {
            this.views = lifContainer.getContainer().getViews();
        } else {
            throw new LifException(LifValidityCheckerStored.MESSAGE_INVALID_LIF);
        }

    }

    private void jsonToLifObjectMapping(JSONProfile jsonObject) throws LifException, IOException {
        LifValidityChecker lifDocumentValidityCheck = new LifValidityCheckerStored(jsonObject);
        ObjectMapper mapper = new ObjectMapper();
        if (lifDocumentValidityCheck.isValid()) {
            lifContainer = mapper.readValue(jsonObject.getJsonString(), LIFContainer.class);
        }

    }

    public LIFContainer getLifContainer() {
        return lifContainer;
    }

    public List<View> getViews() {
        return views;
    }

    public String getFileString() {
        return fileString;
    }

}
