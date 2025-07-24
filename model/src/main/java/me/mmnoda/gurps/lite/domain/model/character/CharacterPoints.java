package me.mmnoda.gurps.lite.domain.model.character;

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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.character.attribute.AttributeLevel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
@EqualsAndHashCode(of = "points")
@ToString(of = "points")
public final class CharacterPoints implements Serializable, Comparable<CharacterPoints>, Formattable {

    public static final CharacterPoints ZERO = of(0);

    public static final CharacterPoints FIVE = of(5);

    public static final CharacterPoints TEN = of(10);

    public static final CharacterPoints TWENTY = of(20);

    public static final CharacterPoints TWENTY_FIVE = of(25);

    private final BigInteger points;

    private CharacterPoints(BigInteger points) {
        this.points = points;
    }

    public static CharacterPoints of(BigInteger points) {
        return new CharacterPoints(points);
    }

    public static CharacterPoints of(long points) {
        return new CharacterPoints(BigInteger.valueOf(points));
    }

    public CharacterPoints calculate(final AttributeLevel attributeLevel) {
        return of(points.multiply(attributeLevel.getLevelBought()));
    }

    @Override
    public int compareTo(CharacterPoints o) {
        return points.compareTo(o.points);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", points);
    }
}
