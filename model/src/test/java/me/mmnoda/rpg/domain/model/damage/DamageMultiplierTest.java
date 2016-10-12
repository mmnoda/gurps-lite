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

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DamageMultiplierTest {

    private DamageMultiplier multiplier;

    private BigInteger result;

    @Test
    public void should_not_multiply_none_type() {
        multiplier = DamageMultiplier.NONE;
        multiply();
        assertResultIsEqualsTo(BigInteger.valueOf(10));
    }

    @Test
    public void should_multiply_by_0_dot_5() {
        multiplier = DamageMultiplier.ZERO_DOT_FIVE;
        multiply();
        assertResultIsEqualsTo(BigInteger.valueOf(5));
    }

    @Test
    public void should_multiply_by_one_dot_5() {
        multiplier = DamageMultiplier.ONE_DOT_FIVE;
        multiply();
        assertResultIsEqualsTo(BigInteger.valueOf(15));
    }

    @Test
    public void should_multiply_by_2() {
        multiplier = DamageMultiplier.DOUBLE;
        multiply();
        assertResultIsEqualsTo(BigInteger.valueOf(20));
    }

    private void assertResultIsEqualsTo(BigInteger expected) {
        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void multiply() {
        result = multiplier.multiply(BigInteger.valueOf(10));
    }
}