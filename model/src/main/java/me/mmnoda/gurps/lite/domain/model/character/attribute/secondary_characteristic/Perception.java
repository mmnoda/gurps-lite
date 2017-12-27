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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.CharacterPoints;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeData;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeObserver;
import me.mmnoda.gurps.lite.domain.model.character.attribute.Intelligence;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 */
@EqualsAndHashCode(of = "data")
@ToString(of = "data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perception implements AttributeObserver {

    private AttributeData data;

    private Perception(final Intelligence intelligence) {
        data = AttributeData.of(CharacterPoints.FIVE, intelligence.getLevel());
    }

    public static Perception of(final Intelligence intelligence) {
        return new Perception(intelligence);
    }

    @Override
    public void update(final AttributeData newData, final AttributeAbbreviate abbreviate) {
        checkArgument(abbreviate == AttributeAbbreviate.IQ);
        data.updateLevelBase(newData.getLevel());
    }
}
