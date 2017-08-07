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

/**
 * This class is responsible for loading and unloading a shader
 *
 * @author Tim Clemens
 */
public final class Shader {

    /** The type of the shader */
    private final int type;

    /** The code for the shader */
    private final String code;

    /**
     * @param type The type of the shader
     * @param code The code for the shader
     */
    Shader(int type, String code) {

        this.type = type;
        this.code = code;
    }

    /**
     * Load the shader
     *
     * @return The shader handle
     */
    public final int load() {

        return ShaderCache.load(type, code);
    }

    /**
     * Unload the shader
     */
    public final void unload() {

        ShaderCache.unload(type, code);
    }
}
