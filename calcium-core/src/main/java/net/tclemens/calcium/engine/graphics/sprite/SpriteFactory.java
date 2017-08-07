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

package net.tclemens.calcium.engine.graphics.sprite;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.engine.graphics.texture.region.RegionFactory;
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class is responsible for creating and initializing sprites
 *
 * @author Tim Clemens
 */
public final class SpriteFactory {

    private SpriteFactory() {
    }

    /**
     * Create a sprite with the default texture region
     *
     * @param position The position of the sprite
     * @param width The width of the sprite
     * @param height The height of the sprite
     *
     * @return The new sprite
     *
     * @throws IllegalArgumentException If the sprite position, texture region, width, or height is invalid
     */
    @NonNull
    public static Sprite createSprite(@NonNull Vector3D position, float width, float height) {

        return createSprite(position, RegionFactory.createDefault(), width, height);
    }

    /**
     * Create a sprite
     *
     * @param position The position of the sprite
     * @param region The texture region of the sprite
     * @param width The width of the sprite
     * @param height The height of the sprite
     *
     * @return The new sprite
     *
     * @throws IllegalArgumentException If the sprite position, texture region, width, or height is invalid
     */
    @NonNull
    public static Sprite createSprite(@NonNull Vector3D position, @NonNull Region region, float width, float height) {

        if (position == null) {

            throw new IllegalArgumentException("Unable to create a sprite with a null position");
        }

        if (region == null) {

            throw new IllegalArgumentException("Unable to create a sprite with a null texture region");
        }

        if (width <= 0f) {

            throw new IllegalArgumentException("Unable to create a sprite with a zero or negative width");
        }

        if (height <= 0f) {

            throw new IllegalArgumentException("Unable to create a sprite with a zero or negative height");
        }

        return buildSprite(position, region, width, height);
    }

    /**
     * Create a sprite
     *
     * @param position The position of the sprite
     * @param region The texture region of the sprite
     * @param width The width of the sprite
     * @param height The height of the sprite
     *
     * @return The new sprite
     */
    static Sprite buildSprite(Vector3D position, Region region, float width, float height) {

        return new Sprite(position, region, width, height);
    }
}
