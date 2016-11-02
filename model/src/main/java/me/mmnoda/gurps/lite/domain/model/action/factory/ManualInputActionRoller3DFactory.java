package me.mmnoda.gurps.lite.domain.model.action.factory;

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

import me.mmnoda.gurps.lite.domain.model.action.ActionRoller;
import me.mmnoda.gurps.lite.domain.model.action.DefaultActionRoller;
import me.mmnoda.gurps.lite.domain.model.action.critical.determination.CriticalDetermination;
import me.mmnoda.gurps.lite.domain.model.dice.result.SingleRollResult;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.custom.ArbitraryValuesDiceRepresentation;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.factory.DefaultRollablesFactory;

/**
 *
 */
public enum ManualInputActionRoller3DFactory {

    INSTANCE;

    public ActionRoller build(final CriticalDetermination criticalDetermination, SingleRollResult first, SingleRollResult... expectedResults) {
        return DefaultActionRoller.of(CustomActionRollerBuilder.of(() -> ArbitraryValuesDiceRepresentation.of(DefaultRollablesFactory.INSTANCE.build3D6(), first, expectedResults),
                criticalDetermination));
    }
}
