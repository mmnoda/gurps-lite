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

import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.dice.Dice;
import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfDices;
import me.mmnoda.gurps.lite.domain.model.dice.factory.Dices;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class ThrustDamageTest {

    private static final String CALCULATE_DATA_PROVIDER = "CalculateDataProvider";

    private ThrustDamage thrustDamage;

    private DiceRepresentation diceRepresentation;

    private final Dice rollable = Dices.D6.getInstance();

    @DataProvider(name = CALCULATE_DATA_PROVIDER)
    public Object[][] createCalculateDataProvider() {
        return new Object[][]{
                {1, buildDefaultDiceRepresentation(NumberOfDices.ONE, -6)},
                {2, buildDefaultDiceRepresentation(NumberOfDices.ONE, -6)},
                {3, buildDefaultDiceRepresentation(NumberOfDices.ONE, -5)},
                {4, buildDefaultDiceRepresentation(NumberOfDices.ONE, -5)},
                {5, buildDefaultDiceRepresentation(NumberOfDices.ONE, -4)},
                {6, buildDefaultDiceRepresentation(NumberOfDices.ONE, -4)},
                {7, buildDefaultDiceRepresentation(NumberOfDices.ONE, -3)},
                {8, buildDefaultDiceRepresentation(NumberOfDices.ONE, -3)},
                {9, buildDefaultDiceRepresentation(NumberOfDices.ONE, -2)},
                {10, buildDefaultDiceRepresentation(NumberOfDices.ONE, -2)},
                {11, buildDefaultDiceRepresentation(NumberOfDices.ONE, -1)},
                {12, buildDefaultDiceRepresentation(NumberOfDices.ONE, -1)},
                {13, buildDefaultDiceRepresentation(NumberOfDices.ONE, 0)},
                {14, buildDefaultDiceRepresentation(NumberOfDices.ONE, 0)},

                {15, buildDefaultDiceRepresentation(NumberOfDices.ONE, 1)},
                {16, buildDefaultDiceRepresentation(NumberOfDices.ONE, 1)},
                {17, buildDefaultDiceRepresentation(NumberOfDices.ONE, 2)},
                {18, buildDefaultDiceRepresentation(NumberOfDices.ONE, 2)},

                {19, buildDefaultDiceRepresentation(NumberOfDices.of(2), -1)},
                {20, buildDefaultDiceRepresentation(NumberOfDices.of(2), -1)},
        };
    }

    @Test(dataProvider = CALCULATE_DATA_PROVIDER)
    public void should_calculate(final int strengthValue, final DiceRepresentation expected) {
        buildThrustDamage(AttributeLevel.of(strengthValue));
        getDiceRepresentation();
        assertThat(diceRepresentation)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void buildThrustDamage(final AttributeLevel of) {
        thrustDamage = ThrustDamage.of(of);
    }

    private DefaultDiceRepresentation buildDefaultDiceRepresentation(final NumberOfDices numberOfDices,
                                                                     final int adjustment) {
        return DefaultDiceRepresentation.of(rollable,
                numberOfDices, DiceAdjustment.of(adjustment));
    }

    private void getDiceRepresentation() {
        diceRepresentation = thrustDamage.getDiceRepresentation();
    }
}