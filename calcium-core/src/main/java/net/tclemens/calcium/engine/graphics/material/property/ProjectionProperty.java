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

package net.tclemens.calcium.engine.graphics.material.property;

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Collection;

import net.tclemens.calcium.engine.graphics.camera.Camera;
import net.tclemens.calcium.engine.graphics.model.Model;
import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents a projection matrix variable in the shader program of a material
 *
 * @author Tim Clemens
 */
final class ProjectionProperty extends Property {

    /** The number of elements in the matrix */
    private static final int SIZE = 16;

    /** The stride of the matrix in bytes */
    private static final int STRIDE = Float.SIZE / Byte.SIZE * SIZE;

    /** The name of the projection matrix variable in the shader */
    private final String name;

    /** The buffer used to store the projection matrices */
    private volatile FloatBuffer buffer;

    /**
     * @param name The name of the model-view matrix variable in the shader
     */
    ProjectionProperty(String name) {

        this.name = name;
    }

    @Override
    public void load(int program, @NonNull Camera camera, @NonNull Collection<Model> models) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to load a property with a null camera");
        }

        if (models == null || models.isEmpty()) {

            throw new IllegalArgumentException("Unable to load a property with a null or empty model collection");
        }

        int cameraHandle = GLES20.glGetUniformLocation(program, name);

        buffer = packProjectionMatrix(camera);

        GLES20.glUniformMatrix4fv(cameraHandle, 1, false, buffer);
    }

    @Override
    public void unload(int program) {
    }

    /**
     * Pack the projection matrix for each model into a buffer
     *
     * @param camera The camera used to draw the models
     *
     * @return The packed projection matrix buffer
     */
    private static FloatBuffer packProjectionMatrix(Camera camera) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        Matrix3D projection = camera.getProjection();

        buffer.putFloat(projection.getAX());
        buffer.putFloat(projection.getAY());
        buffer.putFloat(projection.getAZ());
        buffer.putFloat(projection.getAW());

        buffer.putFloat(projection.getBX());
        buffer.putFloat(projection.getBY());
        buffer.putFloat(projection.getBZ());
        buffer.putFloat(projection.getBW());

        buffer.putFloat(projection.getCX());
        buffer.putFloat(projection.getCY());
        buffer.putFloat(projection.getCZ());
        buffer.putFloat(projection.getCW());

        buffer.putFloat(projection.getDX());
        buffer.putFloat(projection.getDY());
        buffer.putFloat(projection.getDZ());
        buffer.putFloat(projection.getDW());

        buffer.position(0);

        return buffer.asFloatBuffer();
    }
}
