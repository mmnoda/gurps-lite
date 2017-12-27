package me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic;

/*
 * #%L
 * gurps-lite-model
 * %%
 * Copyright (C) 2018 Márcio Noda
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

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.CharacterPoints;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeData;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeObserver;
import me.mmnoda.gurps.lite.domain.model.character.attribute.Dexterity;
import me.mmnoda.gurps.lite.domain.model.character.attribute.Health;

/**
 *
 */
@EqualsAndHashCode(of = "data")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicSpeed implements SecondaryCharacteristic, AttributeObserver, Comparable<BasicSpeed> {

    private static final AttributeLevel DIVISOR = AttributeLevel.of(4);

    private AttributeData data;

    private AttributeLevel dexterityLevel;

    private AttributeLevel healthLevel;

    private static final Map<AttributeAbbreviate, Updater> UPDATER_BY_ATTRIBUTE = ImmutableMap.of(AttributeAbbreviate.DX, Updater.DEXTERITY,
            AttributeAbbreviate.HT, Updater.HEALTH);

    private BasicSpeed(final Dexterity dexterity, final Health health) {
        dexterityLevel = dexterity.getLevel();
        healthLevel = health.getLevel();
    }

    public static BasicSpeed of(final Dexterity dexterity, final Health health) {
        return new BasicSpeed(dexterity, health).init();
    }

    private BasicSpeed init() {
        data = AttributeData.of(CharacterPoints.TWENTY_FIVE, calculate(), AttributeAbbreviate.BS);
        return this;
    }

    private AttributeLevel calculate() {
        return dexterityLevel.add(healthLevel)
                .divide(DIVISOR);
    }

    @Override
    public void update(final AttributeData newData, final AttributeAbbreviate abbreviate) {
        UPDATER_BY_ATTRIBUTE.get(abbreviate)
                .update(this, newData);
        data.updateLevelBase(calculate());
    }

    public boolean addObserver(final BasicMove basicMove) {
        return data.addObserver(basicMove);
    }

    public boolean addObserver(final Dodge dodge) {
        return data.addObserver(dodge);
    }

    public AttributeLevel getLevel() {
        return data.getLevel();
    }

    public AttributeLevel getLevelTruncated() {
        return data.getLevelTruncated();
    }

    @Override
    public int compareTo(BasicSpeed o) {
        return data.compareTo(o.data);
    }

    private enum Updater {
        DEXTERITY {
            @Override
            void update(BasicSpeed basicSpeed, AttributeData data) {
                basicSpeed.dexterityLevel = data.getLevel();
            }
        },

        HEALTH {
            @Override
            void update(BasicSpeed basicSpeed, AttributeData data) {
                basicSpeed.healthLevel = data.getLevel();
            }
        };

        abstract void update(final BasicSpeed basicSpeed, final AttributeData data);
    }
}
