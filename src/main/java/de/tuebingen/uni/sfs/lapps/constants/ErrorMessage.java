/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.constants;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface ErrorMessage {

    public interface Lif {

        public static final String MESSAGE_LIF_ERROR = "LIF ERROR:The Lif file is not correct!!";
        public static final String MESSAGE_LIF_ERROR_CONSTITUENT_PARSER_MISSING_ANNOATAIONS = "LIF ERROR: Missing annotation (PhraseStructure or Constituent or Tokens) in LIF!!";
        public static final String MESSAGE_LIF_ERROR_DEPENDENCY_PARSER_MISSING_ANNOATAIONS = "LIF ERROR: Missing annotation (DependencyStructure or Dependency or Tokens) in LIF!!";
        public static final String MESSAGE_LIF_ERROR_DEPENDENCY_PARSER_TOKEN_MISSING = "LIF ERROR: No lif token found from dependency id!!";
        public static final String MESSAGE_LIF_ERROR_DEPENDENCY_PARSER_ROOT_MISSING = "LIF ERROR: No Root node found for the parse!!";
        public static final String MESSAGE_LIF_ERROR_DEPENDENCIES_MISSING = "LIF ERROR: No Dependency list found for in Parse ";
        public static final String MESSAGE_UNKNOWN_ERROR = "UNKNOWN ERROR:The error of LIF file unknown!!";
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
        public static final String MESSAGE_JSON_ERROR = "JSON ERROR:The json is not correct!!";
        public static final String MESSAGE_File_ERROR = "FILE ERROR:The input file is not correct!!";

    }

    public interface Conversion {

        public static final String MESSAGE_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: LIF to TCF conversion failed!!";
        public static final String MESSAGE_VOCABULARY_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: Vocabulary conversion is failed!!";
        public static final String MESSAGE_LANGUAGE_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: Language conversion failed from LIF to TCF failed!!";
        public static final String MESSAGE_TEXT_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: Text conversion failed from LIF to TCF failed!!";
        public static final String MESSAGE_TOKEN_LAYER_REQUIRED = "LIF to TCF CONVERSION ERROR: There is no token layer. A token layer is required!!";
        public static final String MESSAGE_SENTENCE_LAYER_REQUIRED = "LIF to TCF CONVERSION ERROR: There is no sentence layer. A sentence layer is required!!";
        public static final String MESSAGE_SENTENCE_BOUNDERY_NOT_GIVEN = "LIF to TCF CONVERSION ERROR: the sentence boundery is not given to in lif sentence layer";
        public static final String MESSAGE_STARTID_TOKEN_CONNECTION_NOT_FOUND = "LIF to TCF CONVERSION ERROR: no connected token found for this token-level-layer!!";
        public static final String MESSAGE_TOKEN_LAYER_REQUIRED_FOR_NAMEENTITY_LAYER = "LIF to TCF CONVERSION ERROR: There is no token layer in lif file. For conversion of LIF to TCF nameEntitty layer, a token layer is mandatory!!";
        public static final String MESSAGE_CONSTITUENT_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: the converion of constituent parser failed!!";
        public static final String MESSAGE_DEPENDENCY_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: the converion of dependency parser failed!!";
        public static final String MESSAGE_COREFERENCE_CONVERSION_FAILED = "LIF to TCF CONVERSION ERROR: the converion of coreference parser failed!!";

    }

}
