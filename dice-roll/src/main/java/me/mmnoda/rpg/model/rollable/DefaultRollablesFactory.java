package me.mmnoda.rpg.model.rollable;

import me.mmnoda.rpg.model.dice.factory.Dices;

import static me.mmnoda.rpg.model.dice.component.NumberOfDices.THREE;

/**
 *
 */
public enum DefaultRollablesFactory implements RollablesFactory {
    INSTANCE;

    @Override
    public DiceRepresentation build3D6() {
        return DefaultDiceRepresentation.builder().
                withNumberOfDices(THREE).withRollable(Dices.INSTANCE.getD6()).
                build();
    }
}
