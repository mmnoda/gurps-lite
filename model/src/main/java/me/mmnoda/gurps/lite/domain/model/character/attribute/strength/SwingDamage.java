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

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;

import java.math.BigDecimal;

/**
 *
 */
@EqualsAndHashCode(of = "diceRepresentation")
@ToString(of = "diceRepresentation")
public class SwingDamage {

    private static final BigDecimal SIX = BigDecimal.valueOf(6);

    private static final RangeMap<AttributeLevel, DamageSequenceBuilder> DIVISOR_BY_ATTRIBUTE_LEVEL;

    static {
        final ImmutableRangeMap.Builder<AttributeLevel, DamageSequenceBuilder> builder = ImmutableRangeMap
                .builder();
        builder.put(Range.lessThan(AttributeLevel.of(10)), DamageSequenceBuilder.BELOW_OF_ELEVEN);
        builder.put(Range.closed(AttributeLevel.of(10), AttributeLevel.of(20)),
                DamageSequenceBuilder.ELEVEN_TO_TWENTY);
        DIVISOR_BY_ATTRIBUTE_LEVEL = builder.build();
    }

    @Getter
    private final DiceRepresentation diceRepresentation;

    private SwingDamage(final AttributeLevel strengthLevel) {
        this.diceRepresentation = calculate(strengthLevel);
    }

    public static SwingDamage of(final AttributeLevel strengthLevel) {
        return new SwingDamage(strengthLevel);
    }

    private DiceRepresentation calculate(final AttributeLevel strengthLevel) {
        return DIVISOR_BY_ATTRIBUTE_LEVEL.get(strengthLevel)
                .build(strengthLevel)
                .calculate();
    }

    private enum DamageSequenceBuilder {
        BELOW_OF_ELEVEN{
            @Override
            DamageSequence build(final AttributeLevel strengthLevel) {
                return DamageSequence.of(strengthLevel, BigDecimal.valueOf(2), SIX);
            }
        },

        ELEVEN_TO_TWENTY {
            @Override
            DamageSequence build(final AttributeLevel strengthLevel) {
                return DamageSequence.of(strengthLevel, BigDecimal.ONE, BigDecimal.valueOf(10));
            }
        };

        abstract DamageSequence build(final AttributeLevel strengthLevel);
    }
}
