package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.SingleRollable;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;
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
public class MinValueDiceRepresentationTest {

    private MinValueDiceRepresentation minValueDiceRepresentation;

    private RollResultSum rollResultSum;

    private DiceAdjustment adjustment;

    @Mock
    private SingleRollable singleRollable;

    @Before
    public void setUp() {
        adjustment = DiceAdjustment.ZERO;
    }

    @After
    public void tearDown() {
        reset(singleRollable);
    }

    @Test
    public void should_roll_without_adjustment() {
        mockSingleRollableReturn6Faces();
        buildMinValueDiceRepresentation();
        roll();
        assertRollResultSumIsEqualToMin();
        verify(singleRollable).getNumberOfFaces();
    }

    @Test
    public void should_roll_with_adjustment() {
        mockSingleRollableReturn6Faces();
        adjustment = DiceAdjustment.of(2);
        buildMinValueDiceRepresentation();
        roll();
        assertRollResultSumIsEqualToMin();
        verify(singleRollable).getNumberOfFaces();
    }

    private void buildMinValueDiceRepresentation() {
        minValueDiceRepresentation = MinValueDiceRepresentation
                .of(DefaultDiceRepresentation.of(singleRollable, NumberOfDices.THREE, adjustment));
    }

    private void mockSingleRollableReturn6Faces() {
        when(singleRollable.getNumberOfFaces()).thenReturn(NumberOfFaces._6);
    }

    private void assertRollResultSumIsEqualToMin() {
        final SingleRollResult minSingleResult = SingleRollResult.of(1);
        assertThat(rollResultSum)
                .isNotNull()
                .isEqualTo(RollResultSum.builder()
                        .withAdjustment(adjustment)
                        .add(NumberOfDices.of(1), minSingleResult)
                        .add(NumberOfDices.of(2), minSingleResult)
                        .add(NumberOfDices.of(3), minSingleResult)
                        .build());
    }

    private void roll() {
        rollResultSum = minValueDiceRepresentation.roll();
    }
}