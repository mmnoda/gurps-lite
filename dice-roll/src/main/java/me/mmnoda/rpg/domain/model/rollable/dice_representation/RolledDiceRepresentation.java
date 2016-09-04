package me.mmnoda.rpg.domain.model.rollable.dice_representation;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Formattable;
import java.util.Formatter;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Decorator
 */
public class RolledDiceRepresentation implements DiceRepresentation, Formattable {

    private final DiceRepresentation diceRepresentation;
    private final RollResultSum rollResultSum;

    public RolledDiceRepresentation(DiceRepresentation diceRepresentation, RollResultSum rollResultSum) {
        this.diceRepresentation = diceRepresentation;
        this.rollResultSum = rollResultSum;
    }

    @Override
    public RollResultSum roll() {
        return rollResultSum;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("diceRepresentation", diceRepresentation)
                .add("rollResultSum", rollResultSum)
                .toString();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return diceRepresentation.getNumberOfFaces();
    }

    @Override
    public DiceAdjustment getDiceAdjustment() {
        return diceRepresentation.getDiceAdjustment();
    }

    @Override
    public NumberOfDices getNumberOfDices() {
        return diceRepresentation.getNumberOfDices();
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", rollResultSum);
    }
}
