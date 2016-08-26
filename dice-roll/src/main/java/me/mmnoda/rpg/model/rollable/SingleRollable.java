package me.mmnoda.rpg.model.rollable;

import me.mmnoda.rpg.model.dice.component.NumberOfFaces;
import me.mmnoda.rpg.model.dice.result.SingleRollResult;

import java.util.Random;

/**
 *
 */
public interface SingleRollable {

    default SingleRollResult roll() {
        return SingleRollResult.valueOf(new Random().nextInt(getNumberOfFaces().intValue()) + 1);

    }

    NumberOfFaces getNumberOfFaces();

}
