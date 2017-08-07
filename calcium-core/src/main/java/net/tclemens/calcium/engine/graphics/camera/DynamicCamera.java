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

import net.tclemens.calcium.engine.graphics.animation.Animation;
import net.tclemens.calcium.math.matrix.Matrix3D;
import net.tclemens.calcium.math.matrix.MatrixFactory;
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class represents a camera with a dynamic transformation
 *
 * @author Tim Clemens
 */
final class DynamicCamera extends Camera {

    /** The eye vector of the camera */
    private final Vector3D eye;

    /** The center vector of the camera */
    private final Vector3D center;

    /** The up vector of the camera */
    private final Vector3D up;

    /** The animation to apply to the camera */
    private final Animation animation;

    /**
     * @param projection The projection matrix of the camera
     * @param eye The eye vector of the camera
     * @param center The eye center of the camera
     * @param up The eye up of the camera
     * @param animation The animation to apply to the camera
     */
    DynamicCamera(Matrix3D projection, Vector3D eye, Vector3D center, Vector3D up, Animation animation) {

        super(projection);

        this.eye = eye;
        this.center = center;
        this.up = up;
        this.animation = animation;
    }

    @NonNull
    @Override
    public final Matrix3D getView() {

        return computeView(eye, center, up, animation);
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @NonNull
    @Override
    public final Camera update(long time) {

        Animation animation = this.animation.update(time);

        if (animation.isDynamic()) {

            return CameraFactory.buildDynamic(getProjection(), eye, center, up, animation);
        }

        Matrix3D view = computeView(eye, center, up, animation);

        return CameraFactory.buildStatic(getProjection(), view);
    }

    private static Matrix3D computeView(Vector3D eye, Vector3D center, Vector3D up, Animation animation) {

        Matrix3D matrix = animation.getMatrix();

        return MatrixFactory.createView3D(
                matrix.multiply(eye),
                matrix.multiply(center),
                matrix.multiply(up));
    }
}
