package me.mmnoda.rpg.model.action.critical;

import me.mmnoda.rpg.model.action.result.HasIndicatorOfSuccess;

/**
 *
 */
public enum CriticalStatus {

    CRITICAL_SUCCESS {
        @Override
        public boolean isSuccess(HasIndicatorOfSuccess result) {
            return true;
        }
    },

    CRITICAL_MISS {
        @Override
        public boolean isSuccess(HasIndicatorOfSuccess result) {
            return false;
        }
    },

    NORMAL {
        @Override
        public boolean isSuccess(HasIndicatorOfSuccess result) {
            return result.succeededByDiceSum();
        }
    };

    public abstract boolean isSuccess(HasIndicatorOfSuccess result);
}
