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

import net.tclemens.calcium.engine.graphics.camera.Camera;
import net.tclemens.calcium.engine.graphics.material.Material;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class represents a batch with dynamic models
 *
 * @author Tim Clemens
 */
final class DynamicBatch extends Batch {

    /** The material used to draw the batch */
    private final Material material;

    /** The models in the batch */
    private final Collection<Model> models;

    /**
     * @param material The material used to draw the batch
     * @param models The models in the batch
     */
    DynamicBatch(Material material, Collection<Model> models) {

        this.material = material;
        this.models = models;
    }

    @NonNull
    @Override
    public final Material getMaterial() {

        return material;
    }

    @NonNull
    @Override
    public final Collection<Model> getModels() {

        return models;
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @Override
    public final void draw(@NonNull Camera camera) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to draw a batch with a null camera");
        }

        material.draw(camera, models);
    }

    @NonNull
    @Override
    public final Batch update(long time) {

        Collection<Model> models = new ArrayList<>(this.models.size());
        boolean isDynamic = false;

        for (Model model : this.models) {

            model = model.update(time);
            models.add(model);

            isDynamic = isDynamic || model.isDynamic();
        }

        models = Collections.unmodifiableCollection(models);

        if (isDynamic) {

            return BatchFactory.buildDynamic(material, models);
        }

        return BatchFactory.buildStatic(material, models);
    }
}
