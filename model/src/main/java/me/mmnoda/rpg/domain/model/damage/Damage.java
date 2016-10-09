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
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkState;

/**
 *
 */
public class Damage {

    private final BigInteger value;

    private final DamageType type;

    private final boolean finalValue;

    private Damage(BigInteger value, DamageType type, boolean finalValue) {
        this.value = value;
        this.type = type;
        this.finalValue = finalValue;
    }

    public static Damage of(BigInteger value, DamageType type) {
        return new Damage(value, type, false);
    }

    static Damage ofFinalValue(BigInteger value, DamageType type) {
        return new Damage(value, type, true);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type, finalValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Damage) {
            final Damage other = (Damage) obj;
            return Objects.equals(this.value, other.value)
                    && Objects.equals(this.type, other.type)
                    && Objects.equals(this.finalValue, other.finalValue);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("value", value)
                .add("type", type)
                .toString();
    }

    public Damage finalValue() {
        checkState(!finalValue);
        return type.calculate(this);
    }

    BigInteger toBigInteger() {
        return value;
    }

}
