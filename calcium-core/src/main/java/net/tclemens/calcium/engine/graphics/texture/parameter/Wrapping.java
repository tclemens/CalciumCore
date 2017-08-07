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

package net.tclemens.calcium.engine.graphics.texture.parameter;

import android.opengl.GLES20;

/**
 * This enumeration represents the texture wrap parameters available in OpenGL
 *
 * @author Tim Clemens
 */
public enum Wrapping {

    /** Discard the integer part of the texture coordinate */
    REPEAT(GLES20.GL_REPEAT),

    /** Clamp the texture coordinates to the range (0, 1) */
    STRETCH(GLES20.GL_CLAMP_TO_EDGE);

    /** The equivalent constant in OpenGL */
    private final int constant;

    /**
     * @param constant The equivalent constant in OpenGL
     */
    Wrapping(int constant) {

        this.constant = constant;
    }

    /**
     * Convert the texture wrap enumeration to an equivalent constant in OpenGL
     *
     * @return The equivalent constant in OpenGL
     */
    public final int toInteger() {

        return constant;
    }
}
