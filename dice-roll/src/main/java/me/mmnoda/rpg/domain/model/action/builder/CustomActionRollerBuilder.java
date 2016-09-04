package me.mmnoda.rpg.domain.model.action.builder;

import com.google.common.base.Objects;
import me.mmnoda.rpg.domain.model.action.critical.CriticalDetermination;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.dice_representation.DiceRepresentation;
import me.mmnoda.rpg.domain.model.rollable.dice_representation.factory.RollablesFactory;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class CustomActionRollerBuilder implements ActionRollerBuilder {

    private final RollablesFactory rollablesFactory;
    private final CriticalDetermination criticalDetermination;

    private CustomActionRollerBuilder(RollablesFactory rollablesFactory, CriticalDetermination criticalDetermination) {
        this.rollablesFactory = rollablesFactory;
        this.criticalDetermination = criticalDetermination;
    }

    public static CustomActionRollerBuilder newCustomActionRollerBuilder(RollablesFactory rollablesFactory, CriticalDetermination criticalDetermination) {
        return new CustomActionRollerBuilder(rollablesFactory, criticalDetermination);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rollablesFactory, criticalDetermination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CustomActionRollerBuilder) {
            final CustomActionRollerBuilder other = (CustomActionRollerBuilder) obj;
            return Objects.equal(this.rollablesFactory, other.rollablesFactory)
                    && Objects.equal(this.criticalDetermination, other.criticalDetermination);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).
                add("rollablesFactory", rollablesFactory).
                add("criticalDetermination", criticalDetermination).toString();
    }

    @Override
    public DiceRepresentation getRollables() {
        return rollablesFactory.build3D6();
    }

    @Override
    public CriticalDetermination getCriticalDetermination() {
        return criticalDetermination;
    }
}
