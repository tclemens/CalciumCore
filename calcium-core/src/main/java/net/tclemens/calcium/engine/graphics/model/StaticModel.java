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

import net.tclemens.calcium.engine.graphics.mesh.Mesh;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents a model with a static transformation
 *
 * @author Tim Clemens
 */
final class StaticModel extends Model {

    /** The mesh of the model */
    private final Mesh mesh;

    /** The texture region applied to the model */
    private final Region region;

    /** The transformation matrix applied to the model */
    private final Matrix3D matrix;

    /**
     * @param mesh The mesh of the model
     * @param region The texture region applied to the model
     * @param matrix The transformation matrix applied to the model
     */
    StaticModel(Mesh mesh, Region region, Matrix3D matrix) {

        this.mesh = mesh;
        this.region = region;
        this.matrix = matrix;
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

        return matrix;
    }

    @Override
    public final boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public final Model update(long time) {

        return this;
    }
}
