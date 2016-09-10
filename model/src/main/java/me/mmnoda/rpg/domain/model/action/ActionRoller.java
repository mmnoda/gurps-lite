package me.mmnoda.rpg.domain.model.action;

import me.mmnoda.rpg.domain.model.action.result.ActionRollResult;

/**
 *
 */
public interface ActionRoller {
    ActionRollResult roll(EffectiveValue effectiveValue);
}
