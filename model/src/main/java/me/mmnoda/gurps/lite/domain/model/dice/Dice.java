package me.mmnoda.gurps.lite.domain.model.dice;

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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mmnoda.gurps.lite.domain.model.rollable.dice_representation.SingleRollable;

import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
@EqualsAndHashCode(of = "numberOfFaces")
@ToString(of = "numberOfFaces")
public class Dice implements SingleRollable, Formattable, Comparable<Dice> {

    @Getter
    private final NumberOfFaces numberOfFaces;

    private Dice(NumberOfFaces numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public static Dice of(NumberOfFaces numberOfFaces) {
        return new Dice(numberOfFaces);
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
