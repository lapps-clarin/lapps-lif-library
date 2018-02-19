/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author felahi
 */
public class LifDocumentConnstant {

    private LifDocumentConnstant() {
    }

    public static class GeneralParameters {

        public static final String UNICODE = "UTF-8";
        public static final String PARAMETER_SEPERATOR_REG = "\\=";
        public static final String PARAMETER_SEPERATOR = "=";
    }

    public static class Annotation {

        public static class DiscriminitorsExtended {

            public static final String PENN_TREE = "penntree";
            public static final String CONSTITUENTS = "constituents";
        }

        public static class TreeSets {

            public static final String CONSTITUENT_ROOT = "ROOT";
        }
         public static class Ordering {

            public static final Map<String, Integer> LAYER_ORDER = new HashMap<String, Integer>();

            static {
                LAYER_ORDER.put(Discriminators.Uri.TEXT, 1);
                LAYER_ORDER.put(Discriminators.Uri.TOKEN, 2);
                LAYER_ORDER.put(Discriminators.Uri.SENTENCE, 3);
                LAYER_ORDER.put(Discriminators.Uri.POS, 4);
                LAYER_ORDER.put(Discriminators.Uri.NE, 5);
                LAYER_ORDER.put(Discriminators.Uri.DEPENDENCY_STRUCTURE, 6);
                LAYER_ORDER.put(Discriminators.Uri.PHRASE_STRUCTURE, 7);
                LAYER_ORDER.put(Discriminators.Uri.COREF, 8);
            }
        }

    }

    public static class DocumentStructure {

        public static final String DISCRIMINATOR_KEY_JSON = "discriminator";
        public static final String PAYLOAD_KEY_JSON = "payload";
        public static String WEB_EXCHANGE_VOCABULARY = "http://vocab.lappsgrid.org/lapps-vocabulary.owl";

        public static class TopLevel {

            public static final String CONTEXT_KEY_LIF = "@context";
            public static final String TEXT_KEY_LIF = "text";
            public static final String VIEWS_KEY_LIF = "views";
            public static final String METADATA_KEY_LIF = "metadata";
        }
    }

}
