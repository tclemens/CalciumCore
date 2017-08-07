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

package net.tclemens.calcium.engine.graphics.material.property;

import android.support.annotation.NonNull;

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.camera.Camera;
import net.tclemens.calcium.engine.graphics.model.Model;

/**
 * This class represents a property of a material
 *
 * @author Tim Clemens
 */
public abstract class Property {

    Property() {
    }

    /**
     * Load the property
     *
     * @param program The handle to the shader program of the material
     * @param camera The camera used to draw the models
     * @param models The models to draw
     */
    public abstract void load(int program, @NonNull Camera camera, @NonNull Collection<Model> models);

    /**
     * Unload the property
     *
     * @param program The handle to the shader program of the material
     */
    public abstract void unload(int program);
}
