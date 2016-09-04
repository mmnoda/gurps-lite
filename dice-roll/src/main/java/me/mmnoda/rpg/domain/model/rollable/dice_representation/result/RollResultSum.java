package me.mmnoda.rpg.domain.model.rollable.dice_representation.result;

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.result.DiceSum;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;

import java.io.Serializable;
import java.util.*;

import static com.google.common.collect.Maps.newTreeMap;
import static me.mmnoda.rpg.domain.model.dice.result.DiceSum.ZERO;

/**
 *
 */
public final class RollResultSum implements Serializable, Formattable, Comparable<RollResultSum> {

    private final SortedMap<NumberOfDices, SingleRollResult> results;
    private final DiceSum sum;
    private final DiceAdjustment adjustment;
    private final OverallValue overall;

    private RollResultSum(Builder builder) {
        this.results = builder.results;
        this.adjustment = builder.adjustment;
        this.sum = builder.sum;
        this.overall = sum.calculateOverall(adjustment);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, adjustment, sum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RollResultSum) {
            final RollResultSum other = (RollResultSum) obj;
            return Objects.equals(this.results, other.results)
                    && Objects.equals(this.adjustment, other.adjustment)
                    && Objects.equals(this.sum, other.sum);
        }
        return false;
    }

    public DifferenceOfRoll calculateDifference(EffectiveValue effectiveValue) {
        return overall.calculateDifference(effectiveValue);
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        final StringBuilder result = processFormatTo();
        formatter.format("%s%s = %s", result, adjustment, overall);
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

    private StringBuilder processFormatTo() {
        final StringBuilder result = new StringBuilder('(');
        final Iterator<SingleRollResult> singleRollResultsIterator = results.values().iterator();

        while (singleRollResultsIterator.hasNext()) {
            result.append(String.format("%s", singleRollResultsIterator.next()));

            if (singleRollResultsIterator.hasNext()) {
                result.append('+');
            }
        }

        return result.append(')');
    }

    @Override
    public int compareTo(RollResultSum o) {
        return this.overall.compareTo(o.overall);
    }

    public static class Builder {
        private DiceAdjustment adjustment = DiceAdjustment.ZERO;
        private DiceSum sum = ZERO;

        private final SortedMap<NumberOfDices, SingleRollResult> results = newTreeMap();

        private Builder() {
        }

        public Builder withAdjustment(DiceAdjustment adjustment) {
            this.adjustment = adjustment;
            return this;
        }

        public void add(NumberOfDices numberOfDices, SingleRollResult result) {
            results.put(numberOfDices, result);
            sum = sum.add(result);
        }

        public RollResultSum build() {
            return new RollResultSum(this);
        }
    }
}
