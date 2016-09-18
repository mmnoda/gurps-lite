package me.mmnoda.rpg.domain.model.action.result;

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

import com.google.common.collect.Range;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public final class DifferenceOfRoll implements Comparable<DifferenceOfRoll>, Formattable {

    private static final Range<DifferenceOfRoll> AT_MOST_10_NEGATIVE = Range.atMost(of(-10));

    private final BigInteger value;

    private DifferenceOfRoll(BigInteger value) {
        this.value = value;
    }

    public static DifferenceOfRoll newDifferenceOfRoll(BigInteger value) {
        return new DifferenceOfRoll(value);
    }

    public static DifferenceOfRoll of(long value) {
        return newDifferenceOfRoll(BigInteger.valueOf(value));
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
        if (obj instanceof DifferenceOfRoll) {
            final DifferenceOfRoll other = (DifferenceOfRoll) obj;
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

    public boolean isSucceeded() {
        return value.compareTo(BigInteger.ZERO) >= 0;
    }

    public boolean isFailedAt10Negative() {
        return AT_MOST_10_NEGATIVE.contains(this);
    }

    @Override
    public int compareTo(DifferenceOfRoll o) {
        return value.compareTo(o.value);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(value.toString());
    }
}
