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
 * This class represents a model-view matrix variable in the shader program of a material
 *
 * @author Tim Clemens
 */
final class ModelViewProperty extends Property {

    /** The number of elements in the matrix */
    private static final int SIZE = 16;

    /** The stride of the matrix in bytes */
    private static final int STRIDE = Float.SIZE / Byte.SIZE * SIZE;

    /** The name of the model-view matrix variable in the shader */
    private final String name;

    /** The buffer used to store the model-view matrices */
    private volatile FloatBuffer buffer;

    /**
     * @param name The name of the model-view matrix variable in the shader
     */
    ModelViewProperty(String name) {

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

        buffer = packModelViewMatrices(camera, models);

        GLES20.glUniformMatrix4fv(cameraHandle, models.size(), false, buffer);
    }

    @Override
    public void unload(int program) {
    }

    /**
     * Pack the model-view matrix for each model into a buffer
     *
     * @param camera The camera used to draw the models
     * @param models The models to draw
     *
     * @return The packed model-view matrix buffer
     */
    private static FloatBuffer packModelViewMatrices(Camera camera, Collection<Model> models) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(models.size() * STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        Matrix3D view = camera.getView();

        for (Model model : models) {

            Matrix3D modelView = view.multiply(model.getMatrix());

            buffer.putFloat(modelView.getAX());
            buffer.putFloat(modelView.getAY());
            buffer.putFloat(modelView.getAZ());
            buffer.putFloat(modelView.getAW());

            buffer.putFloat(modelView.getBX());
            buffer.putFloat(modelView.getBY());
            buffer.putFloat(modelView.getBZ());
            buffer.putFloat(modelView.getBW());

            buffer.putFloat(modelView.getCX());
            buffer.putFloat(modelView.getCY());
            buffer.putFloat(modelView.getCZ());
            buffer.putFloat(modelView.getCW());

            buffer.putFloat(modelView.getDX());
            buffer.putFloat(modelView.getDY());
            buffer.putFloat(modelView.getDZ());
            buffer.putFloat(modelView.getDW());
        }

        buffer.position(0);

        return buffer.asFloatBuffer();
    }
}
