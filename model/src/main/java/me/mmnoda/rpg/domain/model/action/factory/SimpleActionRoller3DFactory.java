package me.mmnoda.rpg.domain.model.action.factory;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2015 - 2016 Márcio Noda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import me.mmnoda.rpg.domain.model.action.ActionRoller;
import me.mmnoda.rpg.domain.model.action.DefaultActionRoller;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.custom.*;
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

    AVG_VALUE_ROLL {
        @Override
        public ActionRoller build(CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> AvgValueDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6()),
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
