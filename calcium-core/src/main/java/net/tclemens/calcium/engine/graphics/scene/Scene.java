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

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.batch.Batch;
import net.tclemens.calcium.engine.graphics.camera.Camera;

/**
 * This class represents a collection of batches to draw using the same camera
 *
 * @author Tim Clemens
 */
public abstract class Scene {

    /** The camera used to view the scene */
    private final Camera camera;

    /** The batches to draw in the scene */
    private final Collection<Batch> batches;

    /**
     * @param camera The camera used to view the scene
     * @param batches The batches to draw in the scene
     */
    Scene(Camera camera, Collection<Batch> batches) {

        this.camera = camera;
        this.batches = batches;
    }

    /**
     * Draw each batch in the active render context
     */
    public final void draw() {

        for (Batch batch : batches) {

            batch.draw(camera);
        }
    }

    /**
     * Get the camera for the scene
     *
     * @return The camera
     */
    @NonNull
    public final Camera getCamera() {

        return camera;
    }

    /**
     * Get the batches in the scene
     *
     * @return The batches
     */
    @NonNull
    public final Collection<Batch> getBatches() {

        return batches;
    }

    /**
     * Check if the scene is dynamic
     *
     * @return <tt>true</tt> if the scene is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Attempt to update the camera and the batches in the scene
     *
     * @param time The current time in milliseconds
     *
     * @return The updated scene
     */
    @NonNull
    public abstract Scene update(long time);
}
