package me.mmnoda.gurps.lite.domain.model.character.attribute;

/*
 * #%L
 * gurps-lite-model
 * %%
 * Copyright (C) 2016 Márcio Noda
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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
@EqualsAndHashCode(of = "level", callSuper = false)
@ToString(of = "level")
public final class AttributeLevel extends Number implements Serializable, Comparable<AttributeLevel>, Formattable {

    private static final int DEFAULT_DECIMAL_SCALE = 2;

    private static final BigDecimal FRACTION_LEVEL_RATIO = BigDecimal.valueOf(0.25);

    public static final AttributeLevel ZERO = of(BigDecimal.ZERO);
    public static final AttributeLevel TEN = of(BigDecimal.TEN);

    @Delegate(types = Number.class)
    private final BigDecimal level;

    private final BigDecimal levelRatio;

    private AttributeLevel(final BigDecimal level) {
        if (isInteger(level)) {
            this.level = level;
            this.levelRatio = BigDecimal.ONE;
        } else {
            this.level = level.setScale(DEFAULT_DECIMAL_SCALE, RoundingMode.DOWN);
            this.levelRatio = FRACTION_LEVEL_RATIO;
        }
    }

    private boolean isInteger(final BigDecimal level) {
        return level.scale() <= 0;
    }

    public static AttributeLevel of(BigDecimal value) {
        return new AttributeLevel(value);
    }

    public static AttributeLevel of(int value) {
        return new AttributeLevel(BigDecimal.valueOf(value));
    }

    public static AttributeLevel of(double value) {
        return new AttributeLevel(BigDecimal.valueOf(value));
    }

    // TODO rename method
    public BigInteger getLevelBought() {
        return level.divide(levelRatio, DEFAULT_DECIMAL_SCALE, RoundingMode.DOWN)
                .toBigInteger();
    }

    public AttributeLevel dropFraction() {
        return truncate(RoundingMode.DOWN);
    }

    public AttributeLevel truncate(final RoundingMode roundingMode) {
        return of(level.setScale(0, roundingMode));
    }

    public AttributeLevel add(final AttributeLevel other) {
        return of(level.add(other.level));
    }

    public AttributeLevel subtract(final AttributeLevel other) {
        return of(level.subtract(other.level));
    }

    public AttributeLevel multiply(final AttributeLevel other) {
        return of(level.multiply(other.level));
    }

    public AttributeLevel divide(final AttributeLevel other) {
        return divide(other, RoundingMode.HALF_EVEN);
    }

    public AttributeLevel divide(final AttributeLevel other, final RoundingMode roundingMode) {
        return of(level.divide(other.level, DEFAULT_DECIMAL_SCALE, roundingMode));
    }

    public BigDecimal toBigDecimal() {
        return level;
    }

    public BigInteger toBigInteger() {
        return level.toBigInteger();
    }

    @Override
    public int compareTo(final AttributeLevel o) {
        return level.compareTo(o.level);
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", level);
    }
}
