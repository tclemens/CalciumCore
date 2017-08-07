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

package net.tclemens.calcium.engine.graphics.animation.transformation;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.animation.interpolation.Interpolation;
import net.tclemens.calcium.engine.graphics.animation.interpolation.InterpolationFactory;
import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class is responsible for creating and initializing transformations
 *
 * @author Tim Clemens
 */
public final class TransformationFactory {

    private TransformationFactory() {
    }

    /**
     * Create an immediate rotate transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     *
     * @return The new transformation
     */
    @NonNull
    public static Transformation createRotate(float x, float y, float z) {

        return createRotate(x, y, z, InterpolationFactory.createComplete());
    }

    /**
     * Create an interpolated rotate transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     *
     * @return The new transformation
     *
     * @throws IllegalArgumentException If the transformation interpolation is invalid
     */
    @NonNull
    public static Transformation createRotate(float x, float y, float z, @NonNull Interpolation interpolation) {

        if (interpolation == null) {

            throw new IllegalArgumentException("Unable to create a transformation with a null interpolation");
        }

        return buildRotate(x, y, z, interpolation);
    }

    /**
     * Create an immediate scale transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     *
     * @return The new transformation
     */
    @NonNull
    public static Transformation createScale(float x, float y, float z) {

        return createScale(x, y, z, InterpolationFactory.createComplete());
    }

    /**
     * Create an interpolated scale transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     *
     * @return The new transformation
     *
     * @throws IllegalArgumentException If the transformation interpolation is invalid
     */
    @NonNull
    public static Transformation createScale(float x, float y, float z, @NonNull Interpolation interpolation) {

        if (interpolation == null) {

            throw new IllegalArgumentException("Unable to create a transformation with a null interpolation");
        }

        return buildScale(x, y, z, interpolation);
    }

    /**
     * Create an immediate translate transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     *
     * @return The new transformation
     */
    @NonNull
    public static Transformation createTranslate(float x, float y, float z) {

        return createTranslate(x, y, z, InterpolationFactory.createComplete());
    }

    /**
     * Create an interpolated translate transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     *
     * @return The new transformation
     *
     * @throws IllegalArgumentException If the transformation interpolation is invalid
     */
    @NonNull
    public static Transformation createTranslate(float x, float y, float z, @NonNull Interpolation interpolation) {

        if (interpolation == null) {

            throw new IllegalArgumentException("Unable to create a transformation with a null interpolation");
        }

        return buildTranslate(x, y, z, interpolation);
    }

    /**
     * Create an interpolated rotate transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     *
     * @return The new transformation
     */
    static Transformation buildRotate(float x, float y, float z, Interpolation interpolation) {

        return new RotateTransformation(x, y, z, interpolation);
    }

    /**
     * Create an interpolated scale transformation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     *
     * @return The new transformation
     */
    static Transformation buildScale(float x, float y, float z, Interpolation interpolation) {

        return new ScaleTransformation(x, y, z, interpolation);
    }

    /**
     * Create an interpolated translate transformation without validation
     *
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     *
     * @return The new transformation
     */
    static Transformation buildTranslate(float x, float y, float z, Interpolation interpolation) {

        return new TranslateTransformation(x, y, z, interpolation);
    }

    /**
     * Create an complete transformation without validation
     *
     * @param matrix The matrix of the transformation
     *
     * @return The new transformation
     */
    static Transformation buildComplete(Matrix3D matrix) {

        return new CompleteTransformation(matrix);
    }
}
