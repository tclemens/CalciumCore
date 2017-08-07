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

package net.tclemens.calcium.engine.graphics.material;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.material.program.Program;
import net.tclemens.calcium.engine.graphics.material.property.Property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class is responsible for creating and initializing materials
 *
 * @author Tim Clemens
 */
public final class MaterialFactory {

    private MaterialFactory() {
    }

    /**
     * Create a material
     *
     * @param program The shader program for the material
     * @param properties The properties of the material
     *
     * @return The new material
     *
     * @throws IllegalArgumentException If the shader program or properties are invalid
     */
    @NonNull
    public static Material createMaterial(@NonNull Program program, @NonNull Collection<Property> properties) {

        if (program == null) {

            throw new IllegalArgumentException("Unable to create a material with a null program");
        }

        if (properties == null) {

            throw new IllegalArgumentException("Unable to create a material with a null property collection");
        }

        if (properties.contains(null)) {

            throw new IllegalArgumentException("Unable to create a material with null properties");
        }

        properties = Collections.unmodifiableCollection(new ArrayList<>(properties));

        return buildMaterial(program, properties);
    }

    /**
     * Create a material without validation
     *
     * @param program The shader program for the material
     * @param properties The properties of the material
     *
     * @return The new material
     */
    static Material buildMaterial(Program program, Collection<Property> properties) {

        return new Material(program, properties);
    }
}
