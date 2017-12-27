package me.mmnoda.gurps.lite.domain.model.rollable.dice_representation;

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

import java.util.Comparator;

import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfDices;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfFaces;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
public interface DiceRepresentation extends Comparable<DiceRepresentation> {

    RollResultSum roll();

    NumberOfFaces getNumberOfFaces();

    DiceAdjustment getDiceAdjustment();

    NumberOfDices getNumberOfDices();

    @Override
    default int compareTo(final DiceRepresentation o) {
        final Comparator<DiceRepresentation> comparator = Comparator.comparing(DiceRepresentation::getNumberOfDices);

        return comparator
                .thenComparing(Comparator.comparing(DiceRepresentation::getNumberOfFaces))
                .thenComparing(Comparator.comparing(DiceRepresentation::getDiceAdjustment))
                .compare(this, o);
    }
}
