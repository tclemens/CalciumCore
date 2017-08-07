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
 * This class is responsible for loading and unloading a shader program
 *
 * @author Tim Clemens
 */
public final class Program {

    /** The vertex shader for the program */
    private final Shader vertex;

    /** The fragment shader for the program */
    private final Shader fragment;

    /**
     * @param vertex The vertex shader for the program
     * @param fragment The fragment shader for the program
     */
    Program(Shader vertex, Shader fragment) {

        this.vertex = vertex;
        this.fragment = fragment;
    }

    /**
     * Get the vertex shader for the program
     *
     * @return The vertex shader for the program
     */
    @NonNull
    public final Shader getVertexShader() {

        return vertex;
    }

    /**
     * Get the fragment shader for the program
     *
     * @return The fragment shader for the program
     */
    @NonNull
    public final Shader getFragmentShader() {

        return fragment;
    }

    /**
     * Load the shader program
     *
     * @return The program handle
     */
    public final int load() {

        return ProgramCache.load(vertex, fragment);
    }

    /**
     * Unload the shader program
     */
    public final void unload() {

        ProgramCache.unload(vertex, fragment);
    }
}
