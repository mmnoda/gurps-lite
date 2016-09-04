package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import me.mmnoda.rpg.domain.model.dice.NumberOfDices;
import me.mmnoda.rpg.domain.model.dice.NumberOfFaces;
import me.mmnoda.rpg.domain.model.dice.result.SingleRollResult;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class ArbitraryValuesDiceRepresentation extends AbstractDiceRepresentationDecorator {

    private final List<SingleRollResult> arbitraryRollResults;
    private final SingleRollResult first;

    private ArbitraryValuesDiceRepresentation(DiceRepresentation decorated, SingleRollResult first, SingleRollResult... expectedResults) {
        super(decorated);
        checkNotNull(first);
        this.first = first;

        arbitraryRollResults = ImmutableList.copyOf(expectedResults);
    }

    public static ArbitraryValuesDiceRepresentation of(DiceRepresentation decorated, SingleRollResult first, SingleRollResult... expectedResults) {
        return new ArbitraryValuesDiceRepresentation(decorated, first, expectedResults);
    }

    @Override
    protected void customizeRoll(RollResultSum.Builder builder, NumberOfDices numberOfDices, NumberOfFaces numberOfFaces) {
        final Deque<SingleRollResult> deque = Lists.newLinkedList(arbitraryRollResults);
        deque.addFirst(first);

        for (NumberOfDices numberOfDice : numberOfDices) {
            SingleRollResult expectResult = Optional.ofNullable(deque.pollFirst()).orElse(first);
            builder.add(numberOfDice, expectResult);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(decorated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof ArbitraryValuesDiceRepresentation){
            final ArbitraryValuesDiceRepresentation other = (ArbitraryValuesDiceRepresentation) o;
            return Objects.equals(this.decorated, other.decorated);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("decorated", decorated)
                .toString();
    }
}
