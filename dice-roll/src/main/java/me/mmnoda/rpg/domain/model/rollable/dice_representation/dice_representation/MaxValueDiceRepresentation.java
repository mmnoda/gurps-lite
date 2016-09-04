package me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
public final class MaxValueDiceRepresentation implements DiceRepresentation, Formattable {

    private final DiceRepresentation decorated;

    public MaxValueDiceRepresentation(DiceRepresentation decorated) {
        this.decorated = decorated;
    }

    @Override
    public RollResultSum roll() {

        final RollResultSum.Builder builder = RollResultSum.builder()
                .withAdjustment(this.getDiceAdjustment());

        final NumberOfDices numberOfDices = getNumberOfDices();

        for (NumberOfDices numberOfDice : numberOfDices) {
            builder.add(numberOfDice, SingleRollResult.of(numberOfDices.maxDiceSum(getNumberOfFaces()).toBigInteger()));
        }

        return builder.build();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return decorated.getNumberOfFaces();
    }

    @Override
    public DiceAdjustment getDiceAdjustment() {
        return decorated.getDiceAdjustment();
    }

    @Override
    public NumberOfDices getNumberOfDices() {
        return decorated.getNumberOfDices();
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", decorated);
    }
}
