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
 * This class decorates a batch to prevent it from being drawn until a specified time
 *
 * @author Tim Clemens
 */
final class DelayedBatch extends Batch {

    /** The batch to decorate */
    private final Batch batch;

    /** The time start drawing the batch in milliseconds */
    private final long start;

    /**
     * @param batch The batch to decorate
     * @param start The time start drawing the batch in milliseconds
     */
    DelayedBatch(Batch batch, long start) {

        this.batch = batch;
        this.start = start;
    }

    @NonNull
    @Override
    public final Material getMaterial() {

        return batch.getMaterial();
    }

    @NonNull
    @Override
    public Collection<Model> getModels() {

        return batch.getModels();
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @Override
    public final void draw(@NonNull Camera camera) {
    }

    @NonNull
    @Override
    public final Batch update(long time) {

        if (time < start) {

            return this;
        }

        return batch;
    }
}
