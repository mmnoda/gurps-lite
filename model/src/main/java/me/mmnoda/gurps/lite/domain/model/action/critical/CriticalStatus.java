package me.mmnoda.gurps.lite.domain.model.action.critical;

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

import me.mmnoda.gurps.lite.domain.model.action.result.HasIndicatorOfSuccess;

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
