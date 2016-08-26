package me.mmnoda.rpg.model.dice.custom;

import com.google.common.base.Objects;
import me.mmnoda.rpg.model.dice.component.NumberOfFaces;
import me.mmnoda.rpg.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.model.rollable.SingleRollable;

import static me.mmnoda.rpg.model.dice.result.SingleRollResult.valueOf;

/**
 * Decorator
 */
public class MaximumValueDice implements SingleRollable {

    private final SingleRollable decorated;

    private MaximumValueDice(SingleRollable decorated) {
        this.decorated = decorated;
    }

    public static MaximumValueDice newMaximumValueDice(SingleRollable decorated) {
        return new MaximumValueDice(decorated);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(decorated);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MaximumValueDice) {
            final MaximumValueDice other = (MaximumValueDice) obj;
            return Objects.equal(this.decorated, other.decorated);
        }
        return false;
    }

    @Override
    public String toString() {
        return decorated.toString();
    }

    @Override
    public SingleRollResult roll() {
        return valueOf(getNumberOfFaces());
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return decorated.getNumberOfFaces();
    }
}
