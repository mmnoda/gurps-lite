package me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.custom;

/*
 * #%L
 * gurps-lite-model
 * %%
 * Copyright (C) 2018 Márcio Noda
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

import me.mmnoda.gurps.lite.domain.model.dice.result.SingleRollResult;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;

/**
 *
 */
public enum CustomDiceRepresentationFactoryMethod {

    INSTANCE;

    public DiceRepresentation buildMaxValue(final DiceRepresentation diceRepresentation) {
        return MaxValueDiceRepresentation.of(diceRepresentation);
    }

    public DiceRepresentation buildMinValue(final DiceRepresentation diceRepresentation) {
        return MinValueDiceRepresentation.of(diceRepresentation);
    }

    public DiceRepresentation buildAvgValue(final DiceRepresentation diceRepresentation) {
        return AvgValueDiceRepresentation.of(diceRepresentation);
    }

    public DiceRepresentation buildHighestValueOf3(final DiceRepresentation diceRepresentation) {
        return HighestValueOfNResultDiceRepresentation.of(diceRepresentation, 3);

    }

    public DiceRepresentation buildLowestValueOf3(final DiceRepresentation diceRepresentation) {
        return LowestValueOfNResultDiceRepresentation.of(diceRepresentation, 3);
    }

    public DiceRepresentation buildManualInput(final DiceRepresentation diceRepresentation, final SingleRollResult first,
                                               final SingleRollResult... expectedResults) {
        return ArbitraryValuesDiceRepresentation.of(diceRepresentation, first, expectedResults);

    }

}
