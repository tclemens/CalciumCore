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

package net.tclemens.calcium.engine.graphics.camera;

import android.support.annotation.NonNull;

import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents a camera with a static transformation
 *
 * @author Tim Clemens
 */
final class StaticCamera extends Camera {

    /** The projection matrix of the camera */
    private final Matrix3D view;

    /**
     * @param projection The projection matrix of the camera
     * @param view The view matrix of the camera
     */
    StaticCamera(Matrix3D projection, Matrix3D view) {

        super(projection);

        this.view = view;
    }

    @NonNull
    @Override
    public final Matrix3D getView() {

        return view;
    }

    @Override
    public final boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public final Camera update(long time) {

        return this;
    }
}
