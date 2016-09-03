package me.mmnoda.rpg.domain.model.rollable;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.dice.result.RollResultSum;

/**
 *
 */
public class RolledDiceRepresentation implements DiceRepresentation {

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
        return diceRepresentation.toString();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return diceRepresentation.getNumberOfFaces();
    }

    @Override
    public DiceAdjustment getDiceAdjustment() {
        return diceRepresentation.getDiceAdjustment();
    }
}
