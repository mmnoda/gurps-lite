package me.mmnoda.rpg.domain.model.dice.result;

import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

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