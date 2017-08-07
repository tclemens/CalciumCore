/*
 * Copyright (C) 2017 Tim Clemens
 *
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
 */

package net.tclemens.calcium.engine.graphics.text;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.text.font.Font;
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class is responsible for creating and initializing text
 *
 * @author Tim Clemens
 */
public final class TextFactory {

    private TextFactory() {
    }

    /**
     * Create text
     *
     * @param position The position of the text
     * @param font The font of the text
     * @param symbols The symbols of the text
     * @param height The height of the text
     *
     * @return The new text
     *
     * @throws IllegalArgumentException If the text position, font, symbols, or height are invalid
     */
    @NonNull
    public static Text createText(@NonNull Vector3D position, @NonNull Font font, @NonNull String symbols, float height) {

        if (position == null) {

            throw new IllegalArgumentException("Unable to create text with a null position");
        }

        if (font == null) {

            throw new IllegalArgumentException("Unable to create text with a null font");
        }

        if (symbols == null) {

            throw new IllegalArgumentException("Unable to create text with a null symbol string");
        }

        if (height <= 0f) {

            throw new IllegalArgumentException("Unable to create text with a zero or negative height");
        }

        return buildText(position, font, symbols, height);
    }

    /**
     * Create text
     *
     * @param position The position of the text
     * @param font The font of the text
     * @param symbols The symbols of the text
     * @param height The height of the text
     *
     * @return The new text
     */
    static Text buildText(Vector3D position, Font font, String symbols, float height) {

        return new Text(position, font, symbols, height);
    }
}
