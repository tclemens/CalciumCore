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

package net.tclemens.calcium.engine.graphics.material;

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Collection;

import net.tclemens.calcium.engine.graphics.camera.Camera;
import net.tclemens.calcium.engine.graphics.material.program.Program;
import net.tclemens.calcium.engine.graphics.material.property.Property;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class represents a material used to draw a collection of models
 *
 * @author Tim Clemens
 */
public final class Material {

    /** The stride of each index in bytes */
    private static final int STRIDE = Integer.SIZE / Byte.SIZE;

    /** The shader program for the material */
    private final Program program;

    /** The properties of the material */
    private final Collection<Property> properties;

    /** The buffer used to store the vertex indices */
    private volatile IntBuffer buffer;

    /**
     * @param program The shader program for the material
     * @param properties The properties of the material
     */
    Material(Program program, Collection<Property> properties) {

        this.program = program;
        this.properties = properties;
    }

    /**
     * Get the shader program for the material
     *
     * @return The shader program for the material
     */
    @NonNull
    public final Program getProgram() {

        return program;
    }

    /**
     * Get the properties of the material
     *
     * @return The properties of the material
     */
    @NonNull
    public final Collection<Property> getProperties() {

        return properties;
    }

    /**
     * Draw the specified models using the specified camera
     *
     * @param camera The camera used to draw the models
     * @param models The models to draw
     *
     * @throws IllegalArgumentException If the camera or models are invalid
     */
    public final void draw(@NonNull Camera camera, @NonNull Collection<Model> models) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to draw a material with a null camera");
        }

        if (models == null || models.isEmpty()) {

            throw new IllegalArgumentException("Unable to draw a material with a null or empty model collection");
        }

        int programHandle = program.load();

        GLES20.glUseProgram(programHandle);

        for (Property property : properties) {

            property.load(programHandle, camera, models);
        }

        buffer = packIndices(models);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, buffer.limit(), GLES20.GL_UNSIGNED_INT, buffer);

        for (Property property : properties) {

            property.unload(programHandle);
        }
    }

    /**
     * Pack the indices of each model into a buffer
     *
     * @param models The models to draw with the material
     *
     * @return The packed index buffer
     */
    private static IntBuffer packIndices(Collection<Model> models) {

        int size = 0;

        for (Model model : models) {

            size += model.getMesh().getIndices().size();
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(size * STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        int offset = 0;

        for (Model model : models) {

            Collection<Integer> indices = model.getMesh().getIndices();

            for (int index : indices) {

                buffer.putInt(index + offset);
            }

            offset += model.getMesh().getPositions().size();
        }

        buffer.position(0);

        return buffer.asIntBuffer();
    }
}
