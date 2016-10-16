package me.mmnoda.rpg.domain.model.rollable.dice_representation.result;

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
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 *
 */
public class RollResultSumTest {

    private RollResultSum rollResultSum;

    private String formatted;

    @Test
    public void should_format_one_roll_without_adjustment() {
        rollResultSum = RollResultSum.builder()
                .add(SingleRollResult.of(2))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(2) = 2");
    }

    @Test
    public void should_format_3_roll_without_adjustment() {
        rollResultSum = RollResultSum.builder()
                .add(SingleRollResult.of(2))
                .add(SingleRollResult.of(3))
                .add(SingleRollResult.of(6))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(2 + 3 + 6) = 11");
    }

    @Test
    public void should_format_one_roll_with_adjustment() {
        rollResultSum = RollResultSum.builder()
                .withAdjustment(DiceAdjustment.of(3))
                .add(SingleRollResult.of(4))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(4)+3 = 7");
    }

    @Test
    public void should_format_4_roll_with_adjustment() {
        rollResultSum = RollResultSum.builder()
                .withAdjustment(DiceAdjustment.of(1))
                .add(SingleRollResult.of(1))
                .add(SingleRollResult.of(2))
                .add(SingleRollResult.of(3))
                .add(SingleRollResult.of(4))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(1 + 2 + 3 + 4)+1 = 11");
    }

    @Test(expected = IllegalStateException.class)
    public void should_validate_that_exists_at_least_one_roll_result() {
        rollResultSum = RollResultSum.builder()
                .build();
        fail();
    }

    @Test
    public void should_double_value() {
        rollResultSum = RollResultSum.builder()
                .withAdjustment(DiceAdjustment.of(3))
                .add(SingleRollResult.of(2))
                .add(SingleRollResult.of(4))
                .add(SingleRollResult.of(6))
                .build();

        doubleValue();
        format();
        assertOverallOfRollResultSumIsEqualTo(OverallRollSumValue.of(30));
        assertFormattedIsEqualTo("[(2 + 4 + 6)+3] * 2 = 30");
    }

    @Test
    public void should_triple_value() {
        rollResultSum = RollResultSum.builder()
                .withAdjustment(DiceAdjustment.of(1))
                .add(SingleRollResult.of(3))
                .add(SingleRollResult.of(5))
                .build();

        tripleValue();
        format();
        assertOverallOfRollResultSumIsEqualTo(OverallRollSumValue.of(27));
        assertFormattedIsEqualTo("[(3 + 5)+1] * 3 = 27");
    }

    private void assertOverallOfRollResultSumIsEqualTo(OverallRollSumValue of) {
        assertThat(rollResultSum)
                .isNotNull()
                .extracting(RollResultSum::getOverall)
                .contains(of);
    }

    private void tripleValue() {
        rollResultSum = this.rollResultSum.tripleValue();
    }

    private void doubleValue() {
        rollResultSum = this.rollResultSum.doubleValue();
    }

    private void assertFormattedIsEqualTo(String expected) {
        assertThat(formatted)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void format() {
        formatted = String.format("%s", rollResultSum);
    }

    private void assertRollResultSumIsNotNull() {
        assertThat(rollResultSum)
                .isNotNull();
    }
}