package me.mmnoda.rpg.model.action.builder;

import me.mmnoda.rpg.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.model.action.critical.DefaultCriticalDetermination;
import me.mmnoda.rpg.model.rollable.DiceRepresentation;
import me.mmnoda.rpg.model.rollable.DefaultRollablesFactory;

/**
 *
 */
public interface ActionRollerBuilder {

    default DiceRepresentation getRollables() {
        return DefaultRollablesFactory.INSTANCE.build3D6();
    }

    default CriticalDetermination getCriticalDetermination() {
        return DefaultCriticalDetermination.TO_FIND_OUT;
    }

}
