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
import me.mmnoda.rpg.domain.model.action.EffectiveValue;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDeterminationFactory;
import me.mmnoda.rpg.domain.model.action.result.ActionRollResult;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class ManualInputActionRoller3DFactoryTest {

    private final ManualInputActionRoller3DFactory factory = ManualInputActionRoller3DFactory.INSTANCE;

    private final CriticalDeterminationFactory criticalDeterminationFactory = CriticalDeterminationFactory.INSTANCE;

    private ActionRoller actionRoller;
    private ActionRollResult actionRollResult;

    @Test
    public void should_build_with_default_critical_factory() {
        actionRoller = factory.build(criticalDeterminationFactory.buildDefault(), SingleRollResult.of(1));
        assertActionRollerIsNotNull();
        roll();
        assertActionRollResultIsSucceeded();
    }

    @Test
    public void should_build_with_only_natural_critical_factory() {
        actionRoller = factory.build(criticalDeterminationFactory.buildOnlyNatural(), SingleRollResult.of(3), SingleRollResult.of(2), SingleRollResult.of(5));
        assertActionRollerIsNotNull();
        roll();
        assertActionRollResultIsSucceeded();
    }

    private void assertActionRollResultIsSucceeded() {
        assertThat(actionRollResult)
                .isNotNull()
                .extracting(ActionRollResult::isSuccess)
                .containsExactly(true);
    }

    private void roll() {
        actionRollResult = actionRoller.roll(EffectiveValue.TEN);
    }

    private void assertActionRollerIsNotNull() {
        assertThat(actionRoller)
                .isNotNull();
    }

}