package me.mmnoda.rpg.domain.model.action.critical;

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.dice.result.DiceSum;

/**
 *
 */
public interface CriticalDetermination {

    CriticalStatus determine(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll);
}
