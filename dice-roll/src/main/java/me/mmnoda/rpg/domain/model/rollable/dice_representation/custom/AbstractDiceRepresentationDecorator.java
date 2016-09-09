package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;

import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
abstract class AbstractDiceRepresentationDecorator implements DiceRepresentation, Formattable {

    protected final DiceRepresentation decorated;

    AbstractDiceRepresentationDecorator(DiceRepresentation decorated) {
        this.decorated = decorated;
    }

    @Override
    public final NumberOfFaces getNumberOfFaces() {
        return decorated.getNumberOfFaces();
    }

    @Override
    public final DiceAdjustment getDiceAdjustment() {
        return decorated.getDiceAdjustment();
    }

    @Override
    public final NumberOfDices getNumberOfDices() {
        return decorated.getNumberOfDices();
    }

    @Override
    public final void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", decorated);
    }
}
