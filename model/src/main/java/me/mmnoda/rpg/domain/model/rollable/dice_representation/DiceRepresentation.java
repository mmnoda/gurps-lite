package me.mmnoda.rpg.domain.model.rollable.dice_representation;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2015 - 2016 Márcio Noda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Comparator;

/**
 *
 */
public interface DiceRepresentation extends Comparable<DiceRepresentation> {

    RollResultSum roll();

    NumberOfFaces getNumberOfFaces();

    DiceAdjustment getDiceAdjustment();

    NumberOfDices getNumberOfDices();

    @Override
    default int compareTo(DiceRepresentation o) {
        final Comparator<DiceRepresentation> comparator = (DiceRepresentation d1, DiceRepresentation d2) ->
                d1.getNumberOfDices().compareTo(d2.getNumberOfDices());

        return comparator
                .thenComparing((DiceRepresentation d1, DiceRepresentation d2) -> d1.getNumberOfFaces().compareTo(d2.getNumberOfFaces()))
                .thenComparing((DiceRepresentation d1, DiceRepresentation d2) -> d1.getDiceAdjustment().compareTo(d2.getDiceAdjustment()))
                .compare(this, o);
    }
}
