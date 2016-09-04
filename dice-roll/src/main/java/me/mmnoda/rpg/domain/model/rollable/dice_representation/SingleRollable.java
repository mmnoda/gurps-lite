package me.mmnoda.rpg.domain.model.rollable.dice_representation;

import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;

import java.util.Random;

/**
 *
 */
public interface SingleRollable {

    default SingleRollResult roll() {
        return SingleRollResult.of(new Random().nextInt(getNumberOfFaces().intValue()) + 1);

    }

    NumberOfFaces getNumberOfFaces();
}
