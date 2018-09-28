/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.lifwrapper.api;

import java.util.Map;
import java.util.TreeMap;
import org.lappsgrid.discriminator.Discriminators;

/**
 *
 * @author felahi
 */
public class LifConstants {

    public static final String VIEW_REFERENCE_INDICATOR = ":";
    public static final Integer VIEW_ID_INDEX = 0;
    public static final Integer VIEW_ELEMENT_INDEX = 1;

    private LifConstants() {
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

            public static final Map<String, Integer> LIF_LAYER_ORDER = new TreeMap<String, Integer>();

            static {
                LIF_LAYER_ORDER.put(Discriminators.Uri.TEXT, 1);
                LIF_LAYER_ORDER.put(Discriminators.Uri.TOKEN, 2);
                LIF_LAYER_ORDER.put(Discriminators.Uri.POS, 3);
                LIF_LAYER_ORDER.put(Discriminators.Uri.LEMMA, 4);
                LIF_LAYER_ORDER.put(Discriminators.Uri.NE, 5);
                LIF_LAYER_ORDER.put(Discriminators.Uri.COREF, 7);
                LIF_LAYER_ORDER.put(Discriminators.Uri.SENTENCE, 8);
                LIF_LAYER_ORDER.put(Discriminators.Uri.DEPENDENCY_STRUCTURE, 9);
                LIF_LAYER_ORDER.put(Discriminators.Uri.PHRASE_STRUCTURE, 10);
            }
        }

    }

    public static class DocumentStructure {

        public static final String DISCRIMINATOR_KEY_JSON = "discriminator";
        public static final String PAYLOAD_KEY_JSON = "payload";
        public static String WEB_EXCHANGE_VOCABULARY = "http://vocab.lappsgrid.org/lapps-vocabulary.owl";

        public static class TopLevel {

            public static final String CONTEXT_KEY_LIF = "@context";
            public static final String VIEWS_KEY_LIF = "views";
            public static final String METADATA_KEY_LIF = "metadata";
        }
    }

}
