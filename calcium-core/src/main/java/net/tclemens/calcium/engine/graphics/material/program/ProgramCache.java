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

import android.opengl.GLES20;

import java.util.HashMap;
import java.util.Map;

import net.tclemens.calcium.engine.graphics.material.shader.Shader;

/**
 * This class is responsible for loading and unloading shader programs in graphics memory
 *
 * @author Tim Clemens
 */
public final class ProgramCache {

    /** The handles for each program */
    private static final Map<Integer, Integer> programs = new HashMap<>();

    private ProgramCache() {
    }

    /**
     * Unload each shader program from graphics memory
     */
    public static void clear() {

        for (int programHandle : programs.values()) {

            GLES20.glDeleteProgram(programHandle);
        }

        programs.clear();
    }

    /**
     * Load the shader program into graphics memory if it is not loaded
     *
     * @param vertex The vertex shader for the program
     * @param fragment The fragment shader for the program
     *
     * @return The program handle
     */
    static int load(Shader vertex, Shader fragment) {

        int hash = computeHash(vertex, fragment);

        if (programs.containsKey(hash)) {

            int programHandle = programs.get(hash);

            if (GLES20.glIsProgram(programHandle)) {

                return programHandle;
            }
        }

        int vertexHandle = vertex.load();
        int fragmentHandle = fragment.load();
        int programHandle = GLES20.glCreateProgram();

        GLES20.glAttachShader(programHandle, vertexHandle);
        GLES20.glAttachShader(programHandle, fragmentHandle);

        GLES20.glLinkProgram(programHandle);

        GLES20.glDetachShader(programHandle, vertexHandle);
        GLES20.glDetachShader(programHandle, fragmentHandle);

        programs.put(hash, programHandle);

        return programHandle;
    }

    /**
     * Unload the shader program from graphics memory
     *
     * @param vertex The vertex shader for the program
     * @param fragment The fragment shader for the program
     */
    static void unload(Shader vertex, Shader fragment) {

        int hash = computeHash(vertex, fragment);

        if (programs.containsKey(hash)) {

            int programHandle = programs.get(hash);

            GLES20.glDeleteProgram(programHandle);

            programs.remove(hash);
        }
    }

    /**
     * Compute the hash of the program
     *
     * @param vertex The vertex shader of the program
     * @param fragment The fragment shader of the program
     *
     * @return The hash of the program
     */
    private static int computeHash(Shader vertex, Shader fragment) {

        int result = vertex == null ? 0 : vertex.hashCode();

        result = 31 * result + (fragment == null ? 0 : fragment.hashCode());

        return result;
    }
}
