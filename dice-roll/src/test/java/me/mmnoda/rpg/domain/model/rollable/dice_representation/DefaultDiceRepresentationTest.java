package me.mmnoda.rpg.domain.model.rollable.dice_representation;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation.DefaultDiceRepresentation;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultDiceRepresentationTest {

    private DefaultDiceRepresentation defaultDiceRepresentation;

    private String formatted;

    @Mock
    private SingleRollable singleRollable;

    @After
    public void tearDown() throws Exception {
        reset(singleRollable);
    }

    @Test
    public void should_format_with_adjustment_positive() {
        defaultDiceRepresentation = DefaultDiceRepresentation.of(singleRollable, NumberOfDices.THREE, DiceAdjustment.of(7));
        format();
        assertFormattedIsEqualTo("3D + 7");
    }

    @Test
    public void should_format_with_adjustment_negative() {
        defaultDiceRepresentation = DefaultDiceRepresentation.of(singleRollable, NumberOfDices.of(12), DiceAdjustment.of(-10));
        format();
        assertFormattedIsEqualTo("12D - 10");

    }

    @Test
    public void should_format_without_adjustment() {
        defaultDiceRepresentation = DefaultDiceRepresentation.of(singleRollable, NumberOfDices.of(5));
        format();
        assertFormattedIsEqualTo("5D");

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