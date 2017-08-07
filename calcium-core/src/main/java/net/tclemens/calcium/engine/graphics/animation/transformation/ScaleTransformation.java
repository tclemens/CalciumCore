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
import net.tclemens.calcium.math.matrix.Matrix3D;
import net.tclemens.calcium.math.matrix.MatrixFactory;

/**
 * This class represents an interpolated scale transformation
 *
 * @author Tim Clemens
 */
final class ScaleTransformation extends Transformation {

    /** The x magnitude of the transformation */
    private final float x;

    /** The y magnitude of the transformation */
    private final float y;

    /** The z magnitude of the transformation */
    private final float z;

    /** The interpolation of the transformation */
    private final Interpolation interpolation;

    /**
     * @param x The x magnitude of the transformation
     * @param y The y magnitude of the transformation
     * @param z The z magnitude of the transformation
     * @param interpolation The interpolation of the transformation
     */
    ScaleTransformation(float x, float y, float z, Interpolation interpolation) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.interpolation = interpolation;
    }

    @Override
    public final Matrix3D getMatrix() {

        return computeScale(x, y, z, interpolation);
    }

    @Override
    public final boolean isDynamic() {

        return interpolation.isDynamic();
    }

    @NonNull
    @Override
    public final Transformation update(long time) {

        Interpolation interpolation = this.interpolation.update(time);

        if (interpolation.isDynamic()) {

            return TransformationFactory.buildScale(x, y, z, interpolation.update(time));
        }

        Matrix3D matrix = computeScale(x, y, z, interpolation);

        return TransformationFactory.buildComplete(matrix);
    }

    @NonNull
    @Override
    public final Transformation finish() {

        Interpolation interpolation = this.interpolation.finish();
        Matrix3D matrix = computeScale(x, y, z, interpolation);

        return TransformationFactory.buildComplete(matrix);
    }

    private static Matrix3D computeScale(float x, float y, float z, Interpolation interpolation) {

        float scale = interpolation.getValue();

        return MatrixFactory.createScale3D(scale * x, scale * y, scale * z);
    }
}
