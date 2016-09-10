package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Objects;
import java.util.SortedSet;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class LowerValueOfNResultDiceRepresentation extends AbstractOrderedRollResultDiceRepresentation {

    private LowerValueOfNResultDiceRepresentation(DiceRepresentation decorated, int numberOfRolls) {
        super(decorated, numberOfRolls);
    }

    public static LowerValueOfNResultDiceRepresentation of(DiceRepresentation decorated, int numberOfRolls) {
        return new LowerValueOfNResultDiceRepresentation(decorated, numberOfRolls);
    }

    @Override
    protected RollResultSum getSelectedRoll(SortedSet<RollResultSum> results) {
        return results.first();
    }

    @Override
    public int hashCode() {
        return Objects.hash(decorated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof LowerValueOfNResultDiceRepresentation){
            final LowerValueOfNResultDiceRepresentation other = (LowerValueOfNResultDiceRepresentation) o;
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
