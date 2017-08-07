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
 * This class decorates a batch to permanently prevent it from being drawn
 *
 * @author Tim Clemens
 */
final class TerminalBatch extends Batch {

    /** The batch to decorate */
    private final Batch batch;

    /**
     * @param batch The batch to decorate
     */
    TerminalBatch(Batch batch) {

        this.batch = batch;
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

        return false;
    }

    @Override
    public final void draw(@NonNull Camera camera) {
    }

    @NonNull
    @Override
    public final Batch update(long time) {

        return this;
    }
}
