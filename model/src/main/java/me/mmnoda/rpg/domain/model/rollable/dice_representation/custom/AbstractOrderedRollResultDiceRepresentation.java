package me.mmnoda.rpg.domain.model.rollable.dice_representation.custom;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.result.RollResultSum;

import java.util.SortedSet;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 */
abstract class AbstractOrderedRollResultDiceRepresentation extends AbstractDiceRepresentationDecorator  {

    private final int numberOfRolls;

    protected AbstractOrderedRollResultDiceRepresentation(DiceRepresentation decorated, int numberOfRolls) {
        super(decorated);
        checkArgument(numberOfRolls > 0);
        this.numberOfRolls = numberOfRolls;
    }

    @Override
    public RollResultSum roll() {
        final SortedSet<RollResultSum> rolls = Sets.newTreeSet();

        for (int rollNumber = 1; rollNumber <= numberOfRolls; rollNumber++) {
            rolls.add(decorated.roll());
        }

        return getSelectedRoll(ImmutableSortedSet.copyOf(rolls));
    }

    protected abstract RollResultSum getSelectedRoll(SortedSet<RollResultSum> results);

}
