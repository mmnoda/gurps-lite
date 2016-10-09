package me.mmnoda.rpg.domain.model.action;

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

import me.mmnoda.rpg.domain.model.action.critical.CriticalStatus;
import me.mmnoda.rpg.domain.model.action.critical.determination.CriticalDetermination;
import me.mmnoda.rpg.domain.model.action.factory.ActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.factory.DefaultActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.result.ActionRollResult;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class DefaultActionRoller implements ActionRoller {

    private final DiceRepresentation diceRepresentation;
    private final CriticalDetermination criticalDetermination;

    private DefaultActionRoller(ActionRollerBuilder builder) {
        this.diceRepresentation = builder.getRollables();
        this.criticalDetermination = builder.getCriticalDetermination();
    }

    public static DefaultActionRoller of(ActionRollerBuilder builder) {
        return new DefaultActionRoller(builder);
    }

    public static DefaultActionRoller ofDefault() {
        return new DefaultActionRoller(DefaultActionRollerBuilder.INSTANCE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diceRepresentation, criticalDetermination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof DefaultActionRoller) {
            final DefaultActionRoller other = (DefaultActionRoller) obj;
            return Objects.equals(this.diceRepresentation, other.diceRepresentation)
                    && Objects.equals(this.criticalDetermination, other.criticalDetermination);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("diceRepresentation", diceRepresentation)
                .add("criticalDetermination", criticalDetermination)
                .toString();
    }

    @Override
    public ActionRollResult roll(EffectiveValue effectiveValue) {
        final RollResultSum resultSum = roll3D();
        final DifferenceOfRoll differenceOfRoll = resultSum.calculateDifference(effectiveValue);
        final CriticalStatus criticalStatus = determineCriticalStatus(effectiveValue, resultSum, differenceOfRoll);

        return ActionRollResult.builder()
                .withCriticalStatus(criticalStatus)
                .withDifferenceOfRoll(differenceOfRoll)
                .withEffectiveValue(effectiveValue)
                .withRollResultSum(resultSum)
                .build();
    }

    private CriticalStatus determineCriticalStatus(EffectiveValue effectiveValue, RollResultSum resultSum, DifferenceOfRoll differenceOfRoll) {
        return criticalDetermination.determine(effectiveValue, resultSum, differenceOfRoll);
    }

    private RollResultSum roll3D() {
        return diceRepresentation.roll();
    }
}
