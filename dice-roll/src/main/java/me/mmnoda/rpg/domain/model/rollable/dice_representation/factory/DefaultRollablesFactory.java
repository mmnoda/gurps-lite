package me.mmnoda.rpg.domain.model.rollable.dice_representation.factory;

import me.mmnoda.rpg.domain.model.dice.factory.Dices;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation.DiceRepresentation;

import static me.mmnoda.rpg.domain.model.dice.NumberOfDices.THREE;

/**
 *
 */
public enum DefaultRollablesFactory implements RollablesFactory {
    INSTANCE;

    @Override
    public DiceRepresentation build3D6() {
        return DefaultDiceRepresentation
                .of(Dices.D6.getInstance(), THREE);
    }
}
