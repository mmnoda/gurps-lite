package me.mmnoda.gurps.lite.domain.model.character.attribute;

/*
 * #%L
 * gurps-lite-model
 * %%
 * Copyright (C) 2018 Márcio Noda
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

import me.mmnoda.gurps.lite.domain.model.character.CharacterPoints;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class AttributeDataTest {

    private AttributeData attributeData;

    @Before
    public void setUp() {
        attributeData = AttributeData.of(CharacterPoints.TEN, AttributeLevel.TEN);
    }

    @Test
    public void should_add_ten_levels() {
        attributeData.addLevel(AttributeLevel.TEN);
        assertThatLevelIsEqualsTo(AttributeLevel.of(20));
        assertThatOverallCostIsEqualTo(CharacterPoints.of(100));
    }

    @Test
    public void should_buy_negative_level() {
        attributeData.addLevel(AttributeLevel.of(-2));
        assertThatLevelIsEqualsTo(AttributeLevel.of(8));
        assertThatOverallCostIsEqualTo(CharacterPoints.of(-20));
    }

    @Test
    public void should_set_new_level_value() {
        attributeData.setLevel(AttributeLevel.of(15));
        assertThatLevelIsEqualsTo(AttributeLevel.of(15));
        assertThatOverallCostIsEqualTo(CharacterPoints.of(50));
    }

    private void assertThatOverallCostIsEqualTo(CharacterPoints of) {
        assertThat(attributeData.getOverallCost())
                .isNotNull()
                .isEqualTo(of);
    }

    private void assertThatLevelIsEqualsTo(AttributeLevel of) {
        assertThat(attributeData.getLevel())
                .isNotNull()
                .isEqualTo(of);
    }
}