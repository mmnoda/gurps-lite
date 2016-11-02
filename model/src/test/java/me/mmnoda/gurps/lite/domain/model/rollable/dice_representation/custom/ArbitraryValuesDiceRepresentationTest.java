package me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.custom;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2015 - 2016 Márcio Noda
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

import me.mmnoda.gurps.lite.domain.model.dice.DiceAdjustment;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfDices;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfFaces;
import me.mmnoda.gurps.lite.domain.model.dice.result.SingleRollResult;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.SingleRollable;
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
public class ArbitraryValuesDiceRepresentationTest {

    private ArbitraryValuesDiceRepresentation arbitraryValuesDiceRepresentation;

    private RollResultSum rollResultSum;

    private DiceAdjustment adjustment;

    private SingleRollResult first;
    private SingleRollResult second;
    private SingleRollResult third;

    @Mock
    private SingleRollable singleRollable;

    @Before
    public void setUp() {
        adjustment = DiceAdjustment.ZERO;
        first = SingleRollResult.ONE;
    }

    @After
    public void tearDown() {
        reset(singleRollable);
    }

    @Test
    public void should_roll_setting_1_arbitrary_result_without_adjustment() {
        mockSingleRollableReturn6Faces();
        buildFirstArbitrarySingleRollResult(5);
        buildMaxValueDiceRepresentation();
        roll();

        assertRollResultSumIsEqualTo(RollResultSum.builder()
                .withAdjustment(adjustment)
                .add(first)
                .add(first)
                .add(first)
                .build());

        verifySingleRollableReturn6Faces();
    }

    @Test
    public void should_roll_setting_3_arbitrary_results_without_adjustment() {
        mockSingleRollableReturn6Faces();
        buildArbitraryResults(2, 3, 4);
        buildMaxValueDiceRepresentation(second, third);
        roll();
        assertRollResultSumIsEqualTo(RollResultSum.builder()
                .withAdjustment(adjustment)
                .add(first)
                .add(second)
                .add(third)
                .build());

        verifySingleRollableReturn6Faces();
    }

    @Test
    public void should_roll_setting_2_arbitrary_results_without_adjustment() {
        mockSingleRollableReturn6Faces();
        buildArbitraryResults(6, 5);
        buildMaxValueDiceRepresentation(second);
        roll();

        assertRollResultSumIsEqualTo(RollResultSum.builder()
                .withAdjustment(adjustment)
                .add(first)
                .add(second)
                .add(first)
                .build());

        verifySingleRollableReturn6Faces();
    }

    @Test
    public void should_roll_setting_1_arbitrary_result_with_adjustment() {
        mockSingleRollableReturn6Faces();
        buildFirstArbitrarySingleRollResult(2);
        buildDiceAdjustment(2);
        buildMaxValueDiceRepresentation();
        roll();
        assertRollResultSumIsEqualTo(RollResultSum.builder()
                .withAdjustment(adjustment)
                .add(first)
                .add(first)
                .add(first)
                .build());
        verifySingleRollableReturn6Faces();
    }

    @Test
    public void should_roll_setting_2_arbitrary_result_with_adjustment() {
        mockSingleRollableReturn6Faces();
        buildDiceAdjustment(3);
        buildArbitraryResults(3, 6);
        buildMaxValueDiceRepresentation(second);
        roll();
        assertRollResultSumIsEqualTo(RollResultSum.builder()
                .withAdjustment(adjustment)
                .add(first)
                .add(second)
                .add(first)
                .build());
        verifySingleRollableReturn6Faces();
    }

    @Test
    public void should_roll_setting_3_arbitrary_result_with_adjustment() {
        mockSingleRollableReturn6Faces();
        buildDiceAdjustment(4);
        buildArbitraryResults(1, 2, 3);
        buildMaxValueDiceRepresentation(second, third);
        roll();
        assertRollResultSumIsEqualTo(RollResultSum.builder()
                .withAdjustment(adjustment)
                .add(first)
                .add(second)
                .add(third)
                .build());
        verifySingleRollableReturn6Faces();
    }

    private void verifySingleRollableReturn6Faces() {
        verify(singleRollable).getNumberOfFaces();
    }

    private void buildArbitraryResults(int value1, int value2) {
        buildFirstArbitrarySingleRollResult(value1);
        buildSecondArbitrarySingleRollResult(value2);
    }

    private void buildSecondArbitrarySingleRollResult(int value) {
        second = SingleRollResult.of(value);
    }

    private void buildArbitraryResults(int value1, int value2, int value3) {
        buildArbitraryResults(value1, value2);
        third = SingleRollResult.of(value3);
    }

    private void buildDiceAdjustment(int value) {
        adjustment = DiceAdjustment.of(value);
    }

    private void buildFirstArbitrarySingleRollResult(int value) {
        first = SingleRollResult.of(value);
    }

    private void buildMaxValueDiceRepresentation(SingleRollResult ...  singleRollResults) {
        arbitraryValuesDiceRepresentation = ArbitraryValuesDiceRepresentation
                .of(DefaultDiceRepresentation.of(singleRollable, NumberOfDices.THREE, adjustment), first, singleRollResults);
    }

    private void mockSingleRollableReturn6Faces() {
        when(singleRollable.getNumberOfFaces()).thenReturn(NumberOfFaces._6);
    }

    private void assertRollResultSumIsEqualTo(RollResultSum sum) {
        assertThat(rollResultSum)
                .isNotNull()
                .isEqualTo(sum);
    }

    private void roll() {
        rollResultSum = arbitraryValuesDiceRepresentation.roll();
    }
}