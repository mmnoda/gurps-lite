package me.mmnoda.rpg.domain.model.action.result;

import com.google.common.collect.Range;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class DifferenceOfRoll implements Comparable<DifferenceOfRoll>, Formattable {

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
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DifferenceOfRoll) {
            final DifferenceOfRoll other = (DifferenceOfRoll) obj;
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

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(value.toString());
    }
}
