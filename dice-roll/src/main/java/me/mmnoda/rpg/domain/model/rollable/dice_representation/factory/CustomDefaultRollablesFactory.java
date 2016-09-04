package me.mmnoda.rpg.domain.model.rollable.dice_representation.factory;

import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.factory.DicesFactory;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DefaultDiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;
import static me.mmnoda.rpg.domain.model.dice.NumberOfDices.THREE;

/**
 *
 */
public final class CustomDefaultRollablesFactory implements RollablesFactory {

    private final DicesFactory dicesFactory;

    private final NumberOfDices numberOfDices;

    private CustomDefaultRollablesFactory(DicesFactory dicesFactory, NumberOfDices numberOfDices) {
        checkNotNull(dicesFactory);
        checkNotNull(numberOfDices);
        this.numberOfDices = numberOfDices;
        this.dicesFactory = dicesFactory;
    }

    public static CustomDefaultRollablesFactory of(DicesFactory dicesFactory, NumberOfDices numberOfDices) {
        return new CustomDefaultRollablesFactory(dicesFactory, numberOfDices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dicesFactory, numberOfDices);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof CustomDefaultRollablesFactory) {
            final CustomDefaultRollablesFactory other = (CustomDefaultRollablesFactory) obj;
            return Objects.equals(this.dicesFactory, other.dicesFactory)
                    && Objects.equals(this.numberOfDices, other.numberOfDices);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("dicesFactory", dicesFactory)
                .add("numberOfDices", numberOfDices)
                .toString();
    }

    @Override
    public DiceRepresentation build3D6() {
        return DefaultDiceRepresentation
                .of(dicesFactory.getInstance(), THREE);
    }
}
