package me.mmnoda.rpg.domain.model.rollable.dice_representation.result;

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.result.DiceSum;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.*;
import static me.mmnoda.rpg.domain.model.dice.result.DiceSum.ZERO;

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
        DiceSum sum = ZERO;
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

    public RollResultSum doubleValue() {
        return new RollResultSum(this, Multiplier.DOUBLE);
    }

    public RollResultSum tripleValue() {
        return new RollResultSum(this, Multiplier.TRIPLE);
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

    public OverallRollSumValue getOverall() {
        return overall;
    }

    public static final class Builder {
        private DiceAdjustment adjustment = DiceAdjustment.ZERO;
        private DiceSum sum = ZERO;

        private Builder() {
        }

        public Builder withAdjustment(final DiceAdjustment adjustment) {
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
            checkState(sum.isNotZero(), "There are not results");
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
