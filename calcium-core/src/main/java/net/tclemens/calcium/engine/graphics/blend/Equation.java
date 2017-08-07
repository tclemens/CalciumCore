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

package net.tclemens.calcium.engine.graphics.blend;

import android.opengl.GLES20;

/**
 * This enumeration represents the blend equations available in OpenGL
 *
 * @author Tim Clemens
 */
public enum Equation {

    /** Add the source to the destination */
    ADD_DESTINATION_TO_SOURCE(GLES20.GL_FUNC_ADD),

    /** Subtract the destination from the source */
    SUBTRACT_DESTINATION_FROM_SOURCE(GLES20.GL_FUNC_SUBTRACT),

    /** Subtract the source from the destination */
    SUBTRACT_SOURCE_FROM_DESTINATION(GLES20.GL_FUNC_REVERSE_SUBTRACT);

    /** The equivalent constant in OpenGL */
    private final int constant;

    /**
     * @param constant The equivalent constant in OpenGL
     */
    Equation(int constant) {

        this.constant = constant;
    }

    /**
     * Convert the blend equation enumeration to an equivalent constant in OpenGL
     *
     * @return The equivalent constant in OpenGL
     */
    public final int toInteger() {

        return constant;
    }
}
