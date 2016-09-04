package me.mmnoda.rpg.domain.model.rollable.dice_representation.result;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
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
                .add(NumberOfDices.of(1), SingleRollResult.of(2))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(2) = 2");
    }

    @Test
    public void should_format_3_roll_without_adjustment() {
        rollResultSum = RollResultSum.builder()
                .add(NumberOfDices.of(1), SingleRollResult.of(2))
                .add(NumberOfDices.of(2), SingleRollResult.of(3))
                .add(NumberOfDices.of(3), SingleRollResult.of(6))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(2 + 3 + 6) = 11");
    }

    @Test
    public void should_format_one_roll_with_adjustment() {
        rollResultSum = RollResultSum.builder()
                .withAdjustment(DiceAdjustment.of(3))
                .add(NumberOfDices.of(1), SingleRollResult.of(4))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(4) + 3 = 7");
    }

    @Test
    public void should_format_4_roll_with_adjustment() {
        rollResultSum = RollResultSum.builder()
                .withAdjustment(DiceAdjustment.of(1))
                .add(NumberOfDices.of(1), SingleRollResult.of(1))
                .add(NumberOfDices.of(2), SingleRollResult.of(2))
                .add(NumberOfDices.of(3), SingleRollResult.of(3))
                .add(NumberOfDices.of(4), SingleRollResult.of(4))
                .build();

        format();
        assertRollResultSumIsNotNull();
        assertFormattedIsEqualTo("(1 + 2 + 3 + 4) + 1 = 11");
    }

    @Test(expected = IllegalStateException.class)
    public void should_validate_that_exists_at_least_one_roll_result() {
        rollResultSum = RollResultSum.builder()
                .build();
        fail();
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