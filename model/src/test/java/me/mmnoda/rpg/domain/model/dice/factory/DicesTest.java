package me.mmnoda.rpg.domain.model.dice.factory;

import me.mmnoda.rpg.domain.model.dice.Dice;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DicesTest {

    private Dices factory;
    private Dice dice;

    @Test
    public void should_build_d100() {
        factory = Dices.D100;
        getInstance();
        assertDiceIsEqualsTo(Dice.of(NumberOfFaces._100));
    }

    @Test
    public void should_build_d6() {
        factory = Dices.D6;
        getInstance();
        assertDiceIsEqualsTo(Dice.of(NumberOfFaces._6));
    }

    private void assertDiceIsEqualsTo(Dice expected) {
        assertThat(this.dice)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void getInstance() {
        dice = factory.getInstance();
    }

}