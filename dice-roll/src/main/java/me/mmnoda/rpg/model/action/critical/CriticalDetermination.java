package me.mmnoda.rpg.model.action.critical;

import me.mmnoda.rpg.model.action.EffectiveValue;
import me.mmnoda.rpg.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.model.dice.result.DiceSum;

/**
 *
 */
public interface CriticalDetermination {

    CriticalStatus determine(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll);
}
