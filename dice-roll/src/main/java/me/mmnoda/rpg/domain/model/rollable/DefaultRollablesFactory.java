package me.mmnoda.rpg.domain.model.rollable;

import me.mmnoda.rpg.domain.model.dice.factory.Dices;

import static me.mmnoda.rpg.domain.model.dice.NumberOfDices.THREE;

/**
 *
 */
public enum DefaultRollablesFactory implements RollablesFactory {
    INSTANCE;

    @Override
    public DiceRepresentation build3D6() {
        return DefaultDiceRepresentation
                .builder()
                .withNumberOfDices(THREE)
                .withRollable(Dices.INSTANCE.getD6())
                .build();
    }
}
