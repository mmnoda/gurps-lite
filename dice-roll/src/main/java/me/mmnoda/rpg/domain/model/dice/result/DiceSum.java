package me.mmnoda.rpg.domain.model.dice.result;

import com.google.common.collect.Range;
import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.OverallRollSumValue;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public final class DiceSum implements Comparable<DiceSum>, Formattable {

    public static final DiceSum ZERO = of(0);

    private static final Range<DiceSum> NATURAL_CRITICAL_SUCCESS = Range.closed(of(3), of(4));
    private static final DiceSum NATURAL_CRITICAL_MISS = of(18);

    private final BigInteger value;

    private DiceSum(BigInteger value) {
        this.value = value;
    }

    public static DiceSum of(final BigInteger value) {
        checkNotNull(value);
        return new DiceSum(value);
    }

    public static DiceSum of(long value) {
        return of(BigInteger.valueOf(value));
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

    public DiceSum add(final SingleRollResult rollResult) {
        return of(value.add(rollResult.toBigInteger()));
    }

    public DiceSum half(){
        return of(value.divide(BigInteger.valueOf(2)));
    }

    public OverallRollSumValue calculateOverall(DiceAdjustment adjustment) {
        return OverallRollSumValue.of(value.add(adjustment.toBigInteger()));
    }

    public boolean isNaturalCriticalSuccess() {
        return NATURAL_CRITICAL_SUCCESS.contains(this);
    }

    public boolean isNaturalCriticalMiss() {
        return NATURAL_CRITICAL_MISS.equals(this);
    }

    @Override
    public int compareTo(DiceSum o) {
        return value.compareTo(o.value);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(value.toString());
    }
}
