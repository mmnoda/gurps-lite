package me.mmnoda.rpg.domain.model.action.critical;

import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.dice.result.DiceSum;

/**
 *
 */
public enum DefaultCriticalDetermination implements CriticalDetermination {

    TO_FIND_OUT {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return delegateToCriticalMiss(effectiveValue, diceSum, differenceOfRoll);
        }

        private CriticalStatus delegateToCriticalMiss(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return CRITICAL_MISS.determine(effectiveValue, diceSum, differenceOfRoll);
        }
    },

    CRITICAL_MISS {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return isCriticalMiss(effectiveValue, diceSum, differenceOfRoll) ? CriticalStatus.CRITICAL_MISS :
                    delegateToCriticalSuccess(effectiveValue, diceSum, differenceOfRoll);
        }

        private boolean isCriticalMiss(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return diceSum.isNaturalCriticalMiss() || diceSum.isCriticalMiss(effectiveValue) || differenceOfRoll.isFailedAt10Negative();
        }

        private CriticalStatus delegateToCriticalSuccess(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return CRITICAL_SUCCESS.determine(effectiveValue, diceSum, differenceOfRoll);
        }

    },

    CRITICAL_SUCCESS {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return isCriticalSuccess(effectiveValue, diceSum) ? CriticalStatus.CRITICAL_SUCCESS :
                    delegateToNormal(effectiveValue, diceSum, differenceOfRoll);
        }

        private CriticalStatus delegateToNormal(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return NORMAL.determine(effectiveValue, diceSum, differenceOfRoll);
        }

        private boolean isCriticalSuccess(EffectiveValue effectiveValue, DiceSum diceSum) {
            return diceSum.isNaturalCriticalSuccess() || diceSum.isCriticalSuccess(effectiveValue);
        }

    },

    NORMAL {
        @Override
        public CriticalStatus determine(EffectiveValue effectiveValue, DiceSum diceSum, DifferenceOfRoll differenceOfRoll) {
            return CriticalStatus.NORMAL;
        }
    }

}
