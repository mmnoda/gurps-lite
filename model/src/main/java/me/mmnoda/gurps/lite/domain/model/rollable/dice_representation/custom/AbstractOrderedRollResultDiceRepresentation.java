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

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.SortedSet;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 */
abstract class AbstractOrderedRollResultDiceRepresentation extends AbstractDiceRepresentationDecorator  {

    private final int numberOfRolls;

    AbstractOrderedRollResultDiceRepresentation(DiceRepresentation decorated, int numberOfRolls) {
        super(decorated);
        checkArgument(numberOfRolls > 0);
        this.numberOfRolls = numberOfRolls;
    }

    @Override
    public RollResultSum roll() {
        final SortedSet<RollResultSum> rolls = Sets.newTreeSet();

        for (int rollNumber = 1; rollNumber <= numberOfRolls; rollNumber++) {
            rolls.add(decorated.roll());
        }

        return getSelectedRoll(ImmutableSortedSet.copyOf(rolls));
    }

    protected abstract RollResultSum getSelectedRoll(SortedSet<RollResultSum> results);

}
