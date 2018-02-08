/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tuebingen.uni.sfs.lapps.constants.LifConnstant;
import de.tuebingen.uni.sfs.lapps.core.layer.api.AnnotationLayerFinder;
import de.tuebingen.uni.sfs.lapps.core.layer.impl.LifViewProcess;
import de.tuebingen.uni.sfs.lapps.exceptions.JSONValidityException;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.commons.io.IOUtils;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;

/**
 *
 * @author felahi
 */
public class LIFProfilerImpl implements LIFProfiler{

    private LIFContainer lifContainer = new LIFContainer();
    private String fileString = null;
    private LIFViewProfile lifViewProfile;

    public LIFProfilerImpl(InputStream is) throws LifException, IOException, JSONValidityException {
        fileString = IOUtils.toString(is, LifConnstant.GeneralParameters.UNICODE);
        JSONProfile jsonObject = new JSONProfile(fileString);
        if (jsonObject.isInputValid()) {
            jsonToLifObjectMapping(jsonObject);
        } else {
            throw new JSONValidityException(LifValidityCheckerStored.INVALID_JSON_FILE);
        }
        
        if(lifContainer!=null)
            lifViewProfile=new LifViewProcess(lifContainer.getContainer().getViews());
         else
           throw new LifException(LifValidityCheckerStored.MESSAGE_INVALID_LIF);

    }

    private void jsonToLifObjectMapping(JSONProfile jsonObject) throws LifException, IOException {
        LifValidityChecker lifDocumentValidityCheck = new LifValidityCheckerStored(jsonObject);
        ObjectMapper mapper = new ObjectMapper();
        if (lifDocumentValidityCheck.isValid()) {
            lifContainer = mapper.readValue(jsonObject.getJsonString(), LIFContainer.class);
        }

    }

    @Override
    public String getLanguage() throws LifException {
        return lifContainer.getContainer().getLanguage();
    }

    @Override
    public String getText() throws LifException {
        return lifContainer.getContainer().getText();
    }

    @Override
    public String getFileString() {
       return fileString;
    }

    @Override
    public Container getLifContainer() throws LifException {
         return lifContainer.getContainer();
    }

    @Override
    public LIFViewProfile getLifViewProfile() {
        return lifViewProfile;
    }

    @Override
    public boolean isValid() {
        return true;
    }

}
