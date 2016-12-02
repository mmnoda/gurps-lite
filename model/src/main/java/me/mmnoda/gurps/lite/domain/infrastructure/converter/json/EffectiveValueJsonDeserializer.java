package me.mmnoda.gurps.lite.domain.infrastructure.converter.json;

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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.mmnoda.gurps.lite.domain.model.action.EffectiveValue;

import java.io.IOException;
import java.math.BigInteger;

/**
 *
 */
public class EffectiveValueJsonDeserializer extends JsonDeserializer<EffectiveValue> {

    @Override
    public EffectiveValue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return EffectiveValue.of(jsonParser.readValueAs(BigInteger.class));
    }
}
