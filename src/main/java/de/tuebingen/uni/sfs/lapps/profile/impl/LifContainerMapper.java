/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.profile.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.tuebingen.uni.sfs.lapps.constants.LifDocumentConnstant;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Container;

/**
 *
 * @author felahi
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LifContainerMapper {

    //"payload"
    private String discriminator;

    @JsonProperty(LifDocumentConnstant.DocumentStructure.PAYLOAD_KEY_JSON)
    private Container container;

    public LifContainerMapper() {

    }

    public LifContainerMapper(String discriminator,String fileString) {
        this.discriminator = discriminator;
        this.container = Serializer.parse(fileString, Container.class);
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public Container getContainer() throws LifException {
        return container;
    }
    
}
