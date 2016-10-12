package me.mmnoda.rpg.domain.model.damage;

/*
 * #%L
 * model
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

import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkState;

/**
 *
 */
public class Damage implements Formattable, Comparable<Damage> {

    private final BigInteger value;

    private final DamageType type;

    private final boolean finalValue;

    private Damage(BigInteger value, DamageType type) {
        this.value = value;
        this.type = type;
        this.finalValue = false;
    }

    private Damage(final Damage origin) {
        this.type = origin.type;
        this.value = type.finalValue(origin);
        this.finalValue = true;
    }

    public static Damage of(BigInteger value, DamageType type) {
        return new Damage(value, type);
    }

    static Damage of(long value, DamageType type) {
        return new Damage(BigInteger.valueOf(value), type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Damage) {
            final Damage other = (Damage) obj;
            return Objects.equals(this.value, other.value)
                    && Objects.equals(this.type, other.type);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("value", value)
                .add("type", type)
                .add("finalValue", finalValue)
                .toString();
    }

    public Damage finalValue() {
        checkState(!finalValue);
        return new Damage(this);
    }

    BigInteger toBigInteger() {
        return value;
    }

    @Override
    public int compareTo(Damage o) {
        return value.compareTo(o.value);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s [%s]", value, type);
    }
}
