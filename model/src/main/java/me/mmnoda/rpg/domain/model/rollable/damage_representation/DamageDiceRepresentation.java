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

import me.mmnoda.rpg.domain.model.rollable.damage_representation.result.RollDamageResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;

/**
 *
 */
public interface DamageDiceRepresentation {

    RollDamageResult roll();

    RollDamageResult roll(final DiceRepresentation replacedDiceRepresentation);
}
