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

package net.tclemens.calcium.engine.graphics.texture.region;

/**
 * This class is responsible for creating and initializing texture regions
 *
 * @author Tim Clemens
 */
public final class Region {

    /** The bottom of the texture region */
    private final float bottom;

    /** The top of the texture region */
    private final float top;

    /** The left of the texture region */
    private final float left;

    /** The right of the texture region */
    private final float right;

    /**
     * @param bottom The bottom of the texture region
     * @param top The top of the texture region
     * @param left The left of the texture region
     * @param right The right of the texture region
     */
    Region(float bottom, float top, float left, float right) {

        this.bottom = bottom;
        this.top = top;
        this.left = left;
        this.right = right;
    }

    /**
     * Get the bottom of the texture region
     *
     * @return The bottom of the texture region
     */
    public final float getBottom() {

        return bottom;
    }

    /**
     * Get the top of the texture region
     *
     * @return The top of the texture region
     */
    public final float getTop() {

        return top;
    }

    /**
     * Get the left of the texture region
     *
     * @return The left of the texture region
     */
    public final float getLeft() {

        return left;
    }

    /**
     * Get the right of the texture region
     *
     * @return The right of the texture region
     */
    public final float getRight() {

        return right;
    }

    /**
     * Get the width of the texture region
     *
     * @return The width of the texture region
     */
    public final float getWidth() {

        return right - left;
    }

    /**
     * Get the height of the texture region
     *
     * @return The height of the texture region
     */
    public final float getHeight() {

        return top - bottom;
    }
}
