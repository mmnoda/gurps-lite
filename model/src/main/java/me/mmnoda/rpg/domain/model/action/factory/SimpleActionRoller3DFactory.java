package me.mmnoda.rpg.domain.model.action.factory;

import me.mmnoda.rpg.domain.model.action.ActionRoller;
import me.mmnoda.rpg.domain.model.action.DefaultActionRoller;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.custom.HighestValueOfNResultDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.custom.LowestValueOfNResultDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.custom.MaxValueDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.custom.MinValueDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.factory.DefaultRollablesFactory;

/**
 *
 */
public enum SimpleActionRoller3DFactory {

    DEFAULT {
        @Override
        public ActionRoller build(final CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(DefaultRollablesFactory.INSTANCE, criticalDetermination));
        }
    },

    MIN_VALUE_ROLL {
        @Override
        public ActionRoller build(CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> MinValueDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    },

    MAX_VALUE_ROLL {
        @Override
        public ActionRoller build(CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> MaxValueDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    },

    BEST_OF_3_ROLLS {
        @Override
        public ActionRoller build(CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> LowestValueOfNResultDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6(), 3),
                    criticalDetermination));
        }
    },

    WORST_OF_3_ROLLS {
        @Override
        public ActionRoller build(CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> HighestValueOfNResultDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6(), 3),
                    criticalDetermination));
        }
    };

    public abstract ActionRoller build(CriticalDetermination criticalDetermination);
}
