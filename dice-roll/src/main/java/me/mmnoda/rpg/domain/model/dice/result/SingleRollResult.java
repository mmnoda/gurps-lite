package me.mmnoda.rpg.domain.model.dice.result;

import com.google.common.base.Objects;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;

import java.math.BigInteger;

/**
 *
 */
public class SingleRollResult {

    private final BigInteger value;

    private SingleRollResult(BigInteger value) {
        this.value = value;
    }

    public static SingleRollResult valueOf(int value) {
        return newSingleRollResult(BigInteger.valueOf(value));
    }

    public static SingleRollResult valueOf(NumberOfFaces numberOfFaces) {
        return valueOf(numberOfFaces.intValue());
    }

    public static SingleRollResult newSingleRollResult(BigInteger value) {
        return new SingleRollResult(value);
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
        if (obj instanceof SingleRollResult) {
            final SingleRollResult other = (SingleRollResult) obj;
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
}
