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

import com.google.common.collect.ImmutableSet;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Set;

import static me.mmnoda.rpg.domain.model.damage.DamageMultiplier.*;

/**
 */
public enum DamageType implements Formattable {

    CRUSH("cr"),

    CUT(ONE_DOT_FIVE, "cut"),

    SMALL_PIERCING(ZERO_DOT_FIVE, "pi-"),

    PIERCING("pi"),

    LARGE_PIERCING(ONE_DOT_FIVE, "pi+"),

    HUGE_PIERCING(DOUBLE, "pi++"),

    IMPALING(DOUBLE, "imp"),

    BURNING("burn"),

    TOXIC("tox"),

    CORROSIVE("cor"),

    FATIGUE("fat");

    private final DamageMultiplier multiplier;

    private final String abbreviation;

    private static final Set<DamageType> ALL_PIERCINGS;

    static {
        ALL_PIERCINGS = ImmutableSet.of(SMALL_PIERCING, PIERCING, LARGE_PIERCING, HUGE_PIERCING);
    }

    DamageType(DamageMultiplier multiplier, String abbreviation) {
        this.multiplier = multiplier;
        this.abbreviation = abbreviation;
    }

    DamageType(String abbreviation) {
        this(NONE, abbreviation);
    }

    Damage calculate(final Damage damage) {
        return multiplier.multiply(damage.toBigInteger(), this);
    }

    @Override
    public final void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", abbreviation);
    }
}
