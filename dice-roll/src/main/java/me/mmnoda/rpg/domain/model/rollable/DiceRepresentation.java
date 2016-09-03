package me.mmnoda.rpg.domain.model.rollable;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.dice.result.RollResultSum;

/**
 *
 */
public interface DiceRepresentation {

    RollResultSum roll();

    NumberOfFaces getNumberOfFaces();

    DiceAdjustment getDiceAdjustment();
}
