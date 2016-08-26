package me.mmnoda.rpg.model.action.result;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import me.mmnoda.rpg.model.action.EffectiveValue;
import me.mmnoda.rpg.model.action.critical.CriticalStatus;
import me.mmnoda.rpg.model.dice.result.RollResultSum;

/**
 *
 */
public class ActionRollResult implements HasIndicatorOfSuccess {

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
        return Objects.hashCode(effectiveValue, criticalStatus, rollResultSum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActionRollResult) {
            final ActionRollResult other = (ActionRollResult) obj;
            return Objects.equal(this.effectiveValue, other.effectiveValue) && Objects.equal(this.criticalStatus, other.criticalStatus) &&
                    Objects.equal(this.rollResultSum, other.rollResultSum);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
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

    public boolean isSuccess() {
        return success;
    }

    public static class Builder {
        private EffectiveValue effectiveValue;
        private CriticalStatus criticalStatus;
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
