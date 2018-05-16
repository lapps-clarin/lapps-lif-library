/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.constants;

/**
 *
 * @author felahi
 */
public interface LifErrorMessage {

    public static final String MESSAGE_INVALID_JSON = "LIF ERROR: No Json key/value found!!";
    public static final String MESSAGE_INVALID_LIF = "LIF ERROR: Invalid LIF container!!";
    public static final String MESSAGE_INVALID_LIF_DISCRIMINATOR_DOCUMENT = "LIF ERROR:discriminator is missing in LIF file!!";
    public static final String MESSAGE_INVALID_LIF_PAYLOAD_DOCUMENT = "LIF ERROR:payload is missing in LIF file!!";
    public static final String MESSAGE_INVALID_LIF_METADATA_ANNOTATION = "layer url between metadata and @type mismatched. Please look into ";
    public static final String MESSAGE_INVALID_LIF_TOPLEVEL = "LIF ERROR: the top level annotation (such as context,metadata,text,views) are missing!!";
    public static final String MESSAGE_INVALID_LIF_TOPLEVEL_CONTEXT_MISSING = "LIF ERROR: one of the top level annotation (context) are missing!!";
    public static final String MESSAGE_INVALID_LIF_TOPLEVEL_VIEWS_MISSING = "LIF ERROR: one of the top level annotation (view) are missing!!";
    public static final String MESSAGE_INVALID_LIF_TOPLEVEL_METADATA_MISSING = "LIF ERROR: one of the top level annotation (metadata) are missing!!";
    public static final String MESSAGE_INVALID_LIF_TOPLEVEL_TEXT_MISSING = "LIF ERROR: one of the top level annotation (text) are missing!!";
    public static final String INVALID_JSON_FILE = "LIF ERROR:INVALID JSON FILE!!";
    public static final String NOT_SUPPORTED_YET = "Not supported yet.";
    public static final String NO_ANNOTATION_FOUND = "LIF ERROR: The view is empty!!";
    public static final String NO_ANNOTATION_IN_METADATA = "The metadata defination is wrong!!";
}
