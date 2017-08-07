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

import android.support.annotation.NonNull;

/**
 * This class represents a region of a texture
 *
 * @author Tim Clemens
 */
public final class RegionFactory {

    private RegionFactory() {
    }

    /**
     * Create a texture region covering the entire texture
     *
     * @return The new texture region
     */
    @NonNull
    public static Region createDefault() {

        return buildRegion(0f, 1f, 0f, 1f);
    }

    /**
     * Create a texture region covering the specified area
     *
     * @param bottom The bottom of the texture region
     * @param top The top of the texture region
     * @param left The left of the texture region
     * @param right The right of the texture region
     *
     * @return The new texture region
     *
     * @throws IllegalArgumentException If any of the region dimensions are invalid
     */
    @NonNull
    public static Region createRegion(float bottom, float top, float left, float right) {

        if (bottom < 0f || bottom > 1f) {

            throw new IllegalArgumentException("Unable to create a texture region with a bottom boundary outside the range (0, 1)");
        }

        if (top < 0f || top > 1f) {

            throw new IllegalArgumentException("Unable to create a texture region with a top boundary outside the range (0, 1)");
        }

        if (left < 0f || left > 1f) {

            throw new IllegalArgumentException("Unable to create a texture region with a left boundary outside the range (0, 1)");
        }

        if (right < 0f || right > 1f) {

            throw new IllegalArgumentException("Unable to create a texture region with a right boundary outside the range (0, 1)");
        }

        return buildRegion(bottom, top, left, right);
    }

    /**
     * Create a texture region covering the entire texture
     *
     * @param bottom The bottom of the texture region
     * @param top The top of the texture region
     * @param left The left of the texture region
     * @param right The right of the texture region
     *
     * @return The new texture region
     */
    static Region buildRegion(float bottom, float top, float left, float right) {

        return new Region(bottom, top, left, right);
    }
}
