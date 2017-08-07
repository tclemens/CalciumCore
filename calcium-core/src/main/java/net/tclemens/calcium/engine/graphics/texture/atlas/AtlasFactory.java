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

package net.tclemens.calcium.engine.graphics.texture.atlas;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.tclemens.calcium.engine.graphics.texture.region.Region;

/**
 * This class is responsible for creating and initializing texture atlases
 *
 * @author Tim Clemens
 */
public final class AtlasFactory {

    private AtlasFactory() {
    }

    /**
     * Create a texture atlas
     *
     * @param regions The named texture regions
     * @param size The size of the texture atlas in pixels
     *
     * @return The new texture atlas
     *
     * @throws IllegalArgumentException If the texture regions or size is invalid
     */
    @NonNull
    public static Atlas createAtlas(@NonNull Map<String, Region> regions, int size) {

        if (regions == null) {

            throw new IllegalArgumentException("Unable to create an atlas with a null texture region map");
        }

        if (size <= 0) {

            throw new IllegalArgumentException("Unable to create an atlas with a zero or negative size");
        }

        regions = Collections.unmodifiableMap(new HashMap<>(regions));

        return buildAtlas(regions, size);
    }

    /**
     * Create a texture atlas
     *
     * @param regions The named texture regions
     * @param size The size of the texture atlas in pixels
     *
     * @return The new texture atlas
     */
    static Atlas buildAtlas(Map<String, Region> regions, int size) {

        return new Atlas(regions, size);
    }
}
