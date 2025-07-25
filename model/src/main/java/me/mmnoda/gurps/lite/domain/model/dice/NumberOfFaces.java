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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.mmnoda.gurps.lite.infrastructure.converter.json.NumberOfFacesJsonDeserializer;
import me.mmnoda.gurps.lite.infrastructure.converter.json.NumberOfFacesJsonSerializer;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
@EqualsAndHashCode(of = "faces")
@ToString(of = "faces")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonSerialize(using = NumberOfFacesJsonSerializer.class)
@JsonDeserialize(using = NumberOfFacesJsonDeserializer.class)
public class NumberOfFaces implements Serializable, Formattable, Comparable<NumberOfFaces> {

    public static final NumberOfFaces _6 = of(BigInteger.valueOf(6));
    public static final NumberOfFaces _100 = of(BigInteger.valueOf(100));

    private final BigInteger faces;

    public static NumberOfFaces of(BigInteger faces) {
        return new NumberOfFaces(faces);
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
