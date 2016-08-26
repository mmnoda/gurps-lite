package me.mmnoda.rpg.model.dice;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

import java.math.BigInteger;
import java.util.Map;

/**
 *
 */
public class DiceAdjustment implements Comparable<DiceAdjustment> {

    public static final DiceAdjustment ZERO = newDiceAdjustment(BigInteger.ZERO);

    private static final Map<Integer, String> SIGNAL_MAP = ImmutableMap.of(-1, "+", 0, "", 1, "-");

    private final BigInteger value;

    private DiceAdjustment(BigInteger value) {
        this.value = value;
    }

    public static DiceAdjustment newDiceAdjustment(BigInteger value) {
        return new DiceAdjustment(value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DiceAdjustment) {
            final DiceAdjustment other = (DiceAdjustment) obj;
            return Objects.equal(this.value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return SIGNAL_MAP.get(compareTo(ZERO)) + value.toString();
    }

    public BigInteger toBigInteger() {
        return value;
    }

    public boolean hasAValue() {
        return !value.equals(BigInteger.ZERO);
    }

    @Override
    public int compareTo(DiceAdjustment o) {
        return value.compareTo(o.value);
    }
}
