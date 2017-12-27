package me.mmnoda.gurps.lite.domain.model.character.attribute;

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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.BasicMove;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.BasicSpeed;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.Dodge;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.FatiguePoints;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.HitPoint;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.Perception;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.Will;
import me.mmnoda.gurps.lite.domain.model.character.attribute.strength.BasicLift;
import me.mmnoda.gurps.lite.domain.model.character.attribute.strength.DamageAttribute;
import me.mmnoda.gurps.lite.domain.model.character.attribute.strength.Strength;

/**
 *
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterAttributes {

    private Strength strength;

    private Dexterity dexterity;

    private Intelligence intelligence;

    private Health health;

    private DamageAttribute damageAttribute;

    private BasicSpeed basicSpeed;

    private BasicMove basicMove;

    private Dodge dodge;

    private Perception perception;

    private BasicLift basicLift;

    private FatiguePoints fatiguePoints;

    private HitPoint hitPoint;

    private Will will;

    public static CharacterAttributes of() {
        return new CharacterAttributes().build();
    }

    private CharacterAttributes build() {
        buildPrimaryAttributes();

        hitPoint = HitPoint.of(strength);
        basicLift = BasicLift.of(strength);

        perception = Perception.of(intelligence);
        will = Will.of(intelligence);

        basicSpeed = BasicSpeed.of(dexterity, health);
        basicMove = BasicMove.of(basicSpeed);
        dodge = Dodge.of(basicSpeed);

        fatiguePoints = FatiguePoints.of(health);

        initialize();

        return this;
    }

    private void buildPrimaryAttributes() {
        strength = Strength.of();
        dexterity = Dexterity.of();
        intelligence = Intelligence.of();
        health = Health.of();
    }

    public void initialize() {
        strength.addObserver(hitPoint);

        dexterity.addObserver(basicSpeed);

        health.addObserver(basicSpeed);
        health.addObserver(fatiguePoints);

        intelligence.addObserver(perception);
        intelligence.addObserver(will);

        basicSpeed.addObserver(basicMove);
        basicSpeed.addObserver(dodge);

    }

}
