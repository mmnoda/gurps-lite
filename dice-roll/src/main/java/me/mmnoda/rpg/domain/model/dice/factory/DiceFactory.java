package me.mmnoda.rpg.domain.model.dice.factory;

import me.mmnoda.rpg.domain.model.rollable.SingleRollable;

/**
 *
 */
public interface DiceFactory {

    SingleRollable getD6();

    SingleRollable getD100();
}
