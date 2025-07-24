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
public class SwingDamageTest {

    private static final String CALCULATE_DATA_PROVIDER = "CalculateDataProvider";

    private SwingDamage swingDamage;

    private final Dice rollable = Dices.D6.getInstance();

    private DiceRepresentation diceRepresentation;

    @DataProvider(name = CALCULATE_DATA_PROVIDER)
    public Object[][] createCalculateDataProvider() {
        return new Object[][]{
                {1, buildDefaultDiceRepresentation(NumberOfDices.ONE, -5)},
                {2, buildDefaultDiceRepresentation(NumberOfDices.ONE, -5)},
                {3, buildDefaultDiceRepresentation(NumberOfDices.ONE, -4)},
                {4, buildDefaultDiceRepresentation(NumberOfDices.ONE, -4)},
                {5, buildDefaultDiceRepresentation(NumberOfDices.ONE, -3)},
                {6, buildDefaultDiceRepresentation(NumberOfDices.ONE, -3)},
                {7, buildDefaultDiceRepresentation(NumberOfDices.ONE, -2)},
                {8, buildDefaultDiceRepresentation(NumberOfDices.ONE, -2)},
                {9, buildDefaultDiceRepresentation(NumberOfDices.ONE, -1)},
                {10, buildDefaultDiceRepresentation(NumberOfDices.ONE, 0)},
                {11, buildDefaultDiceRepresentation(NumberOfDices.ONE, 1)},
                {12, buildDefaultDiceRepresentation(NumberOfDices.ONE, 2)},

                {13, buildDefaultDiceRepresentation(NumberOfDices.of(2), -1)},
                {14, buildDefaultDiceRepresentation(NumberOfDices.of(2), 0)},
                {15, buildDefaultDiceRepresentation(NumberOfDices.of(2), 1)},
                {16, buildDefaultDiceRepresentation(NumberOfDices.of(2), 2)},

                {17, buildDefaultDiceRepresentation(NumberOfDices.of(3), -1)},
                {18, buildDefaultDiceRepresentation(NumberOfDices.of(3), 0)},
                {19, buildDefaultDiceRepresentation(NumberOfDices.of(3), 1)},
                {20, buildDefaultDiceRepresentation(NumberOfDices.of(3), 2)},
        };
    }

    @Test(dataProvider = CALCULATE_DATA_PROVIDER)
    public void should_calculate(final int strengthValue, final DiceRepresentation expected) {
        buildSwingDamage(AttributeLevel.of(strengthValue));
        getDiceRepresentation();
        assertThat(diceRepresentation)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void buildSwingDamage(final AttributeLevel attributeLevel) {
        swingDamage = SwingDamage.of(attributeLevel);
    }

    private void getDiceRepresentation() {
        diceRepresentation = swingDamage.getDiceRepresentation();
    }

    private DefaultDiceRepresentation buildDefaultDiceRepresentation(final NumberOfDices numberOfDices,
                                                                     final int adjustment) {
        return DefaultDiceRepresentation.of(rollable,
                numberOfDices, DiceAdjustment.of(adjustment));
    }

}