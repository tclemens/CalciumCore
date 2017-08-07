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

package net.tclemens.calcium.engine.graphics.color;

import android.support.annotation.NonNull;

/**
 * This class is responsible for creating and initializing new colors
 *
 * @author Tim Clemens
 */
public final class ColorFactory {

    private ColorFactory() {
    }

    /**
     * Create a color with the specified components
     *
     * @param red The red component
     * @param green The green component
     * @param blue The blue component
     * @param alpha The alpha component
     *
     * @return The new color
     *
     * @throws IllegalArgumentException If any of the color components are invalid
     */
    @NonNull
    public static Color createColor(int red, int green, int blue, int alpha) {

        if (red < 0 || red >= Color.DEPTH) {

            throw new IllegalArgumentException("Unable to create a color with a red component outside the range (0, " + (Color.DEPTH - 1) + ")");
        }

        if (green < 0 || green >= Color.DEPTH) {

            throw new IllegalArgumentException("Unable to create a color with a green component outside the range (0, " + (Color.DEPTH - 1) + ")");
        }

        if (blue < 0 || blue >= Color.DEPTH) {

            throw new IllegalArgumentException("Unable to create a color with a blue component outside the range (0, " + (Color.DEPTH - 1) + ")");
        }

        if (alpha < 0 || alpha >= Color.DEPTH) {

            throw new IllegalArgumentException("Unable to create a color with a alpha component outside the range (0, " + (Color.DEPTH - 1) + ")");
        }

        return buildColor(Color.toFloat(red), Color.toFloat(green), Color.toFloat(blue), Color.toFloat(alpha));
    }

    /**
     * Create a color with the specified components
     *
     * @param red The red component
     * @param green The green component
     * @param blue The blue component
     * @param alpha The alpha component
     *
     * @return The new color
     *
     * @throws IllegalArgumentException If any of the color components are invalid
     */
    @NonNull
    public static Color createColor(float red, float green, float blue, float alpha) {

        if (red < 0f || red > 1f) {

            throw new IllegalArgumentException("Unable to create a color with a red component outside the range (0, 1)");
        }

        if (green < 0f || green > 1f) {

            throw new IllegalArgumentException("Unable to create a color with a green component outside the range (0, 1)");
        }

        if (blue < 0f || blue > 1f) {

            throw new IllegalArgumentException("Unable to create a color with a blue component outside the range (0, 1)");
        }

        if (alpha < 0f || alpha > 1f) {

            throw new IllegalArgumentException("Unable to create a color with a alpha component outside the range (0, 1)");
        }

        return buildColor(red, green, blue, alpha);
    }

    /**
     * Create a color with the specified components
     *
     * @param red The red component
     * @param green The green component
     * @param blue The blue component
     * @param alpha The alpha component
     *
     * @return The new color
     */
    static Color buildColor(float red, float green, float blue, float alpha) {

        return new Color(red, green, blue, alpha);
    }
}
