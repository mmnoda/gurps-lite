package me.mmnoda.rpg.model.dice;

import com.google.common.base.Objects;
import me.mmnoda.rpg.model.dice.component.NumberOfFaces;
import me.mmnoda.rpg.model.rollable.SingleRollable;

/**
 *
 */
public class Dice implements SingleRollable {

    private final NumberOfFaces numberOfFaces;

    private Dice(NumberOfFaces numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public static Dice of(NumberOfFaces numberOfFaces) {
        return new Dice(numberOfFaces);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberOfFaces);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Dice) {
            final Dice other = (Dice) obj;
            return Objects.equal(this.numberOfFaces, other.numberOfFaces);
        }
        return false;
    }

    @Override
    public String toString() {
        return numberOfFaces.toString();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return numberOfFaces;
    }
}
