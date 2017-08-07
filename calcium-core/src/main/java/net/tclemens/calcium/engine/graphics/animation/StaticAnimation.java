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

package net.tclemens.calcium.engine.graphics.animation;

import android.support.annotation.NonNull;

import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents a static transformation matrix
 *
 * @author Tim Clemens
 */
final class StaticAnimation extends Animation {

    /** The transformation matrix of the animation */
    private final Matrix3D matrix;

    /**
     * @param matrix The transformation matrix of the animation
     */
    StaticAnimation(Matrix3D matrix) {

        this.matrix = matrix;
    }

    @Override
    public final boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public final Matrix3D getMatrix() {

        return matrix;
    }

    @NonNull
    @Override
    public final Animation update(long time) {

        return this;
    }

    @NonNull
    @Override
    public final Animation finish() {

        return this;
    }
}
