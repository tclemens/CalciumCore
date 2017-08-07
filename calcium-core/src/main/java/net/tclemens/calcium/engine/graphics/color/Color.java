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

/**
 * This class represents a color with red, green, blue, and alpha components
 *
 * @author Tim Clemens
 */
public final class Color {

    /** The depth of each component */
    public static final int DEPTH = 256;

    /** The red component */
    private final float red;

    /** The green component */
    private final float green;

    /** The blue component */
    private final float blue;

    /** The alpha component */
    private final float alpha;

    /**
     * @param red The red component
     * @param green The green component
     * @param blue The blue component
     * @param alpha The alpha component
     */
    Color(float red, float green, float blue, float alpha) {

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Get the red component
     *
     * @return The red component
     */
    public final float getRed() {

        return red;
    }

    /**
     * Get the green component
     *
     * @return The green component
     */
    public final float getGreen() {

        return green;
    }

    /**
     * Get the blue component
     *
     * @return The blue component
     */
    public final float getBlue() {

        return blue;
    }

    /**
     * Get the alpha component
     *
     * @return The red component
     */
    public final float getAlpha() {

        return alpha;
    }

    /**
     * Convert the color to an RGBA integer
     *
     * @return An RGBA integer
     */
    public final int toRGBA() {

        return packComponents(red, green, blue, alpha);
    }

    /**
     * Convert the color to an ARGB integer
     *
     * @return An ARGB integer
     */
    public final int toARGB() {

        return packComponents(alpha, red, green, blue);
    }

    /**
     * Approximate the floating-point value of an integer component
     *
     * @param component The integer component
     *
     * @return The converted component
     */
    public static float toFloat(int component) {

        return component / (float) (DEPTH - 1);
    }

    /**
     * Approximate the integer value of a floating-point component
     *
     * @param component The floating-point component
     *
     * @return The converted component
     */
    public static int toInteger(float component) {

        return Math.round(component * (DEPTH - 1));
    }

    /**
     * Pack four floating-point components into an integer
     *
     * @param a The first floating-point component
     * @param b The second floating-point component
     * @param c The third floating-point component
     * @param d The fourth floating-point component
     *
     * @return The packed components
     */
    private static int packComponents(float a, float b, float c, float d) {

        return toInteger(a) << 24 | toInteger(b) << 16 | toInteger(c) << 8 | toInteger(d);
    }
}
