package me.mmnoda.rpg.domain.model.damage;

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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 */
public enum DamageMultiplier {

    NONE(BigDecimal.ONE),

    ZERO_DOT_FIVE(BigDecimal.valueOf(0.5)),

    ONE_DOT_FIVE(BigDecimal.valueOf(1.5)),

    DOUBLE(BigDecimal.valueOf(2));

    private BigDecimal multiplier;

    DamageMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    Damage multiply(final BigInteger damageValue, final DamageType type) {
        return Damage.ofFinalValue(damageValue.multiply(multiplier.toBigInteger()), type);
    }

}
