package me.mmnoda.rpg.domain.model.dice;

import me.mmnoda.rpg.domain.model.rollable.dice_representation.SingleRollable;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class Dice implements SingleRollable, Formattable, Comparable<Dice> {

    private final NumberOfFaces numberOfFaces;

    private Dice(NumberOfFaces numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public static Dice of(NumberOfFaces numberOfFaces) {
        return new Dice(numberOfFaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfFaces);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Dice) {
            final Dice other = (Dice) obj;
            return Objects.equals(this.numberOfFaces, other.numberOfFaces);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("numberOfFaces", numberOfFaces)
                .toString();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return numberOfFaces;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", numberOfFaces);
    }

    @Override
    public int compareTo(Dice o) {
        return numberOfFaces.compareTo(o.numberOfFaces);
    }
}
