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
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeObserver;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 */
@EqualsAndHashCode(of = "data")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dodge implements AttributeObserver, SecondaryCharacteristic {

    private static final AttributeLevel ADJUSTMENT = AttributeLevel.of(3);

    private AttributeData data;

    private Dodge(final BasicSpeed basicSpeed) {
        data = AttributeData.of(CharacterPoints.TWENTY_FIVE, calculate(basicSpeed));
    }

    private AttributeLevel calculate(final BasicSpeed basicSpeed) {
        return basicSpeed.getLevelTruncated()
                .add(ADJUSTMENT);
    }

    public static Dodge of(final BasicSpeed basicSpeed) {
        return new Dodge(basicSpeed);
    }

    @Override
    public void update(final AttributeData newData, final AttributeAbbreviate abbreviate) {
        checkArgument(abbreviate == AttributeAbbreviate.BS);
        data.updateLevelBase(newData.getLevelTruncated());
    }

    public AttributeLevel getLevel() {
        return data.getLevel();
    }
}
