package me.mmnoda.rpg.domain.model.rollable.dice_representation.result;

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
import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll.newDifferenceOfRoll;

/**
 *
 */
public final class OverallRollSumValue implements Formattable, Comparable<OverallRollSumValue> {

    private static final Range<OverallRollSumValue> PROBABLE_CRITICAL_SUCCESS = Range.closed(of(5), of(6));
    private static final OverallRollSumValue PROBABLE_CRITICAL_MISS = of(17);
    private static final OverallRollSumValue FIVE = of(5);

    private final BigInteger value;

    private OverallRollSumValue(BigInteger value) {
        this.value = value;
    }

    public static OverallRollSumValue of(BigInteger value) {
        return new OverallRollSumValue(value);
    }

    static OverallRollSumValue of(int value) {
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
        if (obj instanceof OverallRollSumValue) {
            final OverallRollSumValue other = (OverallRollSumValue) obj;
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

    public OverallRollSumValue doubleValue() {
        return of(value.multiply(BigInteger.valueOf(2)));
    }

    public OverallRollSumValue tripleValue() {
        return of(value.multiply(BigInteger.valueOf(3)));
    }

    public boolean isCriticalMiss(EffectiveValue effectiveValue) {
        return effectiveValue.isLessThan16() && PROBABLE_CRITICAL_MISS.equals(this);
    }

    public boolean isCriticalSuccess(EffectiveValue effectiveValue) {
        return PROBABLE_CRITICAL_SUCCESS.contains(this) && (effectiveValue.isGreaterEquals16() ||
                (effectiveValue.isGreaterEquals15() && FIVE.equals(this)));
    }

    public DifferenceOfRoll calculateDifference(EffectiveValue effectiveValue) {
        return newDifferenceOfRoll(effectiveValue.toBigInteger().subtract(value));
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", value);
    }

    @Override
    public int compareTo(OverallRollSumValue o) {
        return value.compareTo(o.value);
    }

}


