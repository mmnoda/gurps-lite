package me.mmnoda.rpg.model.rollable;

import com.google.common.base.Objects;
import me.mmnoda.rpg.model.dice.DiceAdjustment;
import me.mmnoda.rpg.model.dice.component.NumberOfDices;
import me.mmnoda.rpg.model.dice.component.NumberOfFaces;
import me.mmnoda.rpg.model.dice.result.RollResultSum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class DefaultDiceRepresentation implements DiceRepresentation {

    private final NumberOfDices numberOfDices;
    private final SingleRollable rollable;
    private final DiceAdjustment adjustment;

    private DefaultDiceRepresentation(Builder builder) {
        this.numberOfDices = builder.numberOfDices;
        this.rollable = builder.rollable;
        this.adjustment = builder.adjustment;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberOfDices, rollable, adjustment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DiceRepresentation) {
            final DefaultDiceRepresentation other = (DefaultDiceRepresentation) obj;
            return Objects.equal(this.numberOfDices, other.numberOfDices) && Objects.equal(this.rollable, other.rollable)
                    && Objects.equal(this.adjustment, other.adjustment);
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder().append(numberOfDices).append('D');

        if (adjustment.hasAValue()) {
            sb.append(adjustment);
        }

        return sb.toString();
    }

    @Override
    public RollResultSum roll() {
        RollResultSum.Builder builder = RollResultSum.builder().withAdjustment(adjustment);

        for (NumberOfDices numberOfDice : numberOfDices) {
            builder.add(numberOfDice, rollable.roll());
        }
        return builder.build();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return rollable.getNumberOfFaces();
    }

    @Override
    public DiceAdjustment getDiceAdjustment() {
        return adjustment;
    }

    public static class Builder {
        private NumberOfDices numberOfDices;
        private SingleRollable rollable;
        private DiceAdjustment adjustment = DiceAdjustment.ZERO;

        private Builder() {
        }

        public Builder withAdjustment(DiceAdjustment adjustment) {
            checkNotNull(adjustment, "adjustment is null");
            this.adjustment = adjustment;
            return this;
        }

        public Builder withRollable(SingleRollable rollable) {
            this.rollable = rollable;
            return this;
        }

        public Builder withNumberOfDices(NumberOfDices numberOfDices) {
            this.numberOfDices = numberOfDices;
            return this;
        }

        public DiceRepresentation build() {
            return new DefaultDiceRepresentation(this);
        }

    }
}
