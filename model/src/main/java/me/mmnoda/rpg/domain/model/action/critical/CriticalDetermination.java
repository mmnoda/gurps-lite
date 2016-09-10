package me.mmnoda.rpg.domain.model.action.critical;

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
public interface CriticalDetermination {

    CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll);
}
