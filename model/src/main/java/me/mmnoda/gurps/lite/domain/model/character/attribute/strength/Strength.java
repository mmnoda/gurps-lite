package me.mmnoda.gurps.lite.domain.model.character.attribute.strength;

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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.CharacterPoints;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeData;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.character.attribute.PrimaryAttribute;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.HitPoint;

import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
@EqualsAndHashCode(of = "data")
@ToString(of = "data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Strength implements PrimaryAttribute, Comparable<Strength>, Formattable {

    private AttributeData data;

    public static Strength of() {
        return new Strength().init();
    }

    private Strength init() {
        data = AttributeData.of(CharacterPoints.TEN, AttributeLevel.TEN, AttributeAbbreviate.ST);
        return this;
    }

    public boolean addObserver(final HitPoint hitPoint) {
        return data.addObserver(hitPoint);
    }

    public boolean addObserver(final BasicLift basicLift) {
        return data.addObserver(basicLift);
    }

    public boolean addObserver(final DamageAttribute damageAttribute) {
        return data.addObserver(damageAttribute);
    }

    public AttributeLevel getLevel() {
        return data.getLevel();
    }

    @Override
    public int compareTo(Strength o) {
        return data.compareTo(o.data);
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", data);
    }
}
