/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tuebingen.uni.sfs.lapps.core.api.annotations;

import de.tuebingen.uni.sfs.lapps.core.api.annotations.LifNameEntity;
import java.util.List;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public interface LifNameEntityLayer extends LifTokenLayer {

    public List<LifNameEntity> getNameEntityList();

}
