package me.mmnoda.rpg.domain.model.rollable.dice_representation.factory;

import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;

/**
 *
 */
@FunctionalInterface
public interface RollablesFactory {
    DiceRepresentation build3D6();
}
