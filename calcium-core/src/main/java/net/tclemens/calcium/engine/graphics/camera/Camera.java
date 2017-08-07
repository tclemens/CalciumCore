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
 * This class represents the projection and view matrices applied to a scene
 *
 * @author Tim Clemens
 */
public abstract class Camera {

    /** The projection matrix of the camera */
    private final Matrix3D projection;

    /**
     * @param projection The projection matrix of the camera
     */
    Camera(Matrix3D projection) {

        this.projection = projection;
    }

    /**
     * Get the projection matrix of the camera
     *
     * @return The projection matrix of the camera
     */
    @NonNull
    public final Matrix3D getProjection() {

        return projection;
    }

    /**
     * Get the view matrix of the camera
     *
     * @return The view matrix of the camera
     */
    @NonNull
    public abstract Matrix3D getView();

    /**
     * Check if the camera is dynamic
     *
     * @return <tt>true</tt> if the camera is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Attempt to update the transformation of the camera
     *
     * @param time The current time in milliseconds
     *
     * @return The updated camera
     */
    @NonNull
    public abstract Camera update(long time);
}
