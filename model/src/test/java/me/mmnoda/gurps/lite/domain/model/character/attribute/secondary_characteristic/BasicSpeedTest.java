package me.mmnoda.gurps.lite.domain.model.character.attribute.secondary_characteristic;

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

import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;
import me.mmnoda.gurps.lite.domain.model.character.attribute.Dexterity;
import me.mmnoda.gurps.lite.domain.model.character.attribute.Health;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicSpeedTest {

    private BasicSpeed basicSpeed;

    @Mock
    private Dexterity dexterity;

    @Mock
    private Health health;

    @Before
    public void setUp() {
        when(dexterity.getLevel()).thenReturn(AttributeLevel.of(12));
        when(health.getLevel()).thenReturn(AttributeLevel.of(8));
        basicSpeed = BasicSpeed.of(dexterity, health);
    }

    @After
    public void tearDown() {
        reset(dexterity, health);
    }

    @Test
    public void should_calculate_level_on_build() {
        final AttributeLevel basicSpeedLevel = basicSpeed.getLevel();

        assertThat(basicSpeedLevel)
                .isNotNull()
                .isEqualTo(AttributeLevel.of(5.00));
    }
}