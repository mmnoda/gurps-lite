package me.mmnoda.gurps.lite.domain.model.rollable.damage_representation;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2016 Márcio Noda
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

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.damage.ArmorDivisor;
import me.mmnoda.gurps.lite.domain.model.damage.DamageType;
import me.mmnoda.gurps.lite.domain.model.dice.result.SingleRollResult;
import me.mmnoda.gurps.lite.domain.model.rollable.damage_representation.result.RollDamageResult;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.custom.CustomDiceRepresentationFactoryMethod;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
@EqualsAndHashCode(of = {"diceRepresentation", "damageType", "armorDivisor"})
@ToString
public class DefaultDamageDiceRepresentation implements Serializable, DamageDiceRepresentation, Formattable {

    private final DiceRepresentation diceRepresentation;

    private final DamageType damageType;

    private final ArmorDivisor armorDivisor;

    private DefaultDamageDiceRepresentation(DiceRepresentation diceRepresentation, DamageType damageType) {
        this(diceRepresentation, damageType, ArmorDivisor.NONE);
    }

    private DefaultDamageDiceRepresentation(DiceRepresentation diceRepresentation, DamageType damageType, ArmorDivisor armorDivisor) {
        this.diceRepresentation = diceRepresentation;
        this.damageType = damageType;
        this.armorDivisor = armorDivisor;
    }

    public static DefaultDamageDiceRepresentation of(DiceRepresentation diceRepresentation, DamageType damageType) {
        return new DefaultDamageDiceRepresentation(diceRepresentation, damageType);
    }

    public static DefaultDamageDiceRepresentation of(DiceRepresentation diceRepresentation, DamageType damageType, ArmorDivisor armorDivisor) {
        return new DefaultDamageDiceRepresentation(diceRepresentation, damageType, armorDivisor);
    }

    @Override
    public RollDamageResult roll() {
        return damageResult(diceRepresentation.roll());
    }

    @Override
    public RollDamageResult rollMaxValue() {
        return damageResult(CustomDiceRepresentationFactoryMethod.INSTANCE.buildMaxValue(diceRepresentation).roll());
    }

    @Override
    public RollDamageResult rollMinValue() {
        return damageResult(CustomDiceRepresentationFactoryMethod.INSTANCE.buildMinValue(diceRepresentation).roll());
    }

    @Override
    public RollDamageResult rollAvgValue() {
        return damageResult(CustomDiceRepresentationFactoryMethod.INSTANCE.buildAvgValue(diceRepresentation).roll());
    }

    @Override
    public RollDamageResult rollHighestValueOf3() {
        return damageResult(CustomDiceRepresentationFactoryMethod.INSTANCE.buildHighestValueOf3(diceRepresentation).roll());
    }

    @Override
    public RollDamageResult rollLowestValueOf3() {
        return damageResult(CustomDiceRepresentationFactoryMethod.INSTANCE.buildLowestValueOf3(diceRepresentation).roll());
    }

    @Override
    public RollDamageResult rollManualInput(SingleRollResult first, SingleRollResult... expectedResults) {
        return damageResult(CustomDiceRepresentationFactoryMethod.INSTANCE.buildManualInput(diceRepresentation, first, expectedResults).roll());
    }

    private RollDamageResult damageResult(RollResultSum roll) {
        return RollDamageResult.of(roll, damageType, armorDivisor);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s%s %s", diceRepresentation, armorDivisor, damageType);
    }
}
