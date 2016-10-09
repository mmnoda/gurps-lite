package me.mmnoda.rpg.domain.model.rollable.damage_representation;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2016 Márcio Noda
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

import me.mmnoda.rpg.domain.model.damage.DamageType;
import me.mmnoda.rpg.domain.model.rollable.damage_representation.result.RollDamageResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class DefaultDamageDiceRepresentation implements DamageDiceRepresentation, Formattable {

    private final DiceRepresentation diceRepresentation;

    private final DamageType damageType;

    private DefaultDamageDiceRepresentation(DiceRepresentation diceRepresentation, DamageType damageType) {
        this.diceRepresentation = diceRepresentation;
        this.damageType = damageType;
    }

    public static DefaultDamageDiceRepresentation of(DiceRepresentation diceRepresentation, DamageType damageType) {
        return new DefaultDamageDiceRepresentation(diceRepresentation, damageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diceRepresentation, damageType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof DefaultDamageDiceRepresentation) {
            final DefaultDamageDiceRepresentation other = (DefaultDamageDiceRepresentation) obj;
            return Objects.equals(this.diceRepresentation, other.diceRepresentation)
                    && Objects.equals(this.damageType, other.damageType);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("diceRepresentation", diceRepresentation)
                .add("damageType", damageType)
                .toString();
    }

    @Override
    public RollDamageResult roll() {
        return RollDamageResult.of(diceRepresentation.roll(), damageType);
    }

    @Override
    public RollDamageResult roll(final DiceRepresentation replacedDiceRepresentation) {
        return RollDamageResult.of(replacedDiceRepresentation.roll(), damageType);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s %s", diceRepresentation, damageType);
    }
}
