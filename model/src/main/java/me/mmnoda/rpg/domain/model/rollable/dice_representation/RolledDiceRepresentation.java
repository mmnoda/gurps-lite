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

import java.util.Formattable;
import java.util.Formatter;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Decorator
 */
public final class RolledDiceRepresentation implements DiceRepresentation, Formattable {

    private final DiceRepresentation diceRepresentation;
    private final RollResultSum rollResultSum;

    public RolledDiceRepresentation(DiceRepresentation diceRepresentation, RollResultSum rollResultSum) {
        this.diceRepresentation = diceRepresentation;
        this.rollResultSum = rollResultSum;
    }

    @Override
    public RollResultSum roll() {
        return rollResultSum;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("diceRepresentation", diceRepresentation)
                .add("rollResultSum", rollResultSum)
                .toString();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return diceRepresentation.getNumberOfFaces();
    }

    @Override
    public DiceAdjustment getDiceAdjustment() {
        return diceRepresentation.getDiceAdjustment();
    }

    @Override
    public NumberOfDices getNumberOfDices() {
        return diceRepresentation.getNumberOfDices();
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s => %s", diceRepresentation, rollResultSum);
    }
}
