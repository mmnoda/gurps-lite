package me.mmnoda.rpg.domain.model.action.critical;

/**
 *
 */
public enum CriticalDeterminationFactory {

    INSTANCE;

    public final CriticalDetermination buildDefault() {
        return DefaultCriticalDetermination.TO_FIND_OUT;
    }

    public final CriticalDetermination buildOnlyNatural() {
        return OnlyNaturalCriticalDetermination.TO_FIND_OUT;
    }
}
