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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;

import java.math.BigDecimal;
import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
@EqualsAndHashCode(of = "diceRepresentation")
@ToString(of = "diceRepresentation")
public class ThrustDamage implements Comparable<ThrustDamage>, Formattable {

    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final BigDecimal SEVEN = BigDecimal.valueOf(7);

    @Getter
    private final DiceRepresentation diceRepresentation;

    private ThrustDamage(final AttributeLevel strengthLevel) {
        diceRepresentation = calculate(strengthLevel);
    }

    static ThrustDamage of(final AttributeLevel strengthLevel) {
        return new ThrustDamage(strengthLevel);
    }

    private DiceRepresentation calculate(final AttributeLevel strengthLevel) {
        return DamageSequence.of(strengthLevel, TWO, SEVEN)
                .calculate();
    }

    public ThrustDamage update(final AttributeLevel strengthLevel){
        return of(strengthLevel);
    }

    @Override
    public void formatTo(final Formatter formatter, final int flags, final int width, final int precision) {
        formatter.format("%s", diceRepresentation);
    }

    @Override
    public int compareTo(final ThrustDamage o) {
        return diceRepresentation.compareTo(o.diceRepresentation);
    }
}
