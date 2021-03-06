/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.converter.impl;

import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifDependencyParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifMarkable;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifReference;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifReferenceLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl.LifAnnotationMapper;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.impl.LifDependencyInfo;
import de.tuebingen.uni.sfs.lapps.exceptions.LifException;
import eu.clarin.weblicht.wlfxb.tc.api.Constituent;
import eu.clarin.weblicht.wlfxb.tc.api.ConstituentParse;
import eu.clarin.weblicht.wlfxb.tc.api.ConstituentParsingLayer;
import eu.clarin.weblicht.wlfxb.tc.api.Dependency;
import eu.clarin.weblicht.wlfxb.tc.api.DependencyParsingLayer;
import eu.clarin.weblicht.wlfxb.tc.api.NamedEntitiesLayer;
import eu.clarin.weblicht.wlfxb.tc.api.SentencesLayer;
import eu.clarin.weblicht.wlfxb.tc.api.TextSourceLayer;
import eu.clarin.weblicht.wlfxb.tc.api.Token;
import eu.clarin.weblicht.wlfxb.tc.api.TokensLayer;
import eu.clarin.weblicht.wlfxb.tc.xb.TextCorpusStored;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import de.tuebingen.uni.sfs.lapps.exceptions.ConversionException;
import eu.clarin.weblicht.wlfxb.tc.api.Reference;
import eu.clarin.weblicht.wlfxb.tc.api.ReferencesLayer;
import de.tuebingen.uni.sfs.lapps.exceptions.VocabularyMappingException;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifConstituentParser;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifNameEntity;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifNameEntityLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifSentenceLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifTokenLayer;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.anno.api.LifTokenPosLemma;
import de.tuebingen.uni.sfs.lapps.core.lifwrapper.profiler.api.LifFormat;
import de.tuebingen.uni.sfs.lapps.constants.TcfConstants;
import static de.tuebingen.uni.sfs.lapps.constants.TcfConstants.ANAPHORIC;
import static de.tuebingen.uni.sfs.lapps.constants.TcfConstants.TCF_NAMED_ENTITIES_TYPE_OPENNLP;
import de.tuebingen.uni.sfs.lapps.utils.DuplicateChecker;
import eu.clarin.weblicht.wlfxb.tc.api.LemmasLayer;
import eu.clarin.weblicht.wlfxb.tc.api.PosTagsLayer;
import java.util.concurrent.CopyOnWriteArrayList;
import de.tuebingen.uni.sfs.lapps.core.converter.api.LayersConverter;
import de.tuebingen.uni.sfs.lapps.constants.ErrorMessage;

/**
 *
 * @author felahi
 */
public class LifToTcfAnnoLayerConverter implements LayersConverter {

    private TextCorpusStored textCorpusStored = null;
    private LifCharOffsetToTcfIDBuilder lifTokenToTcfTokenIdMapper = null;

    public LifToTcfAnnoLayerConverter(LifFormat lappsLifFormat) throws ConversionException, LifException, VocabularyMappingException {
        textCorpusStored = new TextCorpusStored(toTcfLanguage(lappsLifFormat.getLanguage()));
        if (lappsLifFormat.getText() != null) {
            toTcfText(lappsLifFormat.getText());
        }
        if (lappsLifFormat.getLifTokenLayer() != null) {
            toTcfToken(lappsLifFormat.getLifTokenLayer());
        }
        if (lappsLifFormat.getLifSentenceLayer() != null) {
            toTcfSentences(lappsLifFormat.getLifSentenceLayer());
        }
        if (lappsLifFormat.getLifNameEntityLayer() != null) {
            this.toTcfNameEntity(lappsLifFormat.getLifNameEntityLayer());
        }
        if (lappsLifFormat.getLifDependencyParser() != null) {
            toTcfDependencyParser(lappsLifFormat.getLifDependencyParser());
        }
        if (lappsLifFormat.getLifConstituentParser() != null) {
            this.toTcfConstituentParser(lappsLifFormat.getLifConstituentParser());
        }
        if (lappsLifFormat.getLifRefererenceLayer() != null) {
            this.toTcfCoreferenceResolver(lappsLifFormat.getLifRefererenceLayer());
        }
        /*if (lappsLifFormat.getFileString() != null) {
            toTcfTextSource(lappsLifFormat.getFileString());
        }*/
    }

