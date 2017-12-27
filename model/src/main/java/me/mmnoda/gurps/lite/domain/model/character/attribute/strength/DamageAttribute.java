package me.mmnoda.gurps.lite.domain.model.character.attribute.strength;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeData;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeObserver;
import me.mmnoda.gurps.lite.domain.model.rollable.damage_representation.DamageDiceRepresentation;

import static com.google.common.base.Preconditions.checkArgument;
import static me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate.ST;

/**
 *
 */
@EqualsAndHashCode(of = {"swingDamage", "thrustDamage"})
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DamageAttribute implements AttributeObserver {

    private DamageDiceRepresentation swingDamage;

    private DamageDiceRepresentation thrustDamage;

    private DamageAttribute(final Strength strength) {
    }

    public static DamageAttribute of(final Strength strength) {
        return new DamageAttribute(strength);
    }

    @Override
    public void update(final AttributeData newData, final AttributeAbbreviate abbreviate) {
        checkArgument(abbreviate == ST);

        // min((str - 14)/2, 1)
    }
}
