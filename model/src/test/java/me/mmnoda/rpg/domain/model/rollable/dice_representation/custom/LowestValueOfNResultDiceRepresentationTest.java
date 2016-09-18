package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

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
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;
import org.junit.After;
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
public class LowestValueOfNResultDiceRepresentationTest {

    private static final RollResultSum LOWEST_ROLL_RESULT_WITHOUT_ADJUSTMENT = RollResultSum.of(SingleRollResult.of(NumberOfDices.ONE, 1));
    private static final RollResultSum LOWEST_ROLL_RESULT_WITH_ADJUSTMENT = RollResultSum.of(DiceAdjustment.of(3), SingleRollResult.of(NumberOfDices.ONE, 1));

    private LowestValueOfNResultDiceRepresentation lowestValueOfNResultDiceRepresentation;

    @Mock
    private DiceRepresentation diceRepresentation;
    private RollResultSum rollResultSum;

    @After
    public void tearDown(){
        reset(diceRepresentation);
    }

    @Test
    public void should_return_lowest_value_of_3_attempts_without_adjustment() {
        when(diceRepresentation.roll())
                .thenReturn(RollResultSum.of(SingleRollResult.of(NumberOfDices.ONE, 1)), LOWEST_ROLL_RESULT_WITHOUT_ADJUSTMENT,
                        RollResultSum.of(SingleRollResult.of(NumberOfDices.ONE, 5)));

        buildHighestValueOfNResultDiceRepresentation(diceRepresentation, 3);
        roll();
        assertRollResultSumIsEqualTo(LOWEST_ROLL_RESULT_WITHOUT_ADJUSTMENT);

    }

    @Test
    public void should_return_lowest_value_of_2_attempts_with_adjustment() {
        when(diceRepresentation.roll())
                .thenReturn(LOWEST_ROLL_RESULT_WITH_ADJUSTMENT,
                        RollResultSum.of(DiceAdjustment.of(3), SingleRollResult.of( NumberOfDices.ONE, 4)));

        buildHighestValueOfNResultDiceRepresentation(diceRepresentation, 2);
        roll();
        assertRollResultSumIsEqualTo(LOWEST_ROLL_RESULT_WITH_ADJUSTMENT);

    }

    private void buildHighestValueOfNResultDiceRepresentation(DiceRepresentation diceRepresentation, int numberOfRolls) {
        lowestValueOfNResultDiceRepresentation = LowestValueOfNResultDiceRepresentation.of(diceRepresentation, numberOfRolls);
    }

    private void assertRollResultSumIsEqualTo(RollResultSum highestRollResultWithoutAdjustment) {
        assertThat(rollResultSum)
                .isNotNull()
                .isEqualTo(highestRollResultWithoutAdjustment);
    }

    private void roll() {
        rollResultSum = lowestValueOfNResultDiceRepresentation.roll();
    }
}