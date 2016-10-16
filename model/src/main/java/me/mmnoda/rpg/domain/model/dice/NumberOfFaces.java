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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class NumberOfFaces implements Serializable, Formattable, Comparable<NumberOfFaces> {

    public static final NumberOfFaces _6 = of(BigInteger.valueOf(6));
    public static final NumberOfFaces _100 = of(BigInteger.valueOf(100));

    private final BigInteger faces;

    private NumberOfFaces(BigInteger faces) {
        this.faces = faces;
    }

    public static NumberOfFaces of(BigInteger faces) {
        return new NumberOfFaces(faces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faces);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof NumberOfFaces) {
            final NumberOfFaces other = (NumberOfFaces) obj;
            return Objects.equals(this.faces, other.faces);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("faces", faces)
                .toString();
    }

    public BigInteger toBigInteger() {
        return faces;
    }

    public int intValue() {
        return faces.intValue();
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(faces.toString());
    }

    @Override
    public int compareTo(NumberOfFaces o) {
        return faces.compareTo(o.faces);
    }
}
