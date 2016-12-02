package me.mmnoda.gurps.lite.domain.model.dice.result;

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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.OverallRollSumValue;

import java.math.BigInteger;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public final class DiceSum implements Comparable<DiceSum>, Formattable, Iterable<SingleRollResult> {

    public static final DiceSum ZERO = new DiceSum(BigInteger.ZERO);

    private static final Range<DiceSum> NATURAL_CRITICAL_SUCCESS = Range.closed(of(3), of(4));
    private static final DiceSum NATURAL_CRITICAL_MISS = of(18);

    private final List<SingleRollResult> results;

    private final BigInteger value;

    private DiceSum(final BigInteger value) {
        this.value = value;
        this.results = ImmutableList.of();
    }

    private DiceSum(final SingleRollResult singleRollResult, final DiceSum diceSum) {
        this.value = diceSum.value.add(singleRollResult.toBigInteger());
        this.results = ImmutableList.<SingleRollResult>builder()
                .addAll(diceSum.results)
                .add(singleRollResult)
                .build();
    }

    public static DiceSum of(final BigInteger value) {
        checkNotNull(value);
        return new DiceSum(value);
    }

    public static DiceSum of(long value) {
        return of(BigInteger.valueOf(value));
    }

    public static DiceSum of(final SingleRollResult singleRollResult, final DiceSum diceSum) {
        return new DiceSum(singleRollResult, diceSum);
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
        if (obj instanceof DiceSum) {
            final DiceSum other = (DiceSum) obj;
            return Objects.equals(this.value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("value", value)
                .add("results", results)
                .toString();
    }

    public BigInteger toBigInteger() {
        return value;
    }

    public DiceSum add(final SingleRollResult rollResult) {
        return of(rollResult, this);
    }

    public DiceSum half() {
        return of(value.divide(BigInteger.valueOf(2)));
    }

    public OverallRollSumValue calculateOverall(DiceAdjustment adjustment) {
        checkNotNull(adjustment);
        return OverallRollSumValue.of(value.add(adjustment.toBigInteger()));
    }

    public boolean isNaturalCriticalSuccess() {
        return NATURAL_CRITICAL_SUCCESS.contains(this);
    }

    public boolean isNaturalCriticalMiss() {
        return NATURAL_CRITICAL_MISS.equals(this);
    }

    public boolean isNotZero() {
        return value.signum() != 0;
    }

    @Override
    public Iterator<SingleRollResult> iterator() {
        return results.iterator();
    }

    @Override
    public int compareTo(DiceSum o) {
        return value.compareTo(o.value);
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        formatter.format(results.isEmpty() ? value.toString() : processFormatTo());
    }

    private String processFormatTo() {
        final StringBuilder result = new StringBuilder().append('(');
        final Iterator<SingleRollResult> singleRollResultsIterator = iterator();

        appendSingleResultsToFormat(result, singleRollResultsIterator);

        return result.append(')').toString();
    }

    private void appendSingleResultsToFormat(StringBuilder result, Iterator<SingleRollResult> singleRollResultsIterator) {
        while (singleRollResultsIterator.hasNext()) {
            result.append(String.format("%s", singleRollResultsIterator.next()));

            if (singleRollResultsIterator.hasNext()) {
                result.append(" + ");
            }
        }
    }
}
