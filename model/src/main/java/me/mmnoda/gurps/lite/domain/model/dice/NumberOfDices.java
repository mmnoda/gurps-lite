package me.mmnoda.gurps.lite.domain.model.dice;

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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.dice.result.DiceSum;
import me.mmnoda.gurps.lite.infrastructure.converter.json.NumberOfDicesJsonDeserializer;
import me.mmnoda.gurps.lite.infrastructure.converter.json.NumberOfDicesJsonSerializer;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
@EqualsAndHashCode(of = "quantity")
@ToString(of = "quantity")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonSerialize(using = NumberOfDicesJsonSerializer.class)
@JsonDeserialize(using = NumberOfDicesJsonDeserializer.class)
public class NumberOfDices implements Serializable, Comparable<NumberOfDices>, Iterable<NumberOfDices>, Formattable {

    private static final NumberOfDices ZERO = of(BigInteger.ZERO);

    public static final NumberOfDices ONE = of(BigInteger.ONE);
    public static final NumberOfDices THREE = of(BigInteger.valueOf(3));

    private final BigInteger quantity;

    public static NumberOfDices of(BigInteger quantity) {
        checkNotNull(quantity);
        return new NumberOfDices(quantity);
    }

    public static NumberOfDices of(long quantity) {
        return of(BigInteger.valueOf(quantity));
    }

    public BigInteger toBigInteger() {
        return quantity;
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
        return of(quantity.add(BigInteger.ONE));
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
