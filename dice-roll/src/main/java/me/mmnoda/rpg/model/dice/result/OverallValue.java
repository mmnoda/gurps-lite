package me.mmnoda.rpg.model.dice.result;

import com.google.common.base.Objects;

import java.math.BigInteger;

/**
 *
 */
public class OverallValue {

    private final BigInteger value;

    private OverallValue(BigInteger value) {
        this.value = value;
    }

    public static OverallValue newOverallValue(BigInteger value) {
        return new OverallValue(value);
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
        if (obj instanceof OverallValue) {
            final OverallValue other = (OverallValue) obj;
            return Objects.equal(this.value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public OverallValue doubleValue() {
        return newOverallValue(value.multiply(BigInteger.valueOf(2)));
    }

    public OverallValue tripleValue() {
        return newOverallValue(value.multiply(BigInteger.valueOf(3)));
    }
}


