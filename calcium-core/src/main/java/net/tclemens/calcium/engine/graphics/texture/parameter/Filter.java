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
 * This enumeration represents the texture filter parameters available in OpenGL
 *
 * @author Tim Clemens
 */
public enum Filter {

    /** Use the weighted average of the four nearest texels */
    LINEAR(GLES20.GL_LINEAR),

    /** Use the value of nearest texel */
    NEAREST(GLES20.GL_NEAREST);

    /** The equivalent constant in OpenGL */
    private final int constant;

    /**
     * @param constant The equivalent constant in OpenGL
     */
    Filter(int constant) {

        this.constant = constant;
    }

    /**
     * Convert the texture filter enumeration to an equivalent constant in OpenGL
     *
     * @return The equivalent constant in OpenGL
     */
    public final int toInteger() {

        return constant;
    }
}
