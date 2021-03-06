/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.converter.impl;

import static de.tuebingen.uni.sfs.lapps.constants.LifConstants.VIEW_ELEMENT_INDEX;
import static de.tuebingen.uni.sfs.lapps.constants.LifConstants.VIEW_REFERENCE_INDICATOR;
import eu.clarin.weblicht.wlfxb.tc.api.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LifCharOffsetToTcfIDBuilder {

    private static Map<String, Token> lifTokenIdToTcfToken = new HashMap<String, Token>();
    private static Map<Long, Token> lifStartIdToTcfToken = new HashMap<Long, Token>();

    public LifCharOffsetToTcfIDBuilder(Map<String, Token> lifTokenIdToTcfToken, Map<Long, Token> lifStartIdToTcfToken) {
        this.lifTokenIdToTcfToken = lifTokenIdToTcfToken;
        this.lifStartIdToTcfToken = lifStartIdToTcfToken;
    }

    public List<Token> getTcfTokens(Long start, Long end) {
        List<Token> tokens = new ArrayList<Token>();
        if (start != null && end != null) {
            for (Long charId = start; charId < end; charId++) {
                if (this.lifStartIdToTcfToken.containsKey(charId)) {
                    Token token = this.lifStartIdToTcfToken.get(charId);
                    tokens.add(token);
                }
            }
        }
        return tokens;
    }

    public Token getTcfToken(String tokenId) {
        if (tokenId.contains(VIEW_REFERENCE_INDICATOR)) {
            String ids[] = tokenId.split(VIEW_REFERENCE_INDICATOR);
            tokenId = ids[VIEW_ELEMENT_INDEX];
        }
        return lifTokenIdToTcfToken.get(tokenId);
    }

    public Token getTcfToken(Long start) {
        return lifStartIdToTcfToken.get(start);
    }

    public Map<String, Token> getLifTokenIdTcfToken() {
        return lifTokenIdToTcfToken;
    }

}
