package me.mmnoda.gurps.lite.domain.model.dice.factory;

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

import me.mmnoda.gurps.lite.domain.model.dice.Dice;
import me.mmnoda.gurps.lite.domain.model.dice.NumberOfFaces;

import static me.mmnoda.gurps.lite.domain.model.dice.Dice.of;

/**
 *
 */
public enum Dices implements DicesFactory {

    D6(of(NumberOfFaces._6)),

    D100(of(NumberOfFaces._100));

    private final Dice dice;

    Dices(Dice dice) {
        this.dice = dice;
    }

    public final Dice getInstance() {
        return dice;
    }
}
