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

import com.google.common.base.Objects;
import me.mmnoda.rpg.domain.model.action.critical.determination.CriticalDetermination;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.factory.RollablesFactory;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
final class CustomActionRollerBuilder implements ActionRollerBuilder {

    private final RollablesFactory rollablesFactory;
    private final CriticalDetermination criticalDetermination;

    private CustomActionRollerBuilder(RollablesFactory rollablesFactory, CriticalDetermination criticalDetermination) {
        this.rollablesFactory = rollablesFactory;
        this.criticalDetermination = criticalDetermination;
    }

    public static CustomActionRollerBuilder of(RollablesFactory rollablesFactory, CriticalDetermination criticalDetermination) {
        return new CustomActionRollerBuilder(rollablesFactory, criticalDetermination);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rollablesFactory, criticalDetermination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CustomActionRollerBuilder) {
            final CustomActionRollerBuilder other = (CustomActionRollerBuilder) obj;
            return Objects.equal(this.rollablesFactory, other.rollablesFactory)
                    && Objects.equal(this.criticalDetermination, other.criticalDetermination);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).
                add("rollablesFactory", rollablesFactory).
                add("criticalDetermination", criticalDetermination).toString();
    }

    @Override
    public DiceRepresentation getRollables() {
        return rollablesFactory.build3D6();
    }

    @Override
    public CriticalDetermination getCriticalDetermination() {
        return criticalDetermination;
    }
}
