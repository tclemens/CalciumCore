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

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for loading and unloading shaders in graphics memory
 *
 * @author Tim Clemens
 */
public final class ShaderCache {

    /** The handles for each shader */
    private static final Map<Integer, Integer> shaders = new HashMap<>();

    private ShaderCache() {
    }

    /**
     * Unload each shader from graphics memory
     */
    public static void clear() {

        for (int shaderHandle : shaders.values()) {

            GLES20.glDeleteShader(shaderHandle);
        }

        shaders.clear();
    }

    /**
     * Load the shader into graphics memory if it is not loaded
     *
     * @param type The type of the shader
     * @param code The code for the shader
     *
     * @return The shader handle
     */
    static int load(int type, String code) {

        int hash = computeHash(type, code);

        if (shaders.containsKey(hash)) {

            int shaderHandle = shaders.get(hash);

            if (GLES20.glIsShader(shaderHandle)) {

                return shaderHandle;
            }
        }

        int shaderHandle = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shaderHandle, code);
        GLES20.glCompileShader(shaderHandle);

        shaders.put(hash, shaderHandle);

        return shaderHandle;
    }

    /**
     * Unload the shader from graphics memory
     *
     * @param type The type of the shader
     * @param code The code for the shader
     */
    static void unload(int type, String code) {

        int hash = computeHash(type, code);

        if (shaders.containsKey(hash)) {

            int shaderHandle = shaders.get(hash);

            GLES20.glDeleteShader(shaderHandle);

            shaders.remove(hash);
        }
    }

    /**
     * Compute the hash of the shader
     *
     * @param type The type of the shader
     * @param code The code for the shader
     *
     * @return The hash of the shader
     */
    private static int computeHash(int type, String code) {

        return 31 * type + (code == null ? 0 : code.hashCode());
    }
}
