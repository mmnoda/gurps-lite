package me.mmnoda.rpg.domain.model.action.critical;

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

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
enum OnlyNaturalCriticalDetermination implements CriticalDetermination {

    TO_FIND_OUT {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return delegateToCriticalMiss(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private CriticalStatus delegateToCriticalMiss(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return CRITICAL_MISS.determine(effectiveValue, rollResultSum, differenceOfRoll);
        }
    },

    CRITICAL_MISS {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return isCriticalMiss(rollResultSum) ? CriticalStatus.CRITICAL_MISS :
                    delegateToCriticalSuccess(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private boolean isCriticalMiss(RollResultSum rollResultSum) {
            return rollResultSum.isNaturalCriticalMiss();
        }

        private CriticalStatus delegateToCriticalSuccess(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return CRITICAL_SUCCESS.determine(effectiveValue, rollResultSum, differenceOfRoll);
        }
    },

    CRITICAL_SUCCESS {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return isCriticalSuccess(rollResultSum) ? CriticalStatus.CRITICAL_SUCCESS :
                    delegateToNormal(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private CriticalStatus delegateToNormal(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return NORMAL.determine(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private boolean isCriticalSuccess(RollResultSum rollResultSum) {
            return rollResultSum.isNaturalCriticalSuccess();
        }

    },

    NORMAL {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return CriticalStatus.NORMAL;
        }
    }


}
