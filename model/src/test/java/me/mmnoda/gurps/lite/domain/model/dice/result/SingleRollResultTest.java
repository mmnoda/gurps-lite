package me.mmnoda.gurps.lite.domain.model.dice.result;

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

import java.math.BigInteger;

import me.mmnoda.gurps.lite.domain.model.dice.NumberOfFaces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 *
 */
public class SingleRollResultTest {

    private SingleRollResult singleRollResult;

    @Before
    public void setUp() {
        singleRollResult = SingleRollResult.ONE;
    }

    @Test
    public void should_accept_max_value_of_6_faces() {
        singleRollResult = SingleRollResult.of(6);
        assertSingleRollResultIsEqualTo(BigInteger.valueOf(6));
    }

    @Test
    public void should_accept_max_value_of_100_faces() {
        singleRollResult = SingleRollResult.of(NumberOfFaces._100, 100);
        assertSingleRollResultIsEqualTo(BigInteger.valueOf(100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_zero() {
        singleRollResult = SingleRollResult.of(0);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_negative_value() {
        singleRollResult = SingleRollResult.of(-1);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_value_greater_than_number_of_6_faces() {
        singleRollResult = SingleRollResult.of(7);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_value_greater_than_number_of_100_faces() {
        singleRollResult = SingleRollResult.of(NumberOfFaces._100, 101);
        fail();
    }

    private void assertSingleRollResultIsEqualTo(BigInteger expectedValue) {
        assertThat(singleRollResult)
                .isNotNull()
                .extracting(SingleRollResult::toBigInteger)
                .contains(expectedValue);
    }

}