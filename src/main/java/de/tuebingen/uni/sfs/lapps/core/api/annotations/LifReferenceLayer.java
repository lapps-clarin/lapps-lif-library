/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.api.annotations;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifReference;
import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifMarkable;
import de.tuebingen.uni.sfs.lapps.utils.AnnotationInterpreter;
import java.util.Map;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifReferenceLayer extends LifTokenLayer {

    public Map<String, LifReference> getCorferenceAnnotations();

    public Map<String, LifMarkable> getMarkableAnnotations();

}
