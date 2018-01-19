/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.library.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.tuebingen.uni.sfs.lapps.library.constants.LifVocabularies;
import de.tuebingen.uni.sfs.lapps.library.exception.LifException;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Container;

/**
 *
 * @author felahi
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LIFProfiler {

    //"payload"
    private String discriminator;

    @JsonProperty(LifVocabularies.LIF.Document.PAYLOAD_KEY_JSON)
    private Container container;

    public LIFProfiler() {

    }

    public LIFProfiler(String discriminator, JsonProfile jsonObject) {
        this.discriminator = discriminator;
        this.container = Serializer.parse(jsonObject.getJsonString(), Container.class);
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public Container getContainer() throws LifException {
        if (container == null) {
            throw new LifException("container is null!!");
        }
        return container;
    }
}
