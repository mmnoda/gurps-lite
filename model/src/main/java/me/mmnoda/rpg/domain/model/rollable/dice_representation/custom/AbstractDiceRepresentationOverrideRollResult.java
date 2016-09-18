package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

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

import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
abstract class AbstractDiceRepresentationOverrideRollResult extends AbstractDiceRepresentationDecorator {

    AbstractDiceRepresentationOverrideRollResult(DiceRepresentation decorated) {
        super(decorated);
        checkNotNull(decorated);
    }

    public final RollResultSum roll() {
        final RollResultSum.Builder builder = getBuilder();
        customizeRoll(builder, getNumberOfDices(), getNumberOfFaces());
        return builder.build();
    }

    private RollResultSum.Builder getBuilder() {
        return RollResultSum
                .builder()
                .withAdjustment(this.getDiceAdjustment());
    }

    protected abstract void customizeRoll(RollResultSum.Builder builder, NumberOfDices numberOfDices, NumberOfFaces numberOfFaces);

}
