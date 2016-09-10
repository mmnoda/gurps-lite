package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Objects;
import java.util.SortedSet;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class HighestValueOfNResultDiceRepresentation extends AbstractOrderedRollResultDiceRepresentation {

    private HighestValueOfNResultDiceRepresentation(DiceRepresentation decorated, int numberOfRolls) {
        super(decorated, numberOfRolls);
    }

    public static HighestValueOfNResultDiceRepresentation of(DiceRepresentation decorated, int numberOfRolls) {
        return new HighestValueOfNResultDiceRepresentation(decorated, numberOfRolls);
    }

    @Override
    protected RollResultSum getSelectedRoll(SortedSet<RollResultSum> results) {
        return results.last();
    }

    @Override
    public int hashCode() {
        return Objects.hash(decorated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof HighestValueOfNResultDiceRepresentation){
            final HighestValueOfNResultDiceRepresentation other = (HighestValueOfNResultDiceRepresentation) o;
            return Objects.equals(this.decorated, other.decorated);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("decorated", decorated)
                .toString();
    }
}
