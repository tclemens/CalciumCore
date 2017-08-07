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
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class represents a model index variable in the shader program of a material
 *
 * @author Tim Clemens
 */
final class ModelProperty extends Property {

    /** The size of the model index */
    private static final int SIZE = 1;

    /** The stride of model index in bytes */
    private static final int STRIDE = Float.SIZE / Byte.SIZE * SIZE;

    /** The name of the model index variable in the shader */
    private final String name;

    /** The buffer used to store the model index */
    private volatile FloatBuffer buffer;

    /**
     * @param name The name of the model index variable in the shader
     */
    ModelProperty(String name) {

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

        int modelHandle = GLES20.glGetAttribLocation(program, name);

        buffer = packModelIndices(models);

        GLES20.glEnableVertexAttribArray(modelHandle);
        GLES20.glVertexAttribPointer(modelHandle, SIZE, GLES20.GL_FLOAT, false, STRIDE, buffer);
    }

    @Override
    public void unload(int program) {

        int modelHandel = GLES20.glGetAttribLocation(program, name);

        GLES20.glDisableVertexAttribArray(modelHandel);
    }

    /**
     * Pack the model indices into a buffer
     *
     * @param models The models to draw
     *
     * @return The packed model index buffer
     */
    private static FloatBuffer packModelIndices(Collection<Model> models) {

        int size = 0;

        for (Model model : models) {

            size += model.getMesh().getPositions().size();
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(size * STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        int index = 0;

        for (Model model : models) {

            for (Vector3D position : model.getMesh().getPositions()) {

                buffer.putFloat(index);
            }

            index++;
        }

        buffer.position(0);

        return buffer.asFloatBuffer();
    }
}
