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

import com.google.common.collect.ImmutableMap;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class DiceAdjustment implements Comparable<DiceAdjustment>, Formattable {

    public static final DiceAdjustment ZERO = of(BigInteger.ZERO);

    private static final Map<Integer, String> SIGNAL_MAP = ImmutableMap.of(+1, "+", 0, "", -1, "-");

    private final BigInteger value;

    private DiceAdjustment(BigInteger value) {
        this.value = value;
    }

    public static DiceAdjustment of(BigInteger value) {
        return new DiceAdjustment(value);
    }

    public static DiceAdjustment of(long value) {
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

        if (obj instanceof DiceAdjustment) {
            final DiceAdjustment other = (DiceAdjustment) obj;
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

    public boolean hasAValue() {
        return !value.equals(BigInteger.ZERO);
    }

    @Override
    public int compareTo(DiceAdjustment o) {
        return value.compareTo(o.value);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        if (hasAValue()) {
            formatter.format("%s%s", SIGNAL_MAP.get(compareTo(ZERO)), value.abs().toString());
        }
    }
}
