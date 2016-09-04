package me.mmnoda.rpg.domain.model.rollable.dice_representation.result;

import com.google.common.collect.ImmutableSortedMap;
import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.result.DiceSum;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;

import java.io.Serializable;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
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
        this.results = ImmutableSortedMap.copyOf(builder.results);
        this.adjustment = builder.adjustment;
        this.sum = builder.sum;
        this.overall = calculateOverall();
    }

    private OverallValue calculateOverall() {
        return sum.calculateOverall(adjustment);
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

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("results", results)
                .add("sum", sum)
                .add("adjustment", adjustment)
                .add("overall", overall)
                .toString();
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
        final StringBuilder result = new StringBuilder().append('(');
        final Iterator<SingleRollResult> singleRollResultsIterator = results.values().iterator();

        appendSingleResultsToFormat(result, singleRollResultsIterator);

        return result.append(')');
    }

    private void appendSingleResultsToFormat(StringBuilder result, Iterator<SingleRollResult> singleRollResultsIterator) {
        while (singleRollResultsIterator.hasNext()) {
            result.append(String.format("%s", singleRollResultsIterator.next()));

            if (singleRollResultsIterator.hasNext()) {
                result.append(" + ");
            }
        }
    }

    @Override
    public int compareTo(RollResultSum o) {
        return this.overall.compareTo(o.overall);
    }

    public static final class Builder {
        private DiceAdjustment adjustment = DiceAdjustment.ZERO;
        private DiceSum sum = ZERO;

        private final SortedMap<NumberOfDices, SingleRollResult> results = newTreeMap();

        private Builder() {
        }

        public Builder withAdjustment(final DiceAdjustment adjustment) {
            this.adjustment = adjustment;
            return this;
        }

        public Builder add(final NumberOfDices numberOfDices, final SingleRollResult result) {
            validate(numberOfDices, result);
            results.put(numberOfDices, result);
            sum = sum.add(result);
            return this;
        }

        private void validate(NumberOfDices numberOfDices, SingleRollResult result) {
            checkNotNull(numberOfDices);
            checkNotNull(result);
        }

        public RollResultSum build() {
            checkState(!results.isEmpty(), "there are not results");
            return new RollResultSum(this);
        }
    }
}
