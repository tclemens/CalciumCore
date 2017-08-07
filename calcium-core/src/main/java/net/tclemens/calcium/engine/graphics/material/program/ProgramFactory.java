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

package net.tclemens.calcium.engine.graphics.material.program;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.material.shader.Shader;

/**
 * This class is responsible for creating and initializing shader programs
 *
 * @author Tim Clemens
 */
public final class ProgramFactory {

    private ProgramFactory() {
    }

    /**
     * Create a shader program
     *
     * @param vertex The vertex shader for the program
     * @param fragment The fragment shader for the program
     *
     * @return The shader program
     *
     * @throws IllegalArgumentException If the vertex or fragment shader is invalid
     */
    @NonNull
    public static Program createProgram(@NonNull Shader vertex, @NonNull Shader fragment) {

        if (vertex == null) {

            throw new IllegalArgumentException("Unable to create a program with a null vertex shader");
        }

        if (fragment == null) {

            throw new IllegalArgumentException("Unable to create a program with a null fragment shader");
        }

        return buildProgram(vertex, fragment);
    }

    /**
     * Create a shader program without validation
     *
     * @param vertex The vertex shader for the program
     * @param fragment The fragment shader for the program
     *
     * @return The shader program
     */
    static Program buildProgram(Shader vertex, Shader fragment) {

        return new Program(vertex, fragment);
    }
}
