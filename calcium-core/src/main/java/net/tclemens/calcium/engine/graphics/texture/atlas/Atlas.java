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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import net.tclemens.calcium.engine.graphics.texture.region.Region;

/**
 * This class represents a set of named texture regions
 *
 * @author Tim Clemens
 */
public final class Atlas {

    /** The named texture regions */
    private final Map<String, Region> regions;

    /** The size of the texture atlas in pixels */
    private final int size;

    /**
     * @param regions The named texture regions
     * @param size The size of the texture atlas in pixels
     */
    Atlas(Map<String, Region> regions, int size) {

        this.regions = regions;
        this.size = size;
    }

    /**
     * Get the names of the texture regions in the atlas
     *
     * @return The names of the texture regions in the atlas
     */
    @NonNull
    public final Collection<String> getNames() {

        return regions.keySet();
    }

    /**
     * Get the texture regions in the atlas
     *
     * @return The texture regions in the atlas
     */
    @NonNull
    public final Collection<Region> getRegions() {

        return regions.values();
    }

    /**
     * Get the texture region associated with the specified name
     *
     * @param name The name of the texture region
     *
     * @return The texture region with the specified name
     */
    @NonNull
    public final Region getRegion(String name) {

        return regions.get(name);
    }

    /**
     * Get the size of the texture atlas in pixels
     *
     * @return The size of the texture atlas in pixels
     */
    public final int getSize() {

        return size;
    }
}
