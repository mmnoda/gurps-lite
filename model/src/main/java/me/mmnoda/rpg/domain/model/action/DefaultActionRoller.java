package me.mmnoda.rpg.domain.model.action;

import me.mmnoda.rpg.domain.model.action.builder.ActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.builder.DefaultActionRollerBuilder;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.action.critical.CriticalStatus;
import me.mmnoda.rpg.domain.model.action.result.ActionRollResult;
import me.mmnoda.rpg.domain.model.action.result.DifferenceOfRoll;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

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

    public static DefaultActionRoller ofDefault() {
        return new DefaultActionRoller(DefaultActionRollerBuilder.INSTANCE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diceRepresentation, criticalDetermination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof DefaultActionRoller) {
            final DefaultActionRoller other = (DefaultActionRoller) obj;
            return Objects.equals(this.diceRepresentation, other.diceRepresentation)
                    && Objects.equals(this.criticalDetermination, other.criticalDetermination);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("diceRepresentation", diceRepresentation)
                .add("criticalDetermination", criticalDetermination)
                .toString();
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
