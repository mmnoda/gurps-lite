package me.mmnoda.rpg.model.dice.custom;

import com.google.common.base.Objects;
import me.mmnoda.rpg.model.dice.component.NumberOfFaces;
import me.mmnoda.rpg.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.model.rollable.SingleRollable;

/**
 * Decorator
 */
public class ManualInputDice implements SingleRollable {

    private final SingleRollable decorated;
    private final SingleRollResult rollResult;

    private ManualInputDice(SingleRollable decorated, SingleRollResult rollResult) {
        this.decorated = decorated;
        this.rollResult = rollResult;
    }

    public static ManualInputDice newManualInputDice(SingleRollable decorated, SingleRollResult rollResult) {
        return new ManualInputDice(decorated, rollResult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(decorated, rollResult);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ManualInputDice) {
            final ManualInputDice other = (ManualInputDice) obj;
            return Objects.equal(this.decorated, other.decorated)
                    && Objects.equal(this.rollResult, other.rollResult);
        }
        return false;
    }

    @Override
    public String toString() {
        return decorated.toString();
    }

    @Override
    public SingleRollResult roll() {
        return rollResult;
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return decorated.getNumberOfFaces();
    }
}
