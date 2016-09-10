package me.mmnoda.rpg.domain.model.action.factory;

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