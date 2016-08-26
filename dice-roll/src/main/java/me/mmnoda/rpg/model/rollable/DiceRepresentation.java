package me.mmnoda.rpg.model.rollable;

import me.mmnoda.rpg.model.dice.DiceAdjustment;
import me.mmnoda.rpg.model.dice.component.NumberOfFaces;
import me.mmnoda.rpg.model.dice.result.RollResultSum;

/**
 *
 */
public interface DiceRepresentation {

    RollResultSum roll();

    NumberOfFaces getNumberOfFaces();

    DiceAdjustment getDiceAdjustment();
}