    @Override
    public String toTcfLanguage(String language) throws ConversionException {
        if (language != null) {
            if (language.equals("en")) {
                return TcfConstants.LANG_EN;
            }
        }
        return TcfConstants.LANG_EN;
    }

    @Override
    public String toTcfText(String text) throws ConversionException {
        String modifiedText = text.replaceAll("\n", "");
        try {
            textCorpusStored.createTextLayer().addText(modifiedText);
        } catch (Exception ex) {
            Logger.getLogger(LifToTcfAnnoLayerConverter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConversionException(ErrorMessage.Conversion.MESSAGE_TEXT_CONVERSION_FAILED);
        }
        return modifiedText;
    }

    /*@Override
    public void toTcfTextSource(String fileString) throws ConversionException {
        try {
            fileString = JsonPrettyPrint.formatJsonString(fileString);
            TextSourceLayer textSourceLayer = textCorpusStored.createTextSourceLayer();
            textSourceLayer.addText(fileString);
        } catch (Exception ex) {
            Logger.getLogger(ConvertToTcfFormat.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConversionException(ex.getMessage());
        }
    }*/
    @Override
    public TokensLayer toTcfToken(LifTokenLayer lifTokenLayer) throws ConversionException {
        TokensLayer tcfTokensLayer = null;
        PosTagsLayer tcfPosLayer = null;
        LemmasLayer tcfLemmaLayer = null;
        DuplicateChecker duplicateChecker = new DuplicateChecker();
        Map<String, Token> lifTokenIdToTcfToken = new HashMap<String, Token>();
        Map<Long, Token> lifStartIdToTcfToken = new HashMap<Long, Token>();

        if (lifTokenLayer.isTokenLayer()) {
            tcfTokensLayer = textCorpusStored.createTokensLayer();
        }
        if (lifTokenLayer.isPosLayer()) {
            tcfPosLayer = textCorpusStored.createPosTagsLayer(TcfConstants.TCF_POSTAGS_TAGSET_PENNTB);
        }
        if (lifTokenLayer.isLemmaLayer()) {
            tcfLemmaLayer = textCorpusStored.createLemmasLayer();
        }

        for (LifTokenPosLemma lifToken : lifTokenLayer.getTokenList()) {
            Token token = null;
            if (!lifToken.getFeatures().isEmpty()) {
                if (!duplicateChecker.isDuplicate(lifToken.getStart())) {
                    if (lifTokenLayer.isTokenLayer()) {
                        token = tcfTokensLayer.addToken(lifToken.getWord());
                        lifStartIdToTcfToken.put(lifToken.getStart(), token);
                        lifTokenIdToTcfToken.put(lifToken.getId(), token);
                    }

                    if (lifTokenLayer.isPosLayer()) {
                        tcfPosLayer.addTag(lifToken.getPos(), token);
                    }

                    if (lifTokenLayer.isLemmaLayer()) {
                        tcfLemmaLayer.addLemma(lifToken.getLemma(), token);
                    }
                }

            }
        }
        if (lifTokenLayer.isTokenLayer()) {
            lifTokenToTcfTokenIdMapper = new LifCharOffsetToTcfIDBuilder(lifTokenIdToTcfToken, lifStartIdToTcfToken);
        }
        return tcfTokensLayer;
    }

    @Override
    public SentencesLayer toTcfSentences(LifSentenceLayer lifSentenceLayer) throws ConversionException, LifException {

        if (textCorpusStored.getSentencesLayer() != null) {
            return textCorpusStored.getSentencesLayer();
        }

        SentencesLayer sentencesLayer = textCorpusStored.createSentencesLayer();

        if (textCorpusStored.getTokensLayer() != null) {
            for (LifAnnotationMapper lifSentence : lifSentenceLayer.getSentenceList()) {
                List<Token> sentenceTokens = new ArrayList<Token>();
                List<Token> tokens = lifTokenToTcfTokenIdMapper.getTcfTokens(lifSentence.getStart(), lifSentence.getEnd());
                if (tokens.isEmpty()) {
                    throw new ConversionException(ErrorMessage.Conversion.MESSAGE_SENTENCE_BOUNDERY_NOT_GIVEN);
                } else {
                    for (Token token : tokens) {
                        sentenceTokens.add(token);
                    }
                    sentencesLayer.addSentence(sentenceTokens);
                }
            }
        } else {
            throw new ConversionException(ErrorMessage.Conversion.MESSAGE_TOKEN_LAYER_REQUIRED);
        }

        return sentencesLayer;
    }

    @Override
    public NamedEntitiesLayer toTcfNameEntity(LifNameEntityLayer lifNameEntityLayer) throws ConversionException, VocabularyMappingException, LifException {
        NamedEntitiesLayer namedEntitiesLayer = textCorpusStored.createNamedEntitiesLayer(TCF_NAMED_ENTITIES_TYPE_OPENNLP);

        if (textCorpusStored.getTokensLayer() != null) {
            for (LifNameEntity lifNameEntity : lifNameEntityLayer.getNameEntityList()) {
                List<Token> tokens = lifTokenToTcfTokenIdMapper.getTcfTokens(lifNameEntity.getStart(), lifNameEntity.getEnd());
                CopyOnWriteArrayList<Token> tokenList = new CopyOnWriteArrayList<Token>(tokens);
                if (!tokenList.isEmpty()) {
                    String nameEntityKey = lifNameEntity.getCategory();
                    if (tokenList.size() == 1) {
                        namedEntitiesLayer.addEntity(nameEntityKey, tokenList.get(0));
                    } else {
                        namedEntitiesLayer.addEntity(nameEntityKey, tokenList);
                    }
                }
            }
        } else {
            throw new ConversionException(ErrorMessage.Conversion.MESSAGE_TOKEN_LAYER_REQUIRED);
        }
        return namedEntitiesLayer;
    }

    @Override
    public ConstituentParsingLayer toTcfConstituentParser(LifConstituentParser lifConstituentParser) throws ConversionException, LifException {
        ConstituentParsingLayer constituentParsingLayer = textCorpusStored.createConstituentParsingLayer(TcfConstants.TCF_PARSING_TAGSET_PENNTB);

        if (textCorpusStored.getSentencesLayer() != null) {
            //do nothing
        } else {
            if (lifConstituentParser.getSentenceLayer() != null) {
                toTcfSentences(lifConstituentParser.getSentenceLayer());
            } else {
                throw new ConversionException(ErrorMessage.Conversion.MESSAGE_SENTENCE_LAYER_REQUIRED);
            }
        }

        try {
            for (Long parseIndex : lifConstituentParser.getParseIndexs()) {
                ConstituentParse tcfTreeBuild = new LifToTcfTreeBuilder(lifConstituentParser.getRoot(parseIndex),
                        lifConstituentParser.getConstituentEntities(parseIndex),
                        lifTokenToTcfTokenIdMapper,
                        constituentParsingLayer);
                Constituent tcfRoot = tcfTreeBuild.getRoot();
                constituentParsingLayer.addParse(tcfRoot);
            }

        } catch (Exception ex) {
            throw new ConversionException(ex.getMessage());
        }

        return constituentParsingLayer;
    }

    @Override
    public DependencyParsingLayer toTcfDependencyParser(LifDependencyParser lifDependencyParser) throws ConversionException, LifException {
        DependencyParsingLayer dependencyParsingLayer = textCorpusStored.createDependencyParsingLayer(TcfConstants.TCF_DEPPARSING_TAGSET_STANFORD, false, true);

        if (textCorpusStored.getSentencesLayer() != null) {
            //do nothing
        } else {
            if (lifDependencyParser.getSentenceLayer() != null) {
                toTcfSentences(lifDependencyParser.getSentenceLayer());
            } else {
                throw new ConversionException(ErrorMessage.Conversion.MESSAGE_SENTENCE_LAYER_REQUIRED);
            }
        }

        try {

            for (Long parseIndex : lifDependencyParser.getParseIndexs()) {
                if (!lifDependencyParser.getRoot(parseIndex)) {
                    throw new LifException(ErrorMessage.Lif.MESSAGE_LIF_ERROR_DEPENDENCY_PARSER_ROOT_MISSING);
                };
                List<Dependency> tcfDependencyList = new ArrayList<Dependency>();
                for (LifDependencyInfo dependencyEntity : lifDependencyParser.getDependencyEntities(parseIndex)) {
                    Token govonor = null, dependent = null;
                    if (dependencyEntity.getGovIDs() != null && dependencyEntity.getDepIDs() != null) {
                        govonor = this.lifTokenToTcfTokenIdMapper.getTcfToken(dependencyEntity.getGovIDs());
                        dependent = this.lifTokenToTcfTokenIdMapper.getTcfToken(dependencyEntity.getDepIDs());
                        Dependency dependency = dependencyParsingLayer.createDependency(dependencyEntity.getFunc(), dependent, govonor);
                        tcfDependencyList.add(dependency);
                    } else if (dependencyEntity.getDepIDs() != null) {
                        dependent = this.lifTokenToTcfTokenIdMapper.getTcfToken(dependencyEntity.getDepIDs());
                        Dependency dependency = dependencyParsingLayer.createDependency(dependencyEntity.getFunc(), dependent);
                        tcfDependencyList.add(dependency);
                    }

                }
                dependencyParsingLayer.addParse(tcfDependencyList);
            }
        } catch (Exception ex) {
            throw new ConversionException(ErrorMessage.Conversion.MESSAGE_DEPENDENCY_CONVERSION_FAILED);
        }
        return dependencyParsingLayer;
    }

    @Override
    public ReferencesLayer toTcfCoreferenceResolver(LifReferenceLayer lifRefererenceLayer) throws ConversionException, LifException {
        ReferencesLayer refsLayer = textCorpusStored.createReferencesLayer(null, null, null);

        try {
            Map<String, Reference> markIdReference = new HashMap<String, Reference>();
            List<Reference> tcfReferences = new ArrayList<Reference>();
            Map<String, Token> lifTokenIdToken = lifTokenToTcfTokenIdMapper.getLifTokenIdTcfToken();
            for (String lifMarkableId : lifRefererenceLayer.getMarkableAnnotations().keySet()) {
                LifMarkable lifMarkable = lifRefererenceLayer.getMarkableAnnotations().get(lifMarkableId);
                List<String> lifTokenIds = lifMarkable.getTargets();
                List<Token> tcftokens = new ArrayList<Token>();
                for (String lifTokenId : lifTokenIds) {
                    if (lifTokenIdToken.containsKey(lifTokenId)) {
                        tcftokens.add(lifTokenIdToken.get(lifTokenId));
                    }
                }
                Reference reference = refsLayer.createReference(tcftokens);
                markIdReference.put(lifMarkableId, reference);
                tcfReferences.add(reference);
            }

            for (String lifCorferId : lifRefererenceLayer.getCorferenceAnnotations().keySet()) {
                LifReference lifReference = lifRefererenceLayer.getCorferenceAnnotations().get(lifCorferId);
                String repMarkableId = lifReference.getRepresentative();
                if (markIdReference.containsKey(repMarkableId)) {
                    Reference refRep = markIdReference.get(repMarkableId);
                    Reference[] refMentions = new Reference[lifReference.getMentions().size() - 1];
                    Integer index = 0;
                    boolean flag = false;
                    for (String mentionMarkableId : lifReference.getMentions()) {
                        if (!mentionMarkableId.equals(repMarkableId) && markIdReference.containsKey(mentionMarkableId)) {
                            refMentions[index++] = markIdReference.get(mentionMarkableId);
                            flag = true;
                        }

                    }
                    if (flag) {
                        refsLayer.addRelation(refRep, ANAPHORIC, refMentions);
                    }

                }
            }
            refsLayer.addReferent(tcfReferences);
        } catch (Exception ex) {
            Logger.getLogger(LifToTcfAnnoLayerConverter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConversionException(ErrorMessage.Conversion.MESSAGE_COREFERENCE_CONVERSION_FAILED);
        }
        return refsLayer;
    }

    @Override
    public TextCorpusStored getTextCorpusStored() {
        return textCorpusStored;
    }

}
