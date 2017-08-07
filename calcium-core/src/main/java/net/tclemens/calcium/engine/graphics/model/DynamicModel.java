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

package net.tclemens.calcium.engine.graphics.model;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.animation.Animation;
import net.tclemens.calcium.engine.graphics.mesh.Mesh;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents a model with a dynamic transformation
 *
 * @author Tim Clemens
 */
final class DynamicModel extends Model {

    /** The mesh of the model */
    private final Mesh mesh;

    /** The texture region applied to the model */
    private final Region region;

    /** The animation applied to the model */
    private final Animation animation;

    /**
     * @param mesh The mesh of the model
     * @param region The texture region applied to the model
     * @param animation The animation applied to the model
     */
    DynamicModel(Mesh mesh, Region region, Animation animation) {

        this.mesh = mesh;
        this.region = region;
        this.animation = animation;
    }

    @NonNull
    @Override
    public final Mesh getMesh() {

        return mesh;
    }

    @NonNull
    @Override
    public final Region getRegion() {

        return region;
    }

    @NonNull
    @Override
    public final Matrix3D getMatrix() {

        return animation.getMatrix();
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @NonNull
    @Override
    public final Model update(long time) {

        Animation animation = this.animation.update(time);

        if (animation.isDynamic()) {

            return ModelFactory.buildDynamic(mesh, region, animation);
        }

        return ModelFactory.buildStatic(mesh, region, animation.getMatrix());
    }
}
