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
import net.tclemens.calcium.engine.graphics.mesh.Mesh;
import net.tclemens.calcium.engine.graphics.model.Model;
import net.tclemens.calcium.engine.graphics.texture.Texture;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.math.vector.Vector2D;

/**
 * This class represents a texture sampler variable in the shader program of a material
 *
 * @author Tim Clemens
 */
final class TextureProperty extends Property {

    /** The size of texture coordinates */
    private static final int SIZE = 2;

    /** The stride of texture coordinates in bytes */
    private static final int STRIDE = Float.SIZE / Byte.SIZE * SIZE;

    /** The name of the texture sampler variable in the shader */
    private final String name;

    /** The texture to sample for the property */
    private final Texture texture;

    /** The buffer used to store the texture coordinates */
    private volatile FloatBuffer buffer;

    /**
     * @param name The name of the texture sampler variable in the shader
     * @param texture The texture to sample for the property
     */
    TextureProperty(String name, Texture texture) {

        this.name = name;
        this.texture = texture;
    }

    @Override
    public final void load(int program, @NonNull Camera camera, @NonNull Collection<Model> models) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to load a property with a null camera");
        }

        if (models == null || models.isEmpty()) {

            throw new IllegalArgumentException("Unable to load a property with a null or empty model collection");
        }

        int textureCoordinatesHandle = GLES20.glGetAttribLocation(program, name);

        texture.load();

        buffer = packTextureCoordinates(models);

        GLES20.glEnableVertexAttribArray(textureCoordinatesHandle);
        GLES20.glVertexAttribPointer(textureCoordinatesHandle, SIZE, GLES20.GL_FLOAT, false, STRIDE, buffer);
    }

    @Override
    public final void unload(int program) {

        int textureCoordinatesHandle = GLES20.glGetAttribLocation(program, name);

        GLES20.glDisableVertexAttribArray(textureCoordinatesHandle);
    }

    /**
     * Pack the texture coordinates into a buffer
     *
     * @param models The models to draw
     *
     * @return The packed texture coordinate buffer
     */
    private static FloatBuffer packTextureCoordinates(Collection<Model> models) {

        int size = 0;

        for (Model model : models) {

            size += model.getMesh().getCoordinates().size();
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(size * STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        for (Model model : models) {

            Mesh mesh = model.getMesh();
            Region region = model.getRegion();

            for (Vector2D coordinates : mesh.getCoordinates()) {

                float u = region.getLeft() + (coordinates.getX() * region.getWidth());
                float v = region.getBottom() + (coordinates.getY() * region.getHeight());

                buffer.putFloat(u);
                buffer.putFloat(v);
            }
        }

        buffer.position(0);

        return buffer.asFloatBuffer();
    }
}
