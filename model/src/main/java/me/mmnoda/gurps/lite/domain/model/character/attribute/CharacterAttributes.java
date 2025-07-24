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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic.*;
import me.mmnoda.gurps.lite.domain.model.character.attribute.strength.BasicLift;
import me.mmnoda.gurps.lite.domain.model.character.attribute.strength.DamageAttribute;
import me.mmnoda.gurps.lite.domain.model.character.attribute.strength.Strength;

/**
 *
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterAttributes {

    private Strength strength;

    private Dexterity dexterity;

    private Intelligence intelligence;

    private Health health;

    private BasicSpeed basicSpeed;

    private BasicMove basicMove;

    private Dodge dodge;

    private Perception perception;

    private BasicLift basicLift;

    private DamageAttribute damageAttribute;

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
        damageAttribute = DamageAttribute.of(strength);

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
        strength.addObserver(basicLift);
        strength.addObserver(damageAttribute);

        dexterity.addObserver(basicSpeed);

        health.addObserver(basicSpeed);
        health.addObserver(fatiguePoints);

        intelligence.addObserver(perception);
        intelligence.addObserver(will);

        basicSpeed.addObserver(basicMove);
        basicSpeed.addObserver(dodge);
    }
}
