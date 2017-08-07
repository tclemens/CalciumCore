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

package net.tclemens.calcium.engine.graphics.material.shader;

import android.opengl.GLES20;
import android.support.annotation.NonNull;

/**
 * This class is responsible for creating and initializing shaders
 *
 * @author Tim Clemens
 */
public final class ShaderFactory {

    private ShaderFactory() {
    }

    /**
     * Create a vertex shader
     *
     * @param code The code for the vertex shader
     *
     * @return The new shader
     *
     * @throws IllegalArgumentException If the shader code is invalid
     */
    @NonNull
    public static Shader createVertex(@NonNull String code) {

        if (code == null || code.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a vertex shader with null or empty code");
        }

        return buildShader(GLES20.GL_VERTEX_SHADER, code);
    }

    /**
     * Create a fragment shader
     *
     * @param code The code for the fragment shader
     *
     * @return The new shader
     *
     * @throws IllegalArgumentException If the shader code is invalid
     */
    @NonNull
    public static Shader createFragment(@NonNull String code) {

        if (code == null || code.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a fragment shader with null or empty code");
        }

        return buildShader(GLES20.GL_FRAGMENT_SHADER, code);
    }

    /**
     * Create a shader without validation
     *
     * @param code The code for the fragment shader
     *
     * @return The new shader
     */
    static Shader buildShader(int type, String code) {

        return new Shader(type, code);
    }
}
