package me.mmnoda.rpg.domain.model.action;

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

import me.mmnoda.rpg.domain.model.action.critical.CriticalDeterminationFactory;
import me.mmnoda.rpg.domain.model.action.factory.ActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.result.ActionRollResult;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultActionRollerTest {

    private DefaultActionRoller defaultActionRoller;

    private EffectiveValue effectiveValue;
    private ActionRollResult actionRollResult;

    @Mock
    private ActionRollerBuilder actionRollerBuilder;

    @Mock
    private DiceRepresentation diceRepresentation;
    private String format;
    private RollResultSum rollResultSum;

    @Before
    public void setUp() {
        effectiveValue = EffectiveValue.TEN;
        mockActionRollerBuilder();
    }

    private void mockActionRollerBuilder() {
        when(actionRollerBuilder.getRollables())
                .thenReturn(diceRepresentation);
        when(actionRollerBuilder.getCriticalDetermination())
                .thenReturn(CriticalDeterminationFactory.INSTANCE.buildDefault());
    }

    @After
    public void tearDown() throws Exception {
        reset(actionRollerBuilder, diceRepresentation);
    }

    @Test
    public void should_roll_from_default_settings() {
        buildDefaultActionRoller();
        roll();
        assertActionRollResultIsNotNull();
    }

    @Test
    public void should_roll_succeeded_by_1() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(2, 3, 4);

        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (2 + 3 + 4) = 9 Succeeded by: 1");
    }

    @Test
    public void should_roll_succeeded_by_zero() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(3, 3, 4);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (3 + 3 + 4) = 10 Succeeded by: 0");
    }

    @Test
    public void should_roll_succeeded_by_natural_critical_of_3() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(1, 1, 1);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (1 + 1 + 1) = 3 [CRITICAL SUCCESS]");
    }

    @Test
    public void should_roll_succeeded_by_natural_critical_of_4() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(1, 2, 1);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (1 + 2 + 1) = 4 [CRITICAL SUCCESS]");
    }

    @Test
    public void should_roll_succeeded_by_critical_success_of_5_with_effective_value_15() {
        effectiveValueOf(15);
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(2, 2, 1);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (2 + 2 + 1) = 5 [CRITICAL SUCCESS]");
    }

    @Test
    public void should_roll_succeeded_without_critical_of_6_with_effective_value_15() {
        effectiveValueOf(15);
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(1, 1, 4);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (1 + 1 + 4) = 6 Succeeded by: 9");
    }

    @Test
    public void should_roll_succeeded_by_critical_success_of_5_with_effective_value_16() {
        effectiveValueOf(16);
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(1, 1, 3);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (1 + 1 + 3) = 5 [CRITICAL SUCCESS]");
    }

    @Test
    public void should_roll_succeeded_by_critical_success_of_6_with_effective_value_16() {
        effectiveValueOf(16);
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(2, 2, 2);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsSucceeded();
        assertFormatIsEqualsTo("Dice Result: (2 + 2 + 2) = 6 [CRITICAL SUCCESS]");
    }

    @Test
    public void should_roll_failed_by_1() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(2, 4, 5);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsFailed();
        assertFormatIsEqualsTo("Dice Result: (2 + 4 + 5) = 11 Failed by: -1");
    }

    @Test
    public void should_roll_failed_by_natural_critical() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(6, 6, 6);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsFailed();
        assertFormatIsEqualsTo("Dice Result: (6 + 6 + 6) = 18 [CRITICAL MISS]");
    }

    @Test
    public void should_roll_failed_by_normal_critical_miss_of_17() {
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(6, 6, 5);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsFailed();
        assertFormatIsEqualsTo("Dice Result: (6 + 6 + 5) = 17 [CRITICAL MISS]");
    }

    @Test
    public void should_roll_failed_without_critical_miss_of_17_with_effective_value_16() {
        effectiveValueOf(16);
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(5, 6, 6);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsFailed();
        assertFormatIsEqualsTo("Dice Result: (5 + 6 + 6) = 17 Failed by: -1");
    }

    @Test
    public void should_roll_return_critical_miss_at_most_10_Negative() {
        effectiveValueOf(5);
        buildDefaultActionRollerWithBuilder();
        buildRollResultSumOf(5, 5, 5);
        mockDiceRepresentationRollReturnExpectedResultSum();
        roll();
        format();
        assertActionRollResultIsFailed();
        assertFormatIsEqualsTo("Dice Result: (5 + 5 + 5) = 15 [CRITICAL MISS]");
    }

    private void effectiveValueOf(int value) {
        effectiveValue = EffectiveValue.of(value);
    }

    private void buildRollResultSumOf(int first, int second, int third) {
        rollResultSum = RollResultSum.of(SingleRollResult.of(NumberOfDices.ONE, first),
                SingleRollResult.of(NumberOfDices.of(2), second),
                SingleRollResult.of(NumberOfDices.of(3), third));
    }

    private void assertActionRollResultIsSucceeded() {
        assertThat(actionRollResult)
                .isNotNull()
                .extracting(ActionRollResult::isSuccess)
                .containsExactly(true);
    }

    private void assertActionRollResultIsFailed() {
        assertThat(actionRollResult)
                .isNotNull()
                .extracting(ActionRollResult::isSuccess)
                .containsExactly(false);
    }

    private void assertFormatIsEqualsTo(String expected) {
        assertThat(format)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void mockDiceRepresentationRollReturnExpectedResultSum() {
        when(diceRepresentation.roll())
                .thenReturn(rollResultSum);
    }

    private void assertActionRollResultIsNotNull() {
        assertThat(actionRollResult)
                .isNotNull();
    }

    private void format() {
        format = String.format("%s", actionRollResult);
    }

    private void buildDefaultActionRollerWithBuilder() {
        defaultActionRoller = DefaultActionRoller.of(actionRollerBuilder);
    }

    private void roll() {
        actionRollResult = defaultActionRoller.roll(effectiveValue);
    }

    private void buildDefaultActionRoller() {
        defaultActionRoller = DefaultActionRoller.ofDefault();
    }
}