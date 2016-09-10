package me.mmnoda.rpg.domain.model.dice;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DiceTest {

    private Dice dice;

    private String valueFormatted;

    @Test
    public void should_format_D6() {
        with(NumberOfFaces._6);
        format();
        assertValueFormattedIsEqual("6");
    }

    @Test
    public void should_format_D100() {
        with(NumberOfFaces._100);
        format();
        assertValueFormattedIsEqual("100");
    }

    private void with(NumberOfFaces numberOfFaces) {
        dice = Dice.of(numberOfFaces);
    }

    private void format() {
        valueFormatted = String.format("%s", dice);
    }

    private void assertValueFormattedIsEqual(String expected) {
        assertThat(valueFormatted)
                .isNotNull()
                .isEqualTo(expected);
    }
}