package me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result;

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
import me.mmnoda.gurps.lite.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.gurps.lite.domain.model.damage.ArmorDivisor;
import me.mmnoda.gurps.lite.domain.model.damage.Damage;
import me.mmnoda.gurps.lite.domain.model.damage.DamageType;
import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.dice.result.DiceSum;
import me.mmnoda.gurps.lite.domain.model.dice.result.SingleRollResult;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.*;

/**
 *
 */
public final class RollResultSum implements Serializable, Formattable, Comparable<RollResultSum> {

    private final Multiplier multiplier;
    private final DiceSum sum;
    private final DiceAdjustment adjustment;
    private final OverallRollSumValue overall;

    private RollResultSum(Builder builder) {
        this.adjustment = builder.adjustment;
        this.sum = builder.sum;
        this.overall = calculateOverall();
        this.multiplier = Multiplier.NONE;
    }

    private RollResultSum(final RollResultSum original, final Multiplier newMultiplier) {
        this.multiplier = newMultiplier;
        this.sum = original.sum;
        this.adjustment = original.adjustment;
        this.overall = newMultiplier.calculate(original.calculateOverall());
    }

    private RollResultSum(DiceAdjustment adjustment, SingleRollResult... results) {
        checkArgument(results.length > 0);
        DiceSum sum = DiceSum.ZERO;
        for (SingleRollResult result : results) {
            sum = sum.add(result);
        }

        this.sum = sum;
        this.multiplier = Multiplier.NONE;
        this.adjustment = adjustment;
        this.overall = calculateOverall();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static RollResultSum of(SingleRollResult... results) {
        return new RollResultSum(DiceAdjustment.ZERO, results);
    }

    public static RollResultSum of(DiceAdjustment adjustment, SingleRollResult... results) {
        return new RollResultSum(adjustment, results);
    }

    private OverallRollSumValue calculateOverall() {
        return sum.calculateOverall(adjustment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(overall, adjustment, sum, multiplier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof RollResultSum) {
            final RollResultSum other = (RollResultSum) obj;
            return Objects.equals(this.overall, other.overall)
                    && Objects.equals(this.adjustment, other.adjustment)
                    && Objects.equals(this.sum, other.sum)
                    && Objects.equals(this.multiplier, other.multiplier);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("sum", sum)
                .add("adjustment", adjustment)
                .add("overall", overall)
                .toString();
    }

    public RollResultSum doubleValue() {
        checkState(multiplier == Multiplier.NONE);
        return new RollResultSum(this, Multiplier.DOUBLE);
    }

    public RollResultSum tripleValue() {
        checkState(multiplier == Multiplier.NONE);
        return new RollResultSum(this, Multiplier.TRIPLE);
    }

    public DifferenceOfRoll calculateDifference(EffectiveValue effectiveValue) {
        return overall.calculateDifference(effectiveValue);
    }

    public boolean isNaturalCriticalSuccess() {
        return sum.isNaturalCriticalSuccess();
    }

    public boolean isNaturalCriticalMiss() {
        return sum.isNaturalCriticalMiss();
    }

    public boolean isCriticalMiss(EffectiveValue effectiveValue) {
        return overall.isCriticalMiss(effectiveValue);
    }

    public boolean isCriticalSuccess(EffectiveValue effectiveValue) {
        return overall.isCriticalSuccess(effectiveValue);
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        formatter.format(multiplier.format, sum, adjustment, overall);
    }

    @Override
    public int compareTo(RollResultSum o) {
        return this.overall.compareTo(o.overall);
    }

    public Damage toDamage(final DamageType type, final ArmorDivisor armorDivisor) {
        return overall.toDamage(type, armorDivisor);
    }

    public OverallRollSumValue getOverall() {
        return overall;
    }

    public static final class Builder {
        private DiceAdjustment adjustment = DiceAdjustment.ZERO;
        private DiceSum sum = DiceSum.ZERO;

        private Builder() {}

        public Builder withAdjustment(final DiceAdjustment adjustment) {
            checkNotNull(adjustment);
            this.adjustment = adjustment;
            return this;
        }

        public Builder add(final SingleRollResult result) {
            validate(result);
            sum = sum.add(result);
            return this;
        }

        private void validate(SingleRollResult result) {
            checkNotNull(result);
        }

        public RollResultSum build() {
            checkState(sum.isNotZero(), "There are no results");
            return new RollResultSum(this);
        }
    }

    private enum Multiplier {

        NONE("%s%s = %s") {
            @Override
            OverallRollSumValue calculate(OverallRollSumValue overallValue) {
                return overallValue;
            }
        },

        DOUBLE("[%s%s] * 2 = %s") {
            @Override
            OverallRollSumValue calculate(OverallRollSumValue overallValue) {
                return overallValue.doubleValue();
            }
        },

        TRIPLE("[%s%s] * 3 = %s") {
            @Override
            OverallRollSumValue calculate(OverallRollSumValue overallValue) {
                return overallValue.tripleValue();
            }
        };

        private final String format;

        Multiplier(final String format) {
            this.format = format;
        }

        abstract OverallRollSumValue calculate(final OverallRollSumValue overallValue);

    }
}
