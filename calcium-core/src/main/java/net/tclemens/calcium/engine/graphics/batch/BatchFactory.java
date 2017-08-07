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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.engine.graphics.material.Material;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class is responsible for creating and initializing batches
 *
 * @author Tim Clemens
 */
public final class BatchFactory {

    private BatchFactory() {
    }

    /**
     * Create a static batch
     *
     * @param material The material used to draw the batch
     * @param models The models in the batch
     *
     * @return The new batch
     *
     * @throws IllegalArgumentException If the batch material or models are invalid
     */
    @NonNull
    public static Batch createStatic(@NonNull Material material, @NonNull Collection<Model> models) {

        if (material == null) {

            throw new IllegalArgumentException("Unable to create a batch with a null material");
        }

        if (models == null) {

            throw new IllegalArgumentException("Unable to create a batch with a null model collection");
        }

        if (models.contains(null)) {

            throw new IllegalArgumentException("Unable to create a batch with null models");
        }

        models = Collections.unmodifiableCollection(new ArrayList<>(models));

        return buildStatic(material, models);
    }

    /**
     * Create a dynamic batch
     *
     * @param material The material used to draw the batch
     * @param models The models in the batch
     *
     * @return The new batch
     *
     * @throws IllegalArgumentException If the batch material or models are invalid
     */
    @NonNull
    public static Batch createDynamic(@NonNull Material material, @NonNull Collection<Model> models) {

        if (material == null) {

            throw new IllegalArgumentException("Unable to create a batch with a null material");
        }

        if (models == null) {

            throw new IllegalArgumentException("Unable to create a batch with a null model collection");
        }

        if (models.contains(null)) {

            throw new IllegalArgumentException("Unable to create a batch with null models");
        }

        models = Collections.unmodifiableCollection(new ArrayList<>(models));

        return buildDynamic(material, models);
    }

    /**
     * Create a decorator to prevent a batch from being drawn until a specified point in time
     *
     * @param batch The batch to decorate
     * @param start The time start drawing the batch in milliseconds
     *
     * @return The batch decorator
     *
     * @throws IllegalArgumentException If the decorated batch or start time is invalid
     */
    @NonNull
    public static Batch createDelayed(@NonNull Batch batch, long start) {

        if (batch == null) {

            throw new IllegalArgumentException("Unable to create a delayed batch decorator on a null batch");
        }

        if (start < 0) {

            throw new IllegalArgumentException("Unable to create a delayed batch decorator with a negative start time");
        }

        return buildDelayed(batch, start);
    }

    /**
     * Create a decorator to allow a batch to be drawn until a specified point in time
     *
     * @param batch The batch to decorate
     * @param end The time to stop drawing the batch in milliseconds
     *
     * @return The batch decorator
     *
     * @throws IllegalArgumentException If the decorated batch or end time is invalid
     */
    @NonNull
    public static Batch createTransient(@NonNull Batch batch, long end) {

        if (batch == null) {

            throw new IllegalArgumentException("Unable to create a transient batch decorator on a null batch");
        }

        if (end < 0) {

            throw new IllegalArgumentException("Unable to create a transient batch decorator with a negative end time");
        }

        return buildTransient(batch, end);
    }

    /**
     * Create a static batch
     *
     * @param material The material used to draw the batch
     * @param models The models in the batch
     *
     * @return The new batch
     */
    static Batch buildStatic(Material material, Collection<Model> models) {

        return new StaticBatch(material, models);
    }

    /**
     * Create a dynamic batch
     *
     * @param material The material used to draw the batch
     * @param models The models in the batch
     *
     * @return The new batch
     */
    static Batch buildDynamic(Material material, Collection<Model> models) {

        return new DynamicBatch(material, models);
    }

    /**
     * Create a decorator to prevent a batch from being drawn until a specified point in time
     *
     * @param batch The batch to decorate
     * @param start The time start drawing the batch in milliseconds
     *
     * @return The batch decorator
     */
    static Batch buildDelayed(Batch batch, long start) {

        return new DelayedBatch(batch, start);
    }

    /**
     * Create a decorator to allow a batch to be drawn until a specified point in time
     *
     * @param batch The batch to decorate
     * @param end The time to stop drawing the batch in milliseconds
     *
     * @return The batch decorator
     */
    static Batch buildTransient(Batch batch, long end) {

        return new TransientBatch(batch, end);
    }

    /**
     * Create a decorator to prevent a batch from being drawn
     *
     * @param batch The batch to decorate
     *
     * @return The batch decorator
     */
    static Batch buildTerminal(Batch batch) {

        return new TerminalBatch(batch);
    }
}
