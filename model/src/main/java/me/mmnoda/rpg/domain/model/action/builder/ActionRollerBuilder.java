package me.mmnoda.rpg.domain.model.action.builder;

import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDeterminationFactory;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.factory.DefaultRollablesFactory;

/**
 *
 */
public interface ActionRollerBuilder {

    default DiceRepresentation getRollables() {
        return DefaultRollablesFactory.INSTANCE.build3D6();
    }

    default CriticalDetermination getCriticalDetermination() {
        return CriticalDeterminationFactory.INSTANCE.buildDefault();
    }

}
