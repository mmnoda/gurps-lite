package me.mmnoda.rpg.domain.model.action.critical;

import me.mmnoda.rpg.domain.model.action.result.HasIndicatorOfSuccess;

/**
 *
 */
public enum CriticalStatus {

    CRITICAL_SUCCESS {

        @Override
        public boolean isSuccess(HasIndicatorOfSuccess result) {
            return true;
        }

        @Override
        public String format(HasIndicatorOfSuccess result) {
            return "[CRITICAL SUCCESS]";
        }
    },

    CRITICAL_MISS {

        @Override
        public boolean isSuccess(HasIndicatorOfSuccess result) {
            return false;
        }

        @Override
        public String format(HasIndicatorOfSuccess result) {
            return "[CRITICAL MISS]";
        }
    },

    NORMAL {

        @Override
        public boolean isSuccess(HasIndicatorOfSuccess result) {
            return result.succeededByDiceSum();
        }

        @Override
        public String format(HasIndicatorOfSuccess result) {
            return String.format("%s: %s", result.succeededByDiceSum() ? "Succeeded by" : "Failed by", result.getDifferenceOfRoll());
        }
    };

    public abstract boolean isSuccess(HasIndicatorOfSuccess result);

    public abstract String format(HasIndicatorOfSuccess result);
}
