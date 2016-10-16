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
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class DefaultDiceRepresentation implements DiceRepresentation, Formattable, Comparable<DefaultDiceRepresentation> {

    private final NumberOfDices numberOfDices;
    private final SingleRollable rollable;
    private final DiceAdjustment adjustment;

    private DefaultDiceRepresentation(SingleRollable rollable, NumberOfDices numberOfDices) {
        this(rollable, numberOfDices, DiceAdjustment.ZERO);
    }

    private DefaultDiceRepresentation(SingleRollable rollable, NumberOfDices numberOfDices, DiceAdjustment adjustment) {
        this.numberOfDices = numberOfDices;
        this.rollable = rollable;
        this.adjustment = adjustment;
    }

    public static DefaultDiceRepresentation of(SingleRollable rollable, NumberOfDices numberOfDices) {
        return new DefaultDiceRepresentation(rollable, numberOfDices);
    }

    public static DefaultDiceRepresentation of(SingleRollable rollable, NumberOfDices numberOfDices, DiceAdjustment adjustment) {
        return new DefaultDiceRepresentation(rollable, numberOfDices, adjustment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfDices, rollable, adjustment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof DiceRepresentation) {
            final DefaultDiceRepresentation other = (DefaultDiceRepresentation) obj;
            return Objects.equals(this.numberOfDices, other.numberOfDices) && Objects.equals(this.rollable, other.rollable)
                    && Objects.equals(this.adjustment, other.adjustment);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("numberOfDices", numberOfDices)
                .add("rollable", rollable)
                .add("adjustment", adjustment)
                .toString();
    }

    @Override
    public RollResultSum roll() {
        final RollResultSum.Builder builder = RollResultSum.builder()
                .withAdjustment(adjustment);

        for (NumberOfDices numberOfDice : numberOfDices) {
            builder.add(rollable.roll(numberOfDice));
        }
        return builder.build();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return rollable.getNumberOfFaces();
    }

    @Override
    public DiceAdjustment getDiceAdjustment() {
        return adjustment;
    }

    @Override
    public NumberOfDices getNumberOfDices() {
        return numberOfDices;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        final StringBuilder result = processFormatTo();
        formatter.format(result.toString());
    }

    private StringBuilder processFormatTo() {
        final StringBuilder result = new StringBuilder()
                .append(String.format("%s", numberOfDices))
                .append('d');

        if (adjustment.hasAValue()) {
            result.append(String.format("%s", adjustment));
        }
        return result;
    }

    @Override
    public int compareTo(DefaultDiceRepresentation o) {
        final Comparator<DefaultDiceRepresentation> comparator = (DefaultDiceRepresentation d1, DefaultDiceRepresentation d2) ->
                d1.numberOfDices.compareTo(d2.numberOfDices);

        return comparator
                .thenComparing((DefaultDiceRepresentation d1, DefaultDiceRepresentation d2) -> d1.getNumberOfFaces().compareTo(d2.getNumberOfFaces()))
                .thenComparing((DefaultDiceRepresentation d1, DefaultDiceRepresentation d2) -> d1.adjustment.compareTo(d2.adjustment))
                .compare(this, o);
    }
}