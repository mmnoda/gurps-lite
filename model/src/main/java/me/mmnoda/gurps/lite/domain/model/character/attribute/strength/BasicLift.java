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

import lombok.*;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeData;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeObserver;

import static com.google.common.base.Preconditions.checkArgument;
import static me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeAbbreviate.ST;

/**
 *
 */
@EqualsAndHashCode(of = "level")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicLift implements AttributeObserver {

    private static final AttributeLevel DIVISOR = AttributeLevel.of(5);

    @Getter
    private AttributeLevel level;

    private BasicLift(final Strength strength) {
        level = calculate(strength.getLevel());
    }

    public static BasicLift of(final Strength strength) {
        return new BasicLift(strength);
    }

    private AttributeLevel calculate(final AttributeLevel strengthLevel) {
        return strengthLevel.multiply(strengthLevel)
                .divide(DIVISOR)
                // TODO check round mode
                .dropFraction();
    }

    @Override
    public void update(AttributeData newData, AttributeAbbreviate abbreviate) {
        checkArgument(abbreviate == ST);
        level = calculate(newData.getLevel());
    }
}
