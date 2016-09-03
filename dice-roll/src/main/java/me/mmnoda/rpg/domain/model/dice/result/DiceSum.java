package me.mmnoda.rpg.domain.model.dice.result;

import com.google.common.collect.Range;
import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;

import java.math.BigInteger;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll.newDifferenceOfRoll;
import static me.mmnoda.rpg.domain.model.dice.result.OverallValue.newOverallValue;

/**
 *
 */
public class DiceSum implements Comparable<DiceSum> {
    public static final DiceSum ZERO = valueOf(0);

    private static final Range<DiceSum> NATURAL_CRITICAL_SUCCESS = Range.closed(valueOf(3), valueOf(4));
    private static final Range<DiceSum> PROBABLE_CRITICAL_SUCCESS = Range.closed(valueOf(5), valueOf(6));
    private static final DiceSum NATURAL_CRITICAL_MISS = valueOf(18);
    private static final DiceSum PROBABLE_CRITICAL_MISS = valueOf(17);
    private static final DiceSum FIVE = valueOf(5);

    private final BigInteger value;

    private DiceSum(BigInteger value) {
        this.value = value;
    }

    public static DiceSum newDiceSum(BigInteger value) {
        return new DiceSum(value);
    }

    public static DiceSum valueOf(long value) {
        return newDiceSum(BigInteger.valueOf(value));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DiceSum) {
            final DiceSum other = (DiceSum) obj;
            return Objects.equals(this.value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("value", value)
                .toString();
    }

    public BigInteger toBigInteger() {
        return value;
    }

    public DiceSum add(SingleRollResult rollResult) {
        return newDiceSum(value.add(rollResult.toBigInteger()));
    }

    public OverallValue calculateOverall(DiceAdjustment adjustment) {
        return newOverallValue(value.add(adjustment.toBigInteger()));
    }

    public boolean isNaturalCriticalSuccess() {
        return NATURAL_CRITICAL_SUCCESS.contains(this);
    }

    public boolean isNaturalCriticalMiss() {
        return NATURAL_CRITICAL_MISS.equals(this);
    }

    public boolean isCriticalMiss(EffectiveValue effectiveValue) {
        return effectiveValue.isLessThan16() && PROBABLE_CRITICAL_MISS.equals(this);
    }

    public boolean isCriticalSuccess(EffectiveValue effectiveValue) {
        return PROBABLE_CRITICAL_SUCCESS.contains(this) && (effectiveValue.isGreaterEquals16() ||
                (effectiveValue.isGreaterEquals15() && FIVE.equals(this)));
    }

    public DifferenceOfRoll calculateDifference(EffectiveValue effectiveValue) {
        return newDifferenceOfRoll(effectiveValue.toBigInteger().subtract(value));
    }

    @Override
    public int compareTo(DiceSum o) {
        return value.compareTo(o.value);
    }
}
