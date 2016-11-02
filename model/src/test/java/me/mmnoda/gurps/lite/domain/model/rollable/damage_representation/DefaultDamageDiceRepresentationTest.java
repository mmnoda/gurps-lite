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

import me.mmnoda.gurps.lite.domain.model.damage.ArmorDivisor;
import me.mmnoda.gurps.lite.domain.model.damage.DamageType;
import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfDices;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfFaces;
import me.mmnoda.gurps.lite.domain.model.dice.result.SingleRollResult;
import me.mmnoda.gurps.lite.domain.model.rollable.damage_representation.result.RollDamageResult;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.result.RollResultSum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultDamageDiceRepresentationTest {

    private final RollResultSum rollResultSum1 = RollResultSum.of(SingleRollResult.of(1), SingleRollResult.of(2), SingleRollResult.of(3));
    private final RollResultSum rollResultSum2 = RollResultSum.of(SingleRollResult.of(5), SingleRollResult.of(5), SingleRollResult.of(6));
    private final RollResultSum rollResultSum3 = RollResultSum.of(SingleRollResult.of(1), SingleRollResult.of(2), SingleRollResult.of(1));

    private final DamageType[] types = DamageType.values();

    private DefaultDamageDiceRepresentation damageDiceRepresentation;

    @Mock
    private DiceRepresentation diceRepresentation;

    private RollDamageResult result;
    private String formatted;

    @Before
    public void setUp() throws Exception {
        mockDiceRepresentation();
    }

    private void mockDiceRepresentation() {
        when(diceRepresentation.getNumberOfDices()).thenReturn(NumberOfDices.THREE);
        when(diceRepresentation.getNumberOfFaces()).thenReturn(NumberOfFaces._6);
        when(diceRepresentation.getDiceAdjustment()).thenReturn(DiceAdjustment.ZERO);
    }

    @After
    public void tearDown() throws Exception {
        reset(diceRepresentation);
    }

    @Test
    public void should_roll_all_damage_types() {
        when(diceRepresentation.roll()).thenReturn(rollResultSum1);
        for (DamageType type : types) {
            buildDamageDiceRepresentation(type);
            roll();
            assertResultIsEqualsTo(RollDamageResult.of(rollResultSum1, type));
        }
        verifyRollOnceForAllTypes();
    }

    @Test
    public void should_roll_min_value_for_all_damage_types() {
        final RollResultSum minRollResultSum = RollResultSum.of(SingleRollResult.of(1), SingleRollResult.of(1), SingleRollResult.of(1));
        for (DamageType type : types) {
            buildDamageDiceRepresentation(type);
            rollMinValue();
            assertResultIsEqualsTo(RollDamageResult.of(minRollResultSum, type));
        }
        verifyNeverCallRoll();
    }

    @Test
    public void should_roll_max_value_for_all_damage_types() {
        final RollResultSum maxRollResultSum = RollResultSum.of(SingleRollResult.of(6), SingleRollResult.of(6), SingleRollResult.of(6));
        for (DamageType type : types) {
            buildDamageDiceRepresentation(type);
            rollMaxValue();
            assertResultIsEqualsTo(RollDamageResult.of(maxRollResultSum, type));
        }
        verifyNeverCallRoll();
    }

    @Test
    public void should_roll_highest_value_of_3_for_all_damage_types() {
        for (DamageType type : types) {
            mockDiceRepresentationRollReturnResult3SumsInSequence();
            buildDamageDiceRepresentation(type);
            rollHighestValueOf3();
            assertResultIsEqualsTo(RollDamageResult.of(rollResultSum2, type));
            verifyRoll3Times();
        }
    }

    @Test
    public void should_roll_lowest_value_of_3_for_all_damage_types() {
        for (DamageType type : types) {
            mockDiceRepresentationRollReturnResult3SumsInSequence();
            buildDamageDiceRepresentation(type);
            rollLowestValueOf3();
            assertResultIsEqualsTo(RollDamageResult.of(rollResultSum3, type));
            verifyRoll3Times();
        }
    }

    @Test
    public void should_roll_avg_value_for_all_damage_types() {
        final RollResultSum avgRollResultSum = RollResultSum.of(SingleRollResult.of(3), SingleRollResult.of(3), SingleRollResult.of(3));
        for (DamageType type : types) {
            buildDamageDiceRepresentation(type);
            rollAvgMinValue();
            assertResultIsEqualsTo(RollDamageResult.of(avgRollResultSum, type));
        }
        verifyNeverCallRoll();
    }

    @Test
    public void should_roll_manual_input_for_all_damage_types() {
        final RollResultSum maxRollResultSum = RollResultSum.of(SingleRollResult.of(6), SingleRollResult.of(6), SingleRollResult.of(6));
        for (DamageType type : types) {
            buildDamageDiceRepresentation(type);
            rollManualInput();
            assertResultIsEqualsTo(RollDamageResult.of(maxRollResultSum, type));
        }
        verifyNeverCallRoll();
    }

    @Test
    public void should_format_crush_type_without_armor_divisor() {
        buildDamageDiceRepresentation(DamageType.CRUSH);
        format();
        assertFormattedIsEqualsTo("diceRepresentation cr");
    }

    @Test
    public void should_format_piercing_type_with_armor_divisor_of_2() {
        buildDamageDiceRepresentation(DamageType.PIERCING, ArmorDivisor._2);
        format();
        assertFormattedIsEqualsTo("diceRepresentation(2) pi");
    }

    private void format() {
        formatted = String.format("%s", damageDiceRepresentation);
    }

    private void assertFormattedIsEqualsTo(String expected) {
        assertThat(formatted)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }

    private void rollManualInput() {
        result = damageDiceRepresentation.rollManualInput(SingleRollResult.of(6));
    }

    private void rollAvgMinValue() {
        result = damageDiceRepresentation.rollAvgMinValue();
    }

    private void verifyRoll3Times() {
        verify(diceRepresentation, times(3)).roll();
    }

    private void rollLowestValueOf3() {
        result = damageDiceRepresentation.rollLowestValueOf3();
    }

    private void mockDiceRepresentationRollReturnResult3SumsInSequence() {
        reset(diceRepresentation);
        when(diceRepresentation.roll()).thenReturn(rollResultSum1, rollResultSum2, rollResultSum3);
    }

    private void rollHighestValueOf3() {
        result = damageDiceRepresentation.rollHighestValueOf3();
    }

    private void verifyNeverCallRoll() {
        verify(diceRepresentation, never()).roll();
    }

    private void rollMinValue() {
        result = damageDiceRepresentation.rollMinValue();
    }

    private void verifyRollOnceForAllTypes() {
        verify(diceRepresentation, times(types.length)).roll();
    }

    private void rollMaxValue() {
        result = damageDiceRepresentation.rollMaxValue();
    }

    private void buildDamageDiceRepresentation(DamageType type) {
        damageDiceRepresentation = DefaultDamageDiceRepresentation.of(diceRepresentation, type);
    }

    private void buildDamageDiceRepresentation(DamageType type, ArmorDivisor armorDivisor) {
        damageDiceRepresentation = DefaultDamageDiceRepresentation.of(diceRepresentation, type, armorDivisor);
    }

    private void assertResultIsEqualsTo(RollDamageResult of) {
        assertThat(result)
                .isNotNull()
                .isEqualTo(of);
    }

    private void roll() {
        result = damageDiceRepresentation.roll();
    }
}