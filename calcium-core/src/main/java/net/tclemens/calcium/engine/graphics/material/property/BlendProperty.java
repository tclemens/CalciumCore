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

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.blend.Equation;
import net.tclemens.calcium.engine.graphics.blend.Function;
import net.tclemens.calcium.engine.graphics.camera.Camera;
import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class represents the use of blending for a material
 *
 * @author Tim Clemens
 */
final class BlendProperty extends Property {

    /** The function used to sample the source */
    private final Function source;

    /** The function used to sample the destination */
    private final Function destination;

    /** The equation used to blend the source and destination samples */
    private final Equation equation;

    /** The constant color sample */
    private final Color color;

    /**
     * @param source The function used to sample the source
     * @param destination The function used to sample the destination
     * @param equation The equation used to blend the source and destination samples
     * @param color The constant color sample
     */
    BlendProperty(Function source, Function destination, Equation equation, Color color) {

        this.source = source;
        this.destination = destination;
        this.equation = equation;
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

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(source.toInteger(), destination.toInteger());
        GLES20.glBlendEquation(equation.toInteger());
        GLES20.glBlendColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    @Override
    public void unload(int program) {

        GLES20.glDisable(GLES20.GL_BLEND);
    }
}
