package me.mmnoda.gurps.lite.domain.model.character.attribute;

/*
 * #%L
 * gurps-lite-model
 * %%
 * Copyright (C) 2016 Márcio Noda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.common.collect.Sets;
import lombok.*;
import me.mmnoda.gurps.lite.domain.model.character.CharacterPoints;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Optional;
import java.util.Set;

/**
 *
 */
@EqualsAndHashCode(of = {"costPerLevel", "level"})
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttributeData implements Serializable, Formattable, Comparable<AttributeData> {

    private CharacterPoints costPerLevel;

    private AttributeLevel levelBase;

    private AttributeAbbreviate attribute;

    @Getter
    private AttributeLevel level;

    private AttributeLevel levelBought = AttributeLevel.ZERO;

    @Getter
    private CharacterPoints overallCost = CharacterPoints.ZERO;

    private final Set<AttributeObserver> observers = Sets.newHashSet();

    private AttributeData(final CharacterPoints costPerLevel, final AttributeLevel levelBase) {
        this(costPerLevel, levelBase, null);
    }

    private AttributeData(final CharacterPoints costPerLevel, final AttributeLevel levelBase,
                          final AttributeAbbreviate attribute) {
        this.costPerLevel = costPerLevel;
        this.levelBase = levelBase;
        this.level = levelBase;
        this.attribute = attribute;
    }

    public static AttributeData of(final CharacterPoints costPerLevel, final AttributeLevel levelBase,
                                   final AttributeAbbreviate attribute) {
        return new AttributeData(costPerLevel, levelBase, attribute);
    }

    public static AttributeData of(final CharacterPoints costPerLevel, final AttributeLevel levelBase) {
        return new AttributeData(costPerLevel, levelBase);
    }

    public final CharacterPoints addLevel(final AttributeLevel addedLevel) {
        levelBought = levelBought.add(addedLevel);
        recalculate();
        return overallCost;
    }

    public final void updateLevelBase(final AttributeLevel newLevel) {
        this.levelBase = newLevel;
        recalculate();
    }

    public final CharacterPoints setLevel(final AttributeLevel newLevel) {
        levelBought = newLevel.subtract(levelBase);
        recalculate();
        return overallCost;
    }

    private void recalculate() {
        overallCost = costPerLevel.calculate(levelBought);
        level = levelBase.add(levelBought);
        Optional.ofNullable(attribute)
                .ifPresent(this::notifyObservers);
    }

    private void notifyObservers(final AttributeAbbreviate attribute) {
        for (final AttributeObserver observer : observers) {
            observer.update(this, attribute);
        }
    }

    public final boolean addObserver(final AttributeObserver observer) {
        return observers.add(observer);
    }

    public AttributeLevel getLevelTruncated() {
        return level.dropFraction();
    }

    @Override
    public void formatTo(final Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", level);
    }

    @Override
    public int compareTo(final AttributeData o) {
        return level.compareTo(o.level);
    }
}