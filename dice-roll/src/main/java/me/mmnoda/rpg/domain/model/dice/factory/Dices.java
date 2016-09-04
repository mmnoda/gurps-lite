package me.mmnoda.rpg.domain.model.dice.factory;

import me.mmnoda.rpg.domain.model.dice.Dice;

import static me.mmnoda.rpg.domain.model.dice.Dice.of;
import static me.mmnoda.rpg.domain.model.dice.NumberOfFaces._100;
import static me.mmnoda.rpg.domain.model.dice.NumberOfFaces._6;

/**
 *
 */
public enum Dices implements DicesFactory {

    D6(of(_6)),

    D100(of(_100));

    private final Dice dice;

    Dices(Dice dice) {
        this.dice = dice;
    }

    public final Dice getInstance() {
        return dice;
    }
}
