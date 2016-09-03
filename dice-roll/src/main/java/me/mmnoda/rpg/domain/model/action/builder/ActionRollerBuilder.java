package me.mmnoda.rpg.domain.model.action.builder;

import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.action.critical.DefaultCriticalDetermination;
import me.mmnoda.rpg.domain.model.rollable.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.DefaultRollablesFactory;

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
