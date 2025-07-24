package me.mmnoda.gurps.lite.domain.model.character.attribute.strength;

/*
 * #%L
 * gurps-lite-model
 * %%
 * Copyright (C) 2018 - 2025 Márcio Noda
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
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfDices;
import me.mmnoda.gurps.lite.domain.model.dice.factory.Dices;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 *
 */
@EqualsAndHashCode
@ToString
class DamageSequence implements Serializable {

    private static final int DEFAULT_SCALE = 2;
    private static final int SCALE_ZERO = 0;
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final BigDecimal FIVE = BigDecimal.valueOf(5);

    private final BigDecimal sequence;

    private DamageSequence(final AttributeLevel strengthLevel, final BigDecimal divisor,
                           final BigDecimal initialConstant) {
        this.sequence = strengthLevel
                .toBigDecimal()
                .divide(divisor, DEFAULT_SCALE, RoundingMode.HALF_EVEN)
                .subtract(initialConstant.setScale(DEFAULT_SCALE, RoundingMode.DOWN))
                .setScale(SCALE_ZERO, RoundingMode.CEILING);
    }

    static DamageSequence of(final AttributeLevel strengthLevel, final BigDecimal divisor,
                             final BigDecimal initialConstant) {
        return new DamageSequence(strengthLevel, divisor, initialConstant);
    }

    private BigDecimal calculateNumberOfDices() {
        return sequence
                .max(BigDecimal.ZERO)
                .add(TWO)
                .divide(FIVE, DEFAULT_SCALE, RoundingMode.HALF_EVEN)
                .setScale(SCALE_ZERO, RoundingMode.DOWN)
                .add(BigDecimal.ONE);
    }

    private DiceAdjustment calculateDiceAdjustment(final BigDecimal numberOfDicesCalc) {

        final BigInteger diceAdjustmentCalc = sequence
                .subtract(numberOfDicesCalc.subtract(BigDecimal.ONE)
                        .multiply(BigDecimal.valueOf(4)))
                .setScale(SCALE_ZERO, RoundingMode.DOWN)
                .toBigInteger();

        return DiceAdjustment.of(diceAdjustmentCalc);
    }

    DiceRepresentation calculate() {

        final BigDecimal numberOfDices = calculateNumberOfDices();

        final DiceAdjustment diceAdjustment = calculateDiceAdjustment(numberOfDices);

        return DefaultDiceRepresentation.of(Dices.D6.getInstance(), NumberOfDices.of(numberOfDices.toBigInteger()),
                diceAdjustment);
    }
}
