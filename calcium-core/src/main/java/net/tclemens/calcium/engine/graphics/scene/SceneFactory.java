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

package net.tclemens.calcium.engine.graphics.scene;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.engine.graphics.batch.Batch;
import net.tclemens.calcium.engine.graphics.camera.Camera;

/**
 * This class is responsible for creating and initializing scenes
 *
 * @author Tim Clemens
 */
public final class SceneFactory {

    private SceneFactory() {
    }

    /**
     * Create a static scene
     *
     * @param camera The camera used to view the scene
     * @param batches The batches to draw in the scene
     *
     * @return The new scene
     *
     * @throws IllegalArgumentException If the camera or batches are invalid
     */
    @NonNull
    public static Scene createStatic(@NonNull Camera camera, @NonNull Collection<Batch> batches) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to create a scene with a null camera");
        }

        if (batches == null) {

            throw new IllegalArgumentException("Unable to create a scene with a null batch collection");
        }

        if (batches.contains(null)) {

            throw new IllegalArgumentException("Unable to create a scene with null batches");
        }

        batches = Collections.unmodifiableCollection(new ArrayList<>(batches));

        return buildStatic(camera, batches);
    }

    /**
     * Create a dynamic scene
     *
     * @param camera The camera used to view the scene
     * @param batches The batches to draw in the scene
     *
     * @return The new scene
     *
     * @throws IllegalArgumentException If the camera or batches are invalid
     */
    @NonNull
    public static Scene createDynamic(@NonNull Camera camera, @NonNull Collection<Batch> batches) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to create a scene with a null camera");
        }

        if (batches == null) {

            throw new IllegalArgumentException("Unable to create a scene with a null batch collection");
        }

        if (batches.contains(null)) {

            throw new IllegalArgumentException("Unable to create a scene with null batches");
        }

        batches = Collections.unmodifiableCollection(new ArrayList<>(batches));

        return buildDynamic(camera, batches);
    }


    /**
     * Create a static scene
     *
     * @param camera The camera used to view the scene
     * @param batches The batches to draw in the scene
     *
     * @return The new scene
     */
    static Scene buildStatic(Camera camera, Collection<Batch> batches) {

        return new StaticScene(camera, batches);
    }

    /**
     * Create a dynamic scene
     *
     * @param camera The camera used to view the scene
     * @param batches The batches to draw in the scene
     *
     * @return The new scene
     */
    static Scene buildDynamic(Camera camera, Collection<Batch> batches) {

        return new DynamicScene(camera, batches);
    }
}
