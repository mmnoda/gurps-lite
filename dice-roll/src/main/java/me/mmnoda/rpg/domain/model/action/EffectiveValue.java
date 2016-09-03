package me.mmnoda.rpg.domain.model.action;

import com.google.common.base.Objects;
import com.google.common.collect.Range;

import java.math.BigInteger;

/**
 *
 */
public class EffectiveValue implements Comparable<EffectiveValue> {

    private static final Range<EffectiveValue> AT_LEAST_FIFTEEN = Range.atLeast(valueOf(15));
    private static final Range<EffectiveValue> AT_LEAST_SIXTEEN = Range.atLeast(valueOf(16));
    private static final Range<EffectiveValue> LESS_THAN_16 = Range.lessThan(valueOf(16));

    private final BigInteger value;

    private EffectiveValue(BigInteger value) {
        this.value = value;
    }

    public static EffectiveValue newEffectiveValue(BigInteger value) {
        return new EffectiveValue(value);
    }

    public static EffectiveValue valueOf(long value) {
        return newEffectiveValue(BigInteger.valueOf(value));
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
        if (obj instanceof EffectiveValue) {
            final EffectiveValue other = (EffectiveValue) obj;
            return Objects.equal(this.value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BigInteger toBigInteger() {
        return value;
    }

    public boolean isGreaterEquals15() {
        return AT_LEAST_FIFTEEN.contains(this);
    }

    public boolean isGreaterEquals16() {
        return AT_LEAST_SIXTEEN.contains(this);
    }

    public boolean isLessThan16() {
        return LESS_THAN_16.contains(this);
    }

    @Override
    public int compareTo(EffectiveValue o) {
        return value.compareTo(o.value);
    }
}
