package me.mmnoda.rpg.domain.model.action.factory;

import me.mmnoda.rpg.domain.model.action.ActionRoller;
import me.mmnoda.rpg.domain.model.action.DefaultActionRoller;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.custom.ArbitraryValuesDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.factory.DefaultRollablesFactory;

/**
 *
 */
public enum ManualInputActionRoller3DFactory {

    INSTANCE;

    public ActionRoller build(final CriticalDetermination criticalDetermination, SingleRollResult first, SingleRollResult... expectedResults) {
        return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> ArbitraryValuesDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6(), first, expectedResults),
                criticalDetermination));
    }
}
