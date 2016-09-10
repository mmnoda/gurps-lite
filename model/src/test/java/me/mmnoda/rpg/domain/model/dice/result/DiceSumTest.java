package me.mmnoda.rpg.domain.model.dice.result;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DiceSumTest {

    private DiceSum diceSum;

    private String formatted;

    private final Set<DiceSum> noNaturalCritical;

    {
        final Builder<DiceSum> builder = ImmutableSet.builder();

        for (int count = 5; count <= 17; count ++) {
            builder.add(DiceSum.of(count));
        }

        noNaturalCritical = builder.build();
    }

    @Before
    public void setUp() throws Exception {
        diceSum = DiceSum.ZERO;
    }

    @Test
    public void should_format_to_zero_value() {
        diceSum = DiceSum.ZERO;
        format();
        assertFormattedIsEquals("0");
    }

    @Test
    public void should_format_to_123_value() {
        diceSum = DiceSum.of(123);
        format();
        assertFormattedIsEquals("123");
    }

    @Test
    public void should_identity_natural_critical_success_of_3() {
        diceSum = DiceSum.of(3);
        assertNaturalCriticalSuccessIsTrue();
        assertNaturalCriticalMissIsFalse();
    }

    @Test
    public void should_identity_natural_critical_success_of_4() {
        diceSum = DiceSum.of(4);
        assertNaturalCriticalSuccessIsTrue();
        assertNaturalCriticalMissIsFalse();
    }

    @Test
    public void should_identity_no_natural_critical_success() {
        for (DiceSum noNaturalCritical : this.noNaturalCritical) {
            assertThat(noNaturalCritical.isNaturalCriticalSuccess())
                    .isFalse();
        }
    }

    @Test
    public void should_identity_natural_critical_miss_of_18() {
        diceSum = DiceSum.of(18);
        assertNaturalCriticalMissIsTrue();
        assertNaturalCriticalSuccessIsFalse();
    }

    @Test
    public void should_identity_no_natural_critical_miss() {
        for (DiceSum noNaturalCritical : this.noNaturalCritical) {
            assertThat(noNaturalCritical.isNaturalCriticalMiss())
                    .isFalse();
        }
    }

    private void assertNaturalCriticalMissIsTrue() {
        assertThat(diceSum.isNaturalCriticalMiss())
                .isTrue();
    }

    private void assertNaturalCriticalMissIsFalse() {
        assertThat(diceSum.isNaturalCriticalMiss())
                .isFalse();
    }

    private void assertNaturalCriticalSuccessIsTrue() {
        assertThat(diceSum.isNaturalCriticalSuccess())
                .isTrue();
    }

    private void assertNaturalCriticalSuccessIsFalse() {
        assertThat(diceSum.isNaturalCriticalSuccess())
                .isFalse();
    }

    private void assertFormattedIsEquals(final String expected) {
        assertThat(formatted)
                .isNotNull()
                .isEqualTo(expected);
    }

    private void format() {
        formatted = String.format("%s", diceSum);
    }
}