package me.mmnoda.rpg.domain.model.action.result;

import com.google.common.base.Objects;
import com.google.common.collect.Range;

import java.math.BigInteger;

/**
 *
 */
public class DifferenceOfRoll implements Comparable<DifferenceOfRoll> {

    private static final Range<DifferenceOfRoll> AT_MOST_10_NEGATIVE = Range.atMost(valueOf(-10));

    private final BigInteger value;

    private DifferenceOfRoll(BigInteger value) {
        this.value = value;
    }

    public static DifferenceOfRoll newDifferenceOfRoll(BigInteger value) {
        return new DifferenceOfRoll(value);
    }

    public static DifferenceOfRoll valueOf(long value) {
        return newDifferenceOfRoll(BigInteger.valueOf(value));
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
        if (obj instanceof DifferenceOfRoll) {
            final DifferenceOfRoll other = (DifferenceOfRoll) obj;
            return Objects.equal(this.value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isSucceeded() {
        return value.compareTo(BigInteger.ZERO) >= 0;
    }

    public boolean isFailedAt10Negative() {
        return AT_MOST_10_NEGATIVE.equals(this);
    }

    @Override
    public int compareTo(DifferenceOfRoll o) {
        return value.compareTo(o.value);
    }
}
