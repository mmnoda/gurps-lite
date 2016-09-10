package me.mmnoda.rpg.domain.model.action;

import com.google.common.collect.Range;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public final class EffectiveValue implements Comparable<EffectiveValue>, Formattable {

    public static final EffectiveValue TEN = of(10);

    private static final Range<EffectiveValue> AT_LEAST_FIFTEEN = Range.atLeast(of(15));
    private static final Range<EffectiveValue> AT_LEAST_SIXTEEN = Range.atLeast(of(16));
    private static final Range<EffectiveValue> LESS_THAN_16 = Range.lessThan(of(16));

    private final BigInteger value;

    private EffectiveValue(BigInteger value) {
        this.value = value;
    }

    public static EffectiveValue of(BigInteger value) {
        checkNotNull(value);
        return new EffectiveValue(value);
    }

    public static EffectiveValue of(long value) {
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
        if (obj instanceof EffectiveValue) {
            final EffectiveValue other = (EffectiveValue) obj;
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

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(value.toString());
    }
}
