package me.mmnoda.rpg.domain.model.dice;

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

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DiceAdjustmentTest {

    private DiceAdjustment diceAdjustment;

    private String formatted;

    @Before
    public void setUp() {
        diceAdjustment = DiceAdjustment.ZERO;
    }

    @Test
    public void should_format_to_zero_value() {
        diceAdjustment = DiceAdjustment.ZERO;
        format();
        assertFormattedIsEqualTo("");
    }

    @Test
    public void should_format_to_positive_value() {
        diceAdjustment = DiceAdjustment.of(1);
        format();
        assertFormattedIsEqualTo("+1");
    }

    @Test
    public void should_format_to_negative_value() {
        diceAdjustment = DiceAdjustment.of(-5);
        format();
        assertFormattedIsEqualTo("-5");
    }

    private void assertFormattedIsEqualTo(String expected) {
        assertThat(formatted)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void format() {
        formatted = String.format("%s", diceAdjustment);
    }

}