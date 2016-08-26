package me.mmnoda.rpg.model.dice.factory;

import me.mmnoda.rpg.model.rollable.SingleRollable;

/**
 *
 */
public interface DiceFactory {

    SingleRollable getD6();

    SingleRollable getD100();
}
