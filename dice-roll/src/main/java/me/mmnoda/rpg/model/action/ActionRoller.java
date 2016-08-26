package me.mmnoda.rpg.model.action;

import me.mmnoda.rpg.model.action.result.ActionRollResult;

/**
 *
 */
public interface ActionRoller {
    ActionRollResult roll(EffectiveValue effectiveValue);
}
