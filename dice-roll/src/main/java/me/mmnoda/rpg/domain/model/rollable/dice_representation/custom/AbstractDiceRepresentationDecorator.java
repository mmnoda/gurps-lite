package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import me.mmnoda.rpg.domain.model.dice.DiceAdjustment;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Formattable;
import java.util.Formatter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public abstract class AbstractDiceRepresentationDecorator implements DiceRepresentation, Formattable {

    protected final DiceRepresentation decorated;

    AbstractDiceRepresentationDecorator(DiceRepresentation decorated) {
        checkNotNull(decorated);
        this.decorated = decorated;
    }

    public final RollResultSum roll() {

        final RollResultSum.Builder builder = getBuilder();

        final NumberOfDices numberOfDices = getNumberOfDices();
        final NumberOfFaces numberOfFaces = getNumberOfFaces();

        customizeRoll(builder, numberOfDices, numberOfFaces);

        return builder.build();
    }

    private RollResultSum.Builder getBuilder() {
        return RollResultSum
                .builder()
                .withAdjustment(this.getDiceAdjustment());
    }

    protected abstract void customizeRoll(RollResultSum.Builder builder, NumberOfDices numberOfDices, NumberOfFaces numberOfFaces);

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
