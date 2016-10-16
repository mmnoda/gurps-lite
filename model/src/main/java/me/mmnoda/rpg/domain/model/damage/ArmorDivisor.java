package me.mmnoda.rpg.domain.model.damage;

/*
 * #%L
 * model
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

import java.util.Formattable;
import java.util.Formatter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public enum ArmorDivisor implements Formattable {

    NONE("");

    private final String format;

    ArmorDivisor(final String format) {
        checkNotNull(format);
        this.format = format;
    }

    @Override
    public final void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format(format);
    }
}
