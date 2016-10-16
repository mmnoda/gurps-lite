package me.mmnoda.rpg.domain.model.rollable.dice_representation;

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

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;
import org.junit.After;
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
public class DefaultDiceRepresentationTest {

    private DefaultDiceRepresentation defaultDiceRepresentation;

    private String formatted;

    private RollResultSum rollResultSum;

    @Mock
    private SingleRollable singleRollable;

    @After
    public void tearDown() {
        reset(singleRollable);
    }

    @Test
    public void should_format_with_adjustment_positive() {
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.THREE, DiceAdjustment.of(7));
        format();
        assertFormattedIsEqualTo("3d+7");
    }

    @Test
    public void should_format_with_adjustment_negative() {
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.of(12), DiceAdjustment.of(-10));
        format();
        assertFormattedIsEqualTo("12d-10");

    }

    @Test
    public void should_format_without_adjustment() {
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.of(5));
        format();
        assertFormattedIsEqualTo("5d");
    }

    @Test
    public void should_roll_without_adjustment() {
        mockSingleRollableReturnRollEqualTo(SingleRollResult.of(3));
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.of(3));
        roll();
        assertRollResultSumIsEqualTo(RollResultSum
                .builder()
                .add(SingleRollResult.of(3))
                .add(SingleRollResult.of(3))
                .add(SingleRollResult.of(3))
                .build());
        verify(singleRollable, times(3)).roll(any());

    }

    @Test
    public void should_roll_with_adjustment() {
        mockSingleRollableReturnRollEqualTo(SingleRollResult.of(5));
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.of(2), DiceAdjustment.of(3));
        roll();
        assertRollResultSumIsEqualTo(RollResultSum
                .builder()
                .add(SingleRollResult.of(5))
                .add(SingleRollResult.of(5))
                .withAdjustment(DiceAdjustment.of(3))
                .build());

    }

    private void assertRollResultSumIsEqualTo(RollResultSum build) {
        assertThat(rollResultSum)
                .isNotNull()
                .isEqualTo(build);
    }

    private void buildDefaultDiceRepresentation(SingleRollable singleRollable, NumberOfDices three, DiceAdjustment of) {
        defaultDiceRepresentation = DefaultDiceRepresentation.of(singleRollable, three, of);
    }

    private void buildDefaultDiceRepresentation(SingleRollable singleRollable, NumberOfDices of) {
        defaultDiceRepresentation = DefaultDiceRepresentation.of(singleRollable, of);
    }

    private void mockSingleRollableReturnRollEqualTo(final SingleRollResult singleRollResult) {
        when(singleRollable.roll(any())).thenReturn(singleRollResult);
    }

    private void roll() {
        rollResultSum = defaultDiceRepresentation.roll();
    }

    private void assertFormattedIsEqualTo(String expected) {
        assertThat(formatted)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void format() {
        formatted = String.format("%s", defaultDiceRepresentation);
    }
}