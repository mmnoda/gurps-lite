package me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.custom;

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

import java.util.Objects;
import java.util.SortedSet;

import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.RollResultSum;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
class LowestValueOfNResultDiceRepresentation extends AbstractOrderedRollResultDiceRepresentation {

    private LowestValueOfNResultDiceRepresentation(DiceRepresentation decorated, int numberOfRolls) {
        super(decorated, numberOfRolls);
    }

    public static LowestValueOfNResultDiceRepresentation of(DiceRepresentation decorated, int numberOfRolls) {
        return new LowestValueOfNResultDiceRepresentation(decorated, numberOfRolls);
    }

    @Override
    protected RollResultSum getSelectedRoll(SortedSet<RollResultSum> results) {
        return results.first();
    }

    @Override
    public int hashCode() {
        return Objects.hash(decorated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof LowestValueOfNResultDiceRepresentation) {
            final LowestValueOfNResultDiceRepresentation other = (LowestValueOfNResultDiceRepresentation) o;
            return Objects.equals(this.decorated, other.decorated);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("decorated", decorated)
                .toString();
    }
}
