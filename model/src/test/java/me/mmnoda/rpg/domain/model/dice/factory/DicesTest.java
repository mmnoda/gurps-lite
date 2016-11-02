package me.mmnoda.rpg.domain.model.dice.factory;

/*
 * #%L
 * rpg-model
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

import me.mmnoda.rpg.domain.model.dice.Dice;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DicesTest {

    private Dices factory;
    private Dice dice;

    @Test
    public void should_build_d100() {
        factory = Dices.D100;
        getInstance();
        assertDiceIsEqualsTo(Dice.of(NumberOfFaces._100));
    }

    @Test
    public void should_build_d6() {
        factory = Dices.D6;
        getInstance();
        assertDiceIsEqualsTo(Dice.of(NumberOfFaces._6));
    }

    private void assertDiceIsEqualsTo(Dice expected) {
        assertThat(this.dice)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void getInstance() {
        dice = factory.getInstance();
    }

}