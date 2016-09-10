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
public class AvgValueDiceRepresentationTest {

    private AvgValueDiceRepresentation avgValueDiceRepresentation;

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
        buildAvgValueDiceRepresentation();
        roll();
        assertRollResultSumIsEqualToAvg();
        verify(singleRollable).getNumberOfFaces();
    }

    @Test
    public void should_roll_with_adjustment() {
        mockSingleRollableReturn6Faces();
        adjustment = DiceAdjustment.of(1);
        buildAvgValueDiceRepresentation();
        roll();
        assertRollResultSumIsEqualToAvg();
        verify(singleRollable).getNumberOfFaces();
    }

    private void buildAvgValueDiceRepresentation() {
        avgValueDiceRepresentation = AvgValueDiceRepresentation
                .of(DefaultDiceRepresentation.of(singleRollable, NumberOfDices.THREE, adjustment));
    }

    private void mockSingleRollableReturn6Faces() {
        when(singleRollable.getNumberOfFaces()).thenReturn(NumberOfFaces._6);
    }

    private void assertRollResultSumIsEqualToAvg() {
        assertThat(rollResultSum)
                .isNotNull()
                .isEqualTo(RollResultSum.of(adjustment, SingleRollResult.of(NumberOfDices.ONE, 3), SingleRollResult.of(NumberOfDices.of(2), 3),
                        SingleRollResult.of(NumberOfDices.of(3), 3)));

    }

    private void roll() {
        rollResultSum = avgValueDiceRepresentation.roll();
    }

}