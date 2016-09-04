package me.mmnoda.rpg.domain.model.dice;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DiceAdjustmentTest {

    private DiceAdjustment diceAdjustment;

    private String formatted;

    @Before
    public void setUp() throws Exception {
        diceAdjustment = DiceAdjustment.ZERO;
    }

    @Test
    public void should_format_to_zero_value() {
        diceAdjustment = DiceAdjustment.ZERO;
        format();
        assertFormattedIsEqualTo("");
    }

    @Test
    public void should_format_to_positive_value() {
        diceAdjustment = DiceAdjustment.of(1);
        format();
        assertFormattedIsEqualTo("+ 1");
    }

    @Test
    public void should_format_to_negative_value() {
        diceAdjustment = DiceAdjustment.of(-5);
        format();
        assertFormattedIsEqualTo("- 5");
    }

    private void assertFormattedIsEqualTo(String expected) {
        assertThat(formatted)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void format() {
        formatted = String.format("%s", diceAdjustment);
    }

}