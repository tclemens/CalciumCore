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
 * This class represents a vertex position variable in the shader program of a material
 *
 * @author Tim Clemens
 */
final class PositionProperty extends Property {

    /** The size of vertex positions */
    private static final int SIZE = 3;

    /** The stride of vertex positions in bytes */
    private static final int STRIDE = Float.SIZE / Byte.SIZE * SIZE;

    /** The name of the vertex position variable in the shader */
    private final String name;

    /** The buffer used to store vertex positions */
    private volatile FloatBuffer buffer;

    /**
     * @param name The name of the vertex position variable in the shader
     */
    PositionProperty(String name) {

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

        int positionHandle = GLES20.glGetAttribLocation(program, name);

        buffer = packVertexPositions(models);

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, SIZE, GLES20.GL_FLOAT, false, STRIDE, buffer);
    }

    @Override
    public void unload(int program) {

        int positionHandle = GLES20.glGetAttribLocation(program, name);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    /**
     * Pack the vertex positions into a buffer
     *
     * @param models The models to draw
     *
     * @return The packed vertex position buffer
     */
    private static FloatBuffer packVertexPositions(Collection<Model> models) {

        int size = 0;

        for (Model model : models) {

            size += model.getMesh().getPositions().size();
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(size * STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        for (Model model : models) {

            for (Vector3D position : model.getMesh().getPositions()) {

                buffer.putFloat(position.getX());
                buffer.putFloat(position.getY());
                buffer.putFloat(position.getZ());
            }
        }

        buffer.position(0);

        return buffer.asFloatBuffer();
    }
}
