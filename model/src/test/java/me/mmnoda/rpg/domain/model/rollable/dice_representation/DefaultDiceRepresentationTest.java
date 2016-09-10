package me.mmnoda.rpg.domain.model.rollable.dice_representation;

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
        assertFormattedIsEqualTo("3D + 7");
    }

    @Test
    public void should_format_with_adjustment_negative() {
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.of(12), DiceAdjustment.of(-10));
        format();
        assertFormattedIsEqualTo("12D - 10");

    }

    @Test
    public void should_format_without_adjustment() {
        buildDefaultDiceRepresentation(singleRollable, NumberOfDices.of(5));
        format();
        assertFormattedIsEqualTo("5D");
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