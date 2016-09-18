package me.mmnoda.rpg.domain.model.dice;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2015 - 2016 Márcio Noda
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

import me.mmnoda.rpg.domain.model.rollable.dice_representation.SingleRollable;

import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class Dice implements SingleRollable, Formattable, Comparable<Dice> {

    private final NumberOfFaces numberOfFaces;

    private Dice(NumberOfFaces numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public static Dice of(NumberOfFaces numberOfFaces) {
        return new Dice(numberOfFaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfFaces);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Dice) {
            final Dice other = (Dice) obj;
            return Objects.equals(this.numberOfFaces, other.numberOfFaces);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("numberOfFaces", numberOfFaces)
                .toString();
    }

    @Override
    public NumberOfFaces getNumberOfFaces() {
        return numberOfFaces;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s", numberOfFaces);
    }

    @Override
    public int compareTo(Dice o) {
        return numberOfFaces.compareTo(o.numberOfFaces);
    }
}
