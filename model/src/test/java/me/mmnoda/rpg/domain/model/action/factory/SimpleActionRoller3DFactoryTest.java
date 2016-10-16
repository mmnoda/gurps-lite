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

import me.mmnoda.rpg.domain.model.action.ActionRoller;
import me.mmnoda.rpg.domain.model.action.critical.determination.CriticalDetermination;
import me.mmnoda.rpg.domain.model.action.critical.determination.CriticalDeterminationFactory;
import org.junit.Test;

import static me.mmnoda.rpg.domain.model.action.factory.SimpleActionRoller3DFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class SimpleActionRoller3DFactoryTest {

    private final CriticalDeterminationFactory determinationFactory = CriticalDeterminationFactory.INSTANCE;

    private SimpleActionRoller3DFactory factory;

    private ActionRoller actionRoller;

    @Test
    public void should_build_default_action_roller() {
        factory = DEFAULT;
        build(determinationFactory.buildOnlyNatural());
        assertActionRollerIsNotNull();
    }

    @Test
    public void should_build_max_value_action_roller() {
        factory = MAX_VALUE_ROLL;
        build(determinationFactory.buildDefault());
        assertActionRollerIsNotNull();
    }

    @Test
    public void should_build_min_value_action_roller() {
        factory = MIN_VALUE_ROLL;
        build(determinationFactory.buildOnlyNatural());
        assertActionRollerIsNotNull();
    }

    @Test
    public void should_build_avg_value_action_roller() {
        factory = AVG_VALUE_ROLL;
        build(determinationFactory.buildOnlyNatural());
        assertActionRollerIsNotNull();
    }

    @Test
    public void should_build_best_of_3_rolls_action_roller() {
        factory = BEST_OF_3_ROLLS;
        build(determinationFactory.buildDefault());
        assertActionRollerIsNotNull();
    }

    @Test
    public void should_build_worst_of_3_rolls_action_roller() {
        factory = WORST_OF_3_ROLLS;
        build(determinationFactory.buildOnlyNatural());
        assertActionRollerIsNotNull();
    }

    private void assertActionRollerIsNotNull() {
        assertThat(actionRoller)
                .isNotNull();
    }

    private void build(CriticalDetermination criticalDetermination) {
        actionRoller = factory.build(criticalDetermination);
    }
}