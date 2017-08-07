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
import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class represents a color variable in the shader program of a material
 *
 * @author Tim Clemens
 */
final class ColorProperty extends Property {

    /** The number of components in the color */
    private static final int SIZE = 4;

    /** The stride of the color in bytes */
    private static final int STRIDE = Float.SIZE / Byte.SIZE * SIZE;

    /** The name of the color variable in the shader */
    private final String name;

    /** The color of the property */
    private final Color color;

    /** The buffer used to store the color */
    private volatile FloatBuffer buffer;

    /**
     * @param name The name of the color variable in the shader
     * @param color The color of the property
     */
    ColorProperty(String name, Color color) {

        this.name = name;
        this.color = color;
    }

    @Override
    public void load(int program, @NonNull Camera camera, @NonNull Collection<Model> models) {

        if (camera == null) {

            throw new IllegalArgumentException("Unable to load a property with a null camera");
        }

        if (models == null || models.isEmpty()) {

            throw new IllegalArgumentException("Unable to load a property with a null or empty model collection");
        }

        int colorHandle = GLES20.glGetUniformLocation(program, name);

        buffer = packColor(color);

        GLES20.glUniform4fv(colorHandle, 1, buffer);
    }

    @Override
    public void unload(int program) {
    }

    /**
     * Pack the color into a buffer
     *
     * @param color The color to pack
     *
     * @return The packed color buffer
     */
    private static FloatBuffer packColor(Color color) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(STRIDE);

        buffer.order(ByteOrder.nativeOrder());

        buffer.putFloat(color.getRed());
        buffer.putFloat(color.getGreen());
        buffer.putFloat(color.getBlue());
        buffer.putFloat(color.getAlpha());

        buffer.position(0);

        return buffer.asFloatBuffer();
    }
}
