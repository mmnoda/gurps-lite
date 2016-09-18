package me.mmnoda.rpg.domain.model.dice;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2015 - 2016 Márcio Noda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import me.mmnoda.rpg.domain.model.dice.result.DiceSum;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class NumberOfDices implements Comparable<NumberOfDices>, Iterable<NumberOfDices>, Formattable {

    private static final NumberOfDices ZERO = newNumberOfDices(BigInteger.ZERO);
    public static final NumberOfDices ONE = newNumberOfDices(BigInteger.ONE);
    public static final NumberOfDices THREE = newNumberOfDices(BigInteger.valueOf(3));

    private final BigInteger quantity;

    private NumberOfDices(BigInteger quantity) {
        this.quantity = quantity;
    }

    public static NumberOfDices newNumberOfDices(BigInteger quantity) {
        checkNotNull(quantity);
        return new NumberOfDices(quantity);
    }

    public static NumberOfDices of(long quantity) {
        return newNumberOfDices(BigInteger.valueOf(quantity));
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof NumberOfDices) {
            final NumberOfDices other = (NumberOfDices) obj;
            return Objects.equals(this.quantity, other.quantity);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("quantity", quantity)
                .toString();
    }

    public DiceSum maxDiceSum(final NumberOfFaces numberOfFaces) {
        return DiceSum.of(numberOfFaces.toBigInteger());
    }

    public DiceSum minDiceSum() {
        return DiceSum.of(1);
    }

    public DiceSum avgDiceSum(final NumberOfFaces numberOfFaces) {
        return maxDiceSum(numberOfFaces).half();
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

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(quantity.toString());
    }

    private final class NumberOfDicesIterator implements Iterator<NumberOfDices> {

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
