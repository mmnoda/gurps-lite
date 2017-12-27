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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Formattable;
import java.util.Formatter;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 */
@EqualsAndHashCode(of = "level")
@ToString(of = "level")
public final class AttributeLevel implements Serializable, Comparable<AttributeLevel>, Formattable {

    private static final int DEFAULT_DECIMAL_SCALE = 2;


    public static final AttributeLevel ZERO = of(BigDecimal.ZERO);
    public static final AttributeLevel TEN = of(BigDecimal.TEN);

    private final BigDecimal level;

    private AttributeLevel(BigDecimal level) {
        this.level = level;
    }

    public static AttributeLevel of(BigDecimal value) {
        return new AttributeLevel(value);
    }

    public static AttributeLevel of(int value) {
        return new AttributeLevel(BigDecimal.valueOf(value));
    }

    public static AttributeLevel of(double value) {
        return new AttributeLevel(BigDecimal.valueOf(value)
                .setScale(DEFAULT_DECIMAL_SCALE, RoundingMode.DOWN));
    }

    public AttributeLevel trunc() {
        return of(level.setScale(0, RoundingMode.DOWN));
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
        return of(level.divide(other.level, DEFAULT_DECIMAL_SCALE, RoundingMode.HALF_EVEN));
    }

    public BigDecimal toBigDecimal() {
        return level;
    }

    public BigInteger toBigInteger() {
        return level.toBigInteger();
    }

    @Override
    public int compareTo(AttributeLevel o) {
        return level.compareTo(o.level);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", level);
    }
}
