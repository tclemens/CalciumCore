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
 * This class represents a mesh and it's applied texture region and transformation matrix
 *
 * @author Tim Clemens
 */
public abstract class Model {

    Model() {
    }

    /**
     * Get the mesh of the model
     *
     * @return The mesh of the model
     */
    @NonNull
    public abstract Mesh getMesh();

    /**
     * Get the texture region applied to the model
     *
     * @return The texture region applied to the model
     */
    @NonNull
    public abstract Region getRegion();

    /**
     * Get the transformation matrix applied to the model
     *
     * @return The world transformation matrix applied to the model
     */
    @NonNull
    public abstract Matrix3D getMatrix();

    /**
     * Check if the model is dynamic
     *
     * @return <tt>true</tt> if the model is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Attempt to update the transformation of the model
     *
     * @param time The current time in milliseconds
     *
     * @return The updated model
     */
    @NonNull
    public abstract Model update(long time);
}
