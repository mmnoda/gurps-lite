package me.mmnoda.rpg.domain.model.action.critical;

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
// TODO implement template method
public enum DefaultCriticalDetermination implements CriticalDetermination {

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
            return isCriticalMiss(effectiveValue, rollResultSum, differenceOfRoll) ? CriticalStatus.CRITICAL_MISS :
                    delegateToCriticalSuccess(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private boolean isCriticalMiss(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return rollResultSum.isNaturalCriticalMiss() || rollResultSum.isCriticalMiss(effectiveValue) || differenceOfRoll.isFailedAt10Negative();
        }

        private CriticalStatus delegateToCriticalSuccess(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return CRITICAL_SUCCESS.determine(effectiveValue, rollResultSum, differenceOfRoll);
        }

    },

    CRITICAL_SUCCESS {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return isCriticalSuccess(effectiveValue, rollResultSum) ? CriticalStatus.CRITICAL_SUCCESS :
                    delegateToNormal(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private CriticalStatus delegateToNormal(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return NORMAL.determine(effectiveValue, rollResultSum, differenceOfRoll);
        }

        private boolean isCriticalSuccess(EffectiveValue effectiveValue, RollResultSum rollResultSum) {
            return rollResultSum.isNaturalCriticalSuccess() || rollResultSum.isCriticalSuccess(effectiveValue);
        }

    },

    NORMAL {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, RollResultSum rollResultSum, DifferenceOfRoll differenceOfRoll) {
            return CriticalStatus.NORMAL;
        }
    }

}
