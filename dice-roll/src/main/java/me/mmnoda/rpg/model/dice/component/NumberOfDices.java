package me.mmnoda.rpg.model.dice.component;

import com.google.common.base.Objects;
import me.mmnoda.rpg.model.dice.result.DiceSum;

import java.math.BigInteger;
import java.util.Iterator;

import static me.mmnoda.rpg.model.dice.result.DiceSum.newDiceSum;

/**
 *
 */
public class NumberOfDices implements Comparable<NumberOfDices>, Iterable<NumberOfDices> {

    public final static NumberOfDices THREE = newNumberOfDices(BigInteger.valueOf(3));

    private final static NumberOfDices ZERO = newNumberOfDices(BigInteger.ZERO);

    private final BigInteger quantity;

    private NumberOfDices(BigInteger quantity) {
        this.quantity = quantity;
    }

    public static NumberOfDices newNumberOfDices(BigInteger quantity) {
        return new NumberOfDices(quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NumberOfDices) {
            final NumberOfDices other = (NumberOfDices) obj;
            return Objects.equal(this.quantity, other.quantity);
        }
        return false;
    }

    public DiceSum maxDiceSum(NumberOfFaces numberOfFaces) {
        return newDiceSum(quantity.multiply(numberOfFaces.toBigInteger()));
    }

    public DiceSum minDiceSum() {
        return newDiceSum(quantity);
    }

    @Override
    public String toString() {
        return quantity.toString();
    }

    @Override
    public int compareTo(NumberOfDices o) {
        return quantity.compareTo(o.quantity);
    }

    @Override
    public Iterator<NumberOfDices> iterator() {
        return new NumberOfDicesIterator();
    }

    private NumberOfDices plusOne() {
        return newNumberOfDices(quantity.add(BigInteger.ONE));
    }

    private class NumberOfDicesIterator implements Iterator<NumberOfDices> {

        private NumberOfDices currentValue = ZERO;

        @Override
        public boolean hasNext() {
            return currentValue.compareTo(NumberOfDices.this) < 0;
        }

        @Override
        public NumberOfDices next() {
            return currentValue = currentValue.plusOne();
        }
    }
}
