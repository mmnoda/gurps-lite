package me.mmnoda.rpg.domain.model.rollable.damage_representation;

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

import me.mmnoda.rpg.domain.model.damage.ArmorDivisor;
import me.mmnoda.rpg.domain.model.damage.DamageType;
import me.mmnoda.rpg.domain.model.dice.Dice;
import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import org.junit.Test;

import static me.mmnoda.rpg.domain.model.damage.DamageType.*;
import static me.mmnoda.rpg.domain.model.dice.factory.Dices.D6;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DefaultDamageDiceRepresentationIT {

    private DefaultDamageDiceRepresentation damageDiceRepresentation;

    private DiceRepresentation diceRepresentation;
    private final Dice d6 =  D6.getInstance();
    private String formatted;

    @Test
    public void should_format_cut_type_without_armor_divisor() {
        buildDefaultDiceRepresentation(NumberOfDices.of(9), DiceAdjustment.of(3));
        buildDefaultDamageDiceRepresentation(CUT, ArmorDivisor.NONE);
        format();
        assertFormattedIsEqualsTo("9d+3 cut");
    }

    @Test
    public void should_format_fatigue_type_with_armor_divisor_of_2() {
        buildDefaultDiceRepresentation(NumberOfDices.of(1), DiceAdjustment.of(2));
        buildDefaultDamageDiceRepresentation(FATIGUE, ArmorDivisor._2);
        format();
        assertFormattedIsEqualsTo("1d+2(2) fat");
    }

    @Test
    public void should_format_toxic_type_with_armor_divisor_of_3() {
        buildDefaultDiceRepresentation(NumberOfDices.of(4), DiceAdjustment.ZERO);
        buildDefaultDamageDiceRepresentation(TOXIC, ArmorDivisor._3);
        format();
        assertFormattedIsEqualsTo("4d(3) tox");
    }

    @Test
    public void should_format_corrosive_type_with_armor_divisor_of_5() {
        buildDefaultDiceRepresentation(NumberOfDices.of(6), DiceAdjustment.of(1));
        buildDefaultDamageDiceRepresentation(CORROSIVE, ArmorDivisor._5);
        format();
        assertFormattedIsEqualsTo("6d+1(5) cor");
    }

    @Test
    public void should_format_impaling_type_with_armor_divisor_of_10() {
        buildDefaultDiceRepresentation(NumberOfDices.of(12), DiceAdjustment.of(4));
        buildDefaultDamageDiceRepresentation(IMPALING, ArmorDivisor._10);
        format();
        assertFormattedIsEqualsTo("12d+4(10) imp");
    }

    private void format() {
        formatted = String.format("%s", damageDiceRepresentation);
    }

    private void buildDefaultDamageDiceRepresentation(DamageType type, ArmorDivisor divisor) {
        damageDiceRepresentation = DefaultDamageDiceRepresentation.of(diceRepresentation, type, divisor);
    }

    private void buildDefaultDiceRepresentation(NumberOfDices of, DiceAdjustment of2) {
        diceRepresentation = DefaultDiceRepresentation.of(d6, of, of2);
    }

    private void assertFormattedIsEqualsTo(String expected) {
        assertThat(formatted)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }
}