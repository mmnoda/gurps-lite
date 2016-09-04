package me.mmnoda.rpg.domain.model.action;

import me.mmnoda.rpg.domain.model.action.builder.ActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.builder.DefaultActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.action.critical.CriticalStatus;
import me.mmnoda.rpg.domain.model.action.result.ActionRollResult;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

/**
 *
 */
public class DefaultActionRoller implements ActionRoller {

    private final DiceRepresentation diceRepresentation;
    private final CriticalDetermination criticalDetermination;

    private DefaultActionRoller(ActionRollerBuilder builder) {
        this.diceRepresentation = builder.getRollables();
        this.criticalDetermination = builder.getCriticalDetermination();
    }

    public static DefaultActionRoller of(ActionRollerBuilder builder) {
        return new DefaultActionRoller(builder);
    }

    public static DefaultActionRoller OfDefault() {
        return new DefaultActionRoller(DefaultActionRollerBuilder.INSTANCE);
    }

    @Override
    public ActionRollResult roll(EffectiveValue effectiveValue) {
        final RollResultSum resultSum = roll3D();
        final DifferenceOfRoll differenceOfRoll = resultSum.calculateDifference(effectiveValue);
        final CriticalStatus criticalStatus = determineCriticalStatus(effectiveValue, resultSum, differenceOfRoll);

        return ActionRollResult.builder()
                .withCriticalStatus(criticalStatus)
                .withDifferenceOfRoll(differenceOfRoll)
                .withEffectiveValue(effectiveValue)
                .withRollResultSum(resultSum)
                .build();
    }

    private CriticalStatus determineCriticalStatus(EffectiveValue effectiveValue, RollResultSum resultSum, DifferenceOfRoll differenceOfRoll) {
        return criticalDetermination.determine(effectiveValue, resultSum, differenceOfRoll);
    }

    private RollResultSum roll3D() {
        return diceRepresentation.roll();
    }
}
