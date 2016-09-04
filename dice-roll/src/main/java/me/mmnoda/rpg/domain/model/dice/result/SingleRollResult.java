package me.mmnoda.rpg.domain.model.dice.result;

import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class SingleRollResult implements Serializable, Formattable {

    private final BigInteger value;

    private SingleRollResult(BigInteger value) {
        this.value = value;
    }

    public static SingleRollResult of(int value) {
        return of(BigInteger.valueOf(value));
    }

    public static SingleRollResult of(NumberOfFaces numberOfFaces) {
        return of(numberOfFaces.intValue());
    }

    public static SingleRollResult of(BigInteger value) {
        return new SingleRollResult(value);
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

        if (obj instanceof SingleRollResult) {
            final SingleRollResult other = (SingleRollResult) obj;
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

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(value.toString());
    }
}
