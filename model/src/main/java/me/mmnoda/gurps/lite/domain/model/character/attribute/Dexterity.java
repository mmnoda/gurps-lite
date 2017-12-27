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

import java.util.Formattable;
import java.util.Formatter;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.CharacterPoints;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.BasicSpeed;

/**
 *
 */
@EqualsAndHashCode(of = "data")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dexterity implements PrimaryAttribute, Comparable<Dexterity>, Formattable {

    private AttributeData data;

    public static Dexterity of() {
        return new Dexterity().init();
    }

    private Dexterity init() {
        data = AttributeData.of(CharacterPoints.TWENTY, AttributeLevel.TEN, AttributeAbbreviate.DX);
        return this;
    }

    public boolean addObserver(final BasicSpeed basicSpeed) {
        return data.addObserver(basicSpeed);
    }

    public AttributeLevel getLevel() {
        return data.getLevel();
    }

    @Override
    public int compareTo(Dexterity o) {
        return data.compareTo(o.data);
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", data);
    }
}
