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

import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents an interpolated affine transformation
 *
 * @author Tim Clemens
 */
public abstract class Transformation {

    Transformation() {
    }

    /**
     * Get the interpolated matrix of the transformation
     *
     * @return The interpolated matrix of the transformation
     */
    public abstract Matrix3D getMatrix();

    /**
     * Check if the transformation is dynamic
     *
     * @return <tt>true</tt> if the transformation is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Attempt to update the interpolation of the transformation
     *
     * @param time The current time in milliseconds
     *
     * @return The updated transformation
     */
    @NonNull
    public abstract Transformation update(long time);

    /**
     * Apply the full interpolation of the transformation
     *
     * @return The updated transformation
     */
    @NonNull
    public abstract Transformation finish();
}
