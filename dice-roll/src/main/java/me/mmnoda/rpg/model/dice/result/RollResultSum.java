package me.mmnoda.rpg.model.dice.result;

import com.google.common.base.Objects;
import me.mmnoda.rpg.model.action.EffectiveValue;
import me.mmnoda.rpg.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.model.dice.DiceAdjustment;
import me.mmnoda.rpg.model.dice.component.NumberOfDices;

import java.util.SortedMap;

import static com.google.common.collect.Maps.newTreeMap;
import static me.mmnoda.rpg.model.dice.result.DiceSum.ZERO;

/**
 *
 */
public class RollResultSum {

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
        return Objects.hashCode(results, adjustment, sum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RollResultSum) {
            final RollResultSum other = (RollResultSum) obj;
            return Objects.equal(this.results, other.results) && Objects.equal(this.adjustment, other.adjustment)
                    && Objects.equal(this.sum, other.sum);
        }
        return false;
    }

    public DiceSum getSum() {
        return sum;
    }

    public DifferenceOfRoll calculateDifference(EffectiveValue effectiveValue) {
        return sum.calculateDifference(effectiveValue);
    }

    public OverallValue getOverall() {
        return overall;
    }

    public static class Builder {
        private DiceAdjustment adjustment;
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
