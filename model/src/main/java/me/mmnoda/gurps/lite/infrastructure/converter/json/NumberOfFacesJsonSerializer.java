package me.mmnoda.gurps.lite.infrastructure.converter.json;

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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import me.mmnoda.gurps.lite.domain.model.dice.NumberOfFaces;

/**
 *
 */
public class NumberOfFacesJsonSerializer extends JsonSerializer<NumberOfFaces> {

    @Override
    public void serialize(NumberOfFaces numberOfFaces, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(numberOfFaces.toBigInteger());
    }
}
