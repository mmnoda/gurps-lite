package me.mmnoda.rpg.domain.model.action.result;

/**
 *
 */
public interface HasIndicatorOfSuccess {

    boolean succeededByDiceSum();

    DifferenceOfRoll getDifferenceOfRoll();
}
