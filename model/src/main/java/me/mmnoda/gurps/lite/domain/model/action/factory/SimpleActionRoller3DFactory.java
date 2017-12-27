package me.mmnoda.gurps.lite.domain.model.action.factory;

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

import me.mmnoda.gurps.lite.domain.model.action.ActionRoller;
import me.mmnoda.gurps.lite.domain.model.action.DefaultActionRoller;
import me.mmnoda.gurps.lite.domain.model.action.critical.determination.CriticalDetermination;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.custom.CustomDiceRepresentationFactoryMethod;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.factory.DefaultRollablesFactory;

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
        public ActionRoller build(final CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> CustomDiceRepresentationFactoryMethod.INSTANCE.buildMinValue(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    },

    MAX_VALUE_ROLL {
        @Override
        public ActionRoller build(final CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> CustomDiceRepresentationFactoryMethod.INSTANCE.buildMaxValue(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    },

    AVG_VALUE_ROLL {
        @Override
        public ActionRoller build(final CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> CustomDiceRepresentationFactoryMethod.INSTANCE.buildAvgValue(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    },

    BEST_OF_3_ROLLS {
        @Override
        public ActionRoller build(final CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> CustomDiceRepresentationFactoryMethod.INSTANCE.buildLowestValueOf3(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    },

    WORST_OF_3_ROLLS {
        @Override
        public ActionRoller build(final CriticalDetermination criticalDetermination) {
            return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> CustomDiceRepresentationFactoryMethod.INSTANCE.buildHighestValueOf3(DefaultRollablesFactory.INSTANCE.build3D6()),
                    criticalDetermination));
        }
    };

    public abstract ActionRoller build(CriticalDetermination criticalDetermination);
}
