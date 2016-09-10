package me.mmnoda.rpg.domain.model.rollable.dice_representation;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
public interface DiceRepresentation {

    RollResultSum roll();

    NumberOfFaces getNumberOfFaces();

    DiceAdjustment getDiceAdjustment();

    NumberOfDices getNumberOfDices();
}
