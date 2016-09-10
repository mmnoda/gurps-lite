package me.mmnoda.rpg.domain.model.dice.result;

import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public final class SingleRollResult implements Serializable, Formattable, Comparable<SingleRollResult> {

    public static final SingleRollResult ONE = new SingleRollResult(NumberOfDices.ONE, NumberOfFaces._6, BigInteger.ONE);

    private final NumberOfDices numberOfDices;

    private final NumberOfFaces numberOfFaces;

    private final BigInteger value;

    private SingleRollResult(NumberOfDices numberOfDices, NumberOfFaces numberOfFaces, BigInteger value) {
        this.numberOfDices = numberOfDices;
        this.numberOfFaces = numberOfFaces;
        this.value = value;
    }

    public static SingleRollResult of(final NumberOfDices numberOfDices, final NumberOfFaces numberOfFaces, final BigInteger value) {
        validate(numberOfDices, numberOfFaces, value);
        return new SingleRollResult(numberOfDices, numberOfFaces, value);
    }

    public static SingleRollResult of(final NumberOfDices numberOfDices, final NumberOfFaces numberOfFaces, final int value) {
        return of(numberOfDices, numberOfFaces, BigInteger.valueOf(value));
    }

    public static SingleRollResult of(final NumberOfFaces numberOfFaces, final BigInteger value) {
        final NumberOfDices one = NumberOfDices.ONE;
        validate(one, numberOfFaces, value);
        return new SingleRollResult(one, numberOfFaces, value);
    }

    public static SingleRollResult of(final BigInteger value) {
        return of(NumberOfFaces._6, value);
    }

    public static SingleRollResult of(final NumberOfFaces numberOfFaces, final int value) {
        return of(numberOfFaces, BigInteger.valueOf(value));
    }

    public static SingleRollResult of(final int value) {
        return of(NumberOfFaces._6, BigInteger.valueOf(value));
    }

    private static void validate(NumberOfDices numberOfDices, NumberOfFaces numberOfFaces, BigInteger value) {
        checkNotNull(value);
        checkNotNull(numberOfFaces);
        checkNotNull(numberOfDices);
        checkArgument(value.compareTo(BigInteger.ZERO) > 0, "the value must the greater than ZERO");
        checkArgument(value.compareTo(numberOfFaces.toBigInteger()) <= 0, String.format("tha value must be equals or less than %s", numberOfFaces));
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfDices, numberOfFaces, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SingleRollResult) {
            final SingleRollResult other = (SingleRollResult) obj;
            return Objects.equals(this.numberOfDices, other.numberOfDices)
                    && Objects.equals(this.numberOfFaces, other.numberOfFaces)
                    && Objects.equals(this.value, other.value);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("numberOfDices", numberOfDices)
                .add("value", value)
                .toString();
    }

    public SingleRollResult normalize(NumberOfDices newNumberOfDice) {
        return of(newNumberOfDice, numberOfFaces, value);
    }

    public BigInteger toBigInteger() {
        return value;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(value.toString());
    }

    @Override
    public int compareTo(SingleRollResult o) {
        return this.value.compareTo(o.value);
    }
}
