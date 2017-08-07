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

import android.graphics.Matrix;
import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.animation.Animation;
import net.tclemens.calcium.engine.graphics.mesh.Mesh;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.engine.graphics.texture.region.RegionFactory;
import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class is responsible for creating and initializing models
 *
 * @author Tim Clemens
 */
public final class ModelFactory {

    private ModelFactory() {
    }

    /**
     * Create a static model with the default texture region
     *
     * @param mesh The mesh of the model
     * @param matrix The transformation matrix applied to the model
     *
     * @return The new model
     *
     * @throws IllegalArgumentException If the mesh or transformation matrix is null
     */
    @NonNull
    public static Model createStatic(@NonNull Mesh mesh, @NonNull Matrix3D matrix) {

        return createStatic(mesh, RegionFactory.createDefault(), matrix);
    }

    /**
     * Create a static model
     *
     * @param mesh The mesh of the model
     * @param region The texture region applied to the model
     * @param matrix The transformation matrix applied to the model
     *
     * @return The new model
     *
     * @throws IllegalArgumentException If the mesh, texture region, or transformation matrix is null
     */
    @NonNull
    public static Model createStatic(@NonNull Mesh mesh, @NonNull Region region, @NonNull Matrix3D matrix) {

        if (mesh == null) {

            throw new IllegalArgumentException("Unable to create a model with a null mesh");
        }

        if (region == null) {

            throw new IllegalArgumentException("Unable to create a model with a null texture region");
        }

        if (matrix == null) {

            throw new IllegalArgumentException("Unable to create a model with a null transformation matrix");
        }

        return buildStatic(mesh, region, matrix);
    }

    /**
     * Create a dynamic model with the default texture region
     *
     * @param mesh The mesh of the model
     * @param animation The animation applied to the model
     *
     * @return The new model
     *
     * @throws IllegalArgumentException If the mesh or animation is null
     */
    @NonNull
    public static Model createDynamic(@NonNull Mesh mesh, @NonNull Animation animation) {

        return createDynamic(mesh, RegionFactory.createDefault(), animation);
    }

    /**
     * Create a dynamic model
     *
     * @param mesh The mesh of the model
     * @param region The texture region applied to the model
     * @param animation The animation applied to the model
     *
     * @return The new model
     *
     * @throws IllegalArgumentException If the mesh, texture region, or animation is null
     */
    @NonNull
    public static Model createDynamic(@NonNull Mesh mesh, @NonNull Region region, @NonNull Animation animation) {

        if (mesh == null) {

            throw new IllegalArgumentException("Unable to create a model with a null mesh");
        }

        if (region == null) {

            throw new IllegalArgumentException("Unable to create a model with a null texture region");
        }

        if (animation == null) {

            throw new IllegalArgumentException("Unable to create a model with a null animation");
        }

        return buildDynamic(mesh, region, animation);
    }

    /**
     * Create a static model
     *
     * @param mesh The mesh of the model
     * @param region The texture region applied to the model
     * @param matrix The transformation matrix applied to the model
     *
     * @return The new model
     */
    static Model buildStatic(Mesh mesh, Region region, Matrix3D matrix) {

        return new StaticModel(mesh, region, matrix);
    }

    /**
     * Create a dynamic model
     *
     * @param mesh The mesh of the model
     * @param region The texture region applied to the model
     * @param animation The animation applied to the model
     *
     * @return The new model
     */
    static Model buildDynamic(Mesh mesh, Region region, Animation animation) {

        return new DynamicModel(mesh, region, animation);
    }
}
