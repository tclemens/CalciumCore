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

import net.tclemens.calcium.engine.graphics.animation.Animation;
import net.tclemens.calcium.math.matrix.Matrix3D;
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class is responsible for creating and initializing cameras
 *
 * @author Tim Clemens
 */
public final class CameraFactory {

    private CameraFactory() {
    }

    /**
     * Create a dynamic camera
     *
     * @param projection The projection matrix of the camera
     * @param eye The position the camera
     * @param center The focal point of the camera
     * @param up The upward orientation from the origin of the camera
     * @param animation The animation to apply to the camera
     *
     * @return The new camera
     *
     * @throws IllegalArgumentException If camera projection matrix, view vectors, or animation is invalid
     */
    public static Camera createDynamic(Matrix3D projection, Vector3D eye, Vector3D center, Vector3D up, Animation animation) {

        if (projection == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null projection matrix");
        }

        if (eye == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null eye vector");
        }

        if (center == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null center vector");
        }

        if (up == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null up vector");
        }

        if (animation == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null animation");
        }

        return buildDynamic(projection, eye, center, up, animation);
    }

    /**
     * Create a static camera
     *
     * @param projection The projection matrix of the camera
     * @param view The view matrix of the camera
     *
     * @return The new camera
     *
     * @throws IllegalArgumentException If the camera projection or view matrices are invalid
     */
    public static Camera createStatic(Matrix3D projection, Matrix3D view) {

        if (projection == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null projection matrix");
        }

        if (view == null) {

            throw new IllegalArgumentException("Unable to create a camera with a null view matrix");
        }

        return buildStatic(projection, view);
    }

    /**
     * Create a dynamic camera
     *
     * @param projection The projection matrix of the camera
     * @param eye The position the camera
     * @param center The focal point of the camera
     * @param up The upward orientation from the origin of the camera
     * @param animation The animation to apply to the camera
     *
     * @return The new camera
     */
    static Camera buildDynamic(Matrix3D projection, Vector3D eye, Vector3D center, Vector3D up, Animation animation) {

        return new DynamicCamera(projection, eye, center, up, animation);
    }

    /**
     * Create a static camera
     *
     * @param projection The projection matrix of the camera
     * @param view The view matrix of the camera
     *
     * @return The new camera
     */
    static Camera buildStatic(Matrix3D projection, Matrix3D view) {

        return new StaticCamera(projection, view);
    }
}
