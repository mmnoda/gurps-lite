package me.mmnoda.rpg.model.action;

import me.mmnoda.rpg.model.action.builder.ActionRollerBuilder;
import me.mmnoda.rpg.model.action.builder.DefaultActionRollerBuilder;
import me.mmnoda.rpg.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.model.action.critical.CriticalStatus;
import me.mmnoda.rpg.model.action.result.ActionRollResult;
import me.mmnoda.rpg.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.model.dice.result.RollResultSum;
import me.mmnoda.rpg.model.rollable.DiceRepresentation;

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

    public static DefaultActionRoller newDefaultActionRoller(ActionRollerBuilder builder) {
        return new DefaultActionRoller(builder);
    }

    public static DefaultActionRoller newDefaultActionRoller() {
        return new DefaultActionRoller(DefaultActionRollerBuilder.INSTANCE);
    }

    @Override
    public ActionRollResult roll(EffectiveValue effectiveValue) {
        RollResultSum resultSum = roll3D();
        DifferenceOfRoll differenceOfRoll = resultSum.calculateDifference(effectiveValue);
        CriticalStatus criticalStatus = determineCriticalStatus(effectiveValue, resultSum, differenceOfRoll);

        return ActionRollResult.builder().withCriticalStatus(criticalStatus).withDifferenceOfRoll(differenceOfRoll).
                withEffectiveValue(effectiveValue).withRollResultSum(resultSum).
                build();
    }

    private CriticalStatus determineCriticalStatus(EffectiveValue effectiveValue, RollResultSum resultSum, DifferenceOfRoll differenceOfRoll) {
        return criticalDetermination.determine(effectiveValue, resultSum.getSum(), differenceOfRoll);
    }

    private RollResultSum roll3D() {
        return diceRepresentation.roll();
    }
}
