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
 * This enumeration represents the blend functions available in OpenGL
 *
 * @author Tim Clemens
 */
public enum Function {

    /** Use the red green and blue components from the specified constant */
    CONSTANT_COLOR(GLES20.GL_CONSTANT_COLOR),

    /** Use the alpha component from the specified constant */
    CONSTANT_ALPHA(GLES20.GL_CONSTANT_ALPHA),

    /** Use the compliment of the red green and blue components from the specified constant */
    COMLIEMENT_OF_CONSTANT_COLOR(GLES20.GL_ONE_MINUS_CONSTANT_COLOR),

    /** Use the compliment of the alpha component from the specified constant */
    COMPLIMENT_OF_CONSTANT_COLOR(GLES20.GL_ONE_MINUS_CONSTANT_ALPHA),

    /** Use the red green and blue components from the destination */
    DESTINATION_COLOR(GLES20.GL_DST_COLOR),

    /** Use the alpha component from the destination */
    DESTINATION_ALPHA(GLES20.GL_DST_ALPHA),

    /** Use the compliment of the red green and blue components from the destination */
    COMPLIMENT_OF_DESTINATION_COLOR(GLES20.GL_ONE_MINUS_DST_COLOR),

    /** Use the compliment of the alpha component from the destination */
    COMPLIMENT_OF_DESTINATION_ALPHA(GLES20.GL_ONE_MINUS_DST_ALPHA),

    /** Use the red green and blue components from the source */
    SOURCE_COLOR(GLES20.GL_SRC_COLOR),

    /** Use the alpha component from the source */
    SOURCE_ALPHA(GLES20.GL_SRC_ALPHA),

    /** Use the compliment of the red green and blue components from the source */
    COMPLIMENT_OF_SOURCE_COLOR(GLES20.GL_ONE_MINUS_SRC_COLOR),

    /** Use the compliment of the alpha component from the source */
    COMPLIMENT_OF_SOURCE_ALPHA(GLES20.GL_ONE_MINUS_SRC_ALPHA);

    /** The equivalent constant in OpenGL */
    private final int constant;

    /**
     * @param constant The equivalent constant in OpenGL
     */
    Function(int constant) {

        this.constant = constant;
    }

    /**
     * Convert the blend function enumeration to an equivalent constant in OpenGL
     *
     * @return The equivalent constant in OpenGL
     */
    public final int toInteger() {

        return constant;
    }
}
