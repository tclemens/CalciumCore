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

package net.tclemens.calcium.engine.graphics.batch;

import android.support.annotation.NonNull;

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.camera.Camera;
import net.tclemens.calcium.engine.graphics.material.Material;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class represents a collection of models to draw using the same material
 *
 * @author Tim Clemens
 */
public abstract class Batch {

    Batch() {
    }

    /**
     * Get the material used to draw the batch
     *
     * @return The material used to draw the batch
     */
    @NonNull
    public abstract Material getMaterial();

    /**
     * Get the models in the batch
     *
     * @return The models in the batch
     */
    @NonNull
    public abstract Collection<Model> getModels();

    /**
     * Check if the batch is dynamic
     *
     * @return <tt>true</tt> if the batch is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Draw each model using the material in the active render context
     *
     * @param camera The camera used to view the batches
     */
    public abstract void draw(@NonNull Camera camera);

    /**
     * Attempt to update each model in the batch
     *
     * @param time The current time in milliseconds
     *
     * @return The updated instance of the current batch
     */
    @NonNull
    public abstract Batch update(long time);
}
