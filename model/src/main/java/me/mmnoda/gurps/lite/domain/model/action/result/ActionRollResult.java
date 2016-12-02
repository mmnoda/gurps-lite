package me.mmnoda.gurps.lite.domain.model.action.result;

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

import me.mmnoda.gurps.lite.domain.model.action.EffectiveValue;
import me.mmnoda.gurps.lite.domain.model.action.critical.CriticalStatus;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class ActionRollResult implements HasIndicatorOfSuccess, Formattable {

    private final EffectiveValue effectiveValue;
    private final RollResultSum rollResultSum;
    private final DifferenceOfRoll differenceOfRoll;
    private final CriticalStatus criticalStatus;
    private final boolean success;

    private ActionRollResult(Builder builder) {
        this.effectiveValue = builder.effectiveValue;
        this.criticalStatus = builder.criticalStatus;
        this.rollResultSum = builder.rollResultSum;
        this.differenceOfRoll = builder.differenceOfRoll;
        this.success = criticalStatus.isSuccess(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(effectiveValue, criticalStatus, rollResultSum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ActionRollResult) {
            final ActionRollResult other = (ActionRollResult) obj;
            return Objects.equals(this.effectiveValue, other.effectiveValue)
                    && Objects.equals(this.criticalStatus, other.criticalStatus)
                    && Objects.equals(this.rollResultSum, other.rollResultSum);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("effectiveValue", effectiveValue)
                .add("criticalStatus", criticalStatus)
                .add("rollResultSum", rollResultSum)
                .add("success", success)
                .toString();
    }

    @Override
    public boolean succeededByDiceSum() {
        return differenceOfRoll.isSucceeded();
    }

    @Override
    public DifferenceOfRoll getDifferenceOfRoll() {
        return differenceOfRoll;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("Dice Result: %s %s", rollResultSum, criticalStatus.format(this));
    }

    public static class Builder {
        private CriticalStatus criticalStatus = CriticalStatus.NORMAL;
        private EffectiveValue effectiveValue;
        private RollResultSum rollResultSum;
        private DifferenceOfRoll differenceOfRoll;

        private Builder() {
        }

        public Builder withEffectiveValue(EffectiveValue effectiveValue) {
            this.effectiveValue = effectiveValue;
            return this;
        }

        public Builder withCriticalStatus(CriticalStatus criticalStatus) {
            this.criticalStatus = criticalStatus;
            return this;
        }

        public Builder withRollResultSum(RollResultSum rollResultSum) {
            this.rollResultSum = rollResultSum;
            return this;
        }

        public Builder withDifferenceOfRoll(DifferenceOfRoll differenceOfRoll) {
            this.differenceOfRoll = differenceOfRoll;
            return this;
        }

        public ActionRollResult build() {
            return new ActionRollResult(this);
        }
    }
}
