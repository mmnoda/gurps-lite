package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
abstract class AbstractDiceRepresentationOverrideRollDecorator extends AbstractDiceRepresentationDecorator {

    AbstractDiceRepresentationOverrideRollDecorator(DiceRepresentation decorated) {
        super(decorated);
        checkNotNull(decorated);
    }

    public final RollResultSum roll() {
        final RollResultSum.Builder builder = getBuilder();
        customizeRoll(builder, getNumberOfDices(), getNumberOfFaces());
        return builder.build();
    }

    private RollResultSum.Builder getBuilder() {
        return RollResultSum
                .builder()
                .withAdjustment(this.getDiceAdjustment());
    }

    protected abstract void customizeRoll(RollResultSum.Builder builder, NumberOfDices numberOfDices, NumberOfFaces numberOfFaces);

}
