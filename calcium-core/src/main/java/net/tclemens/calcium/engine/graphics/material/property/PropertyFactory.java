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

import net.tclemens.calcium.engine.graphics.blend.Equation;
import net.tclemens.calcium.engine.graphics.blend.Function;
import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.color.ColorFactory;
import net.tclemens.calcium.engine.graphics.texture.Texture;

/**
 * This class is responsible for creating and initializing material properties
 *
 * @author Tim Clemens
 */
public final class PropertyFactory {

    private static final Color DEFAULT_COLOR = ColorFactory.createColor(255, 255, 255, 255);

    private PropertyFactory() {
    }

    /**
     * Create a blend property
     *
     * @param source The function used to sample the source
     * @param destination The function used to sample the destination
     * @param equation The equation used to blend the source and destination samples
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the sample functions or blend equation are invalid
     */
    @NonNull
    public static Property createBlend(@NonNull Function source,
                                       @NonNull Function destination,
                                       @NonNull Equation equation) {

        return buildBlend(source, destination, equation, DEFAULT_COLOR);
    }

    /**
     * Create a blend property with a constant color
     *
     * @param source The function used to sample the source
     * @param destination The function used to sample the destination
     * @param equation The equation used to blend the source and destination samples
     * @param color The constant color sample
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the sample functions, blend equation, or constant color are invalid
     */
    @NonNull
    public static Property createBlend(@NonNull Function source,
                                       @NonNull Function destination,
                                       @NonNull Equation equation,
                                       @NonNull Color color) {

        if (source == null) {

            throw new IllegalArgumentException("Unable to create a blend property with a null source function");
        }

        if (destination == null) {

            throw new IllegalArgumentException("Unable to create a blend property with a null destination function");
        }

        if (source == null) {

            throw new IllegalArgumentException("Unable to create a blend property with a null blend equation");
        }

        if (source == null) {

            throw new IllegalArgumentException("Unable to create a blend property with a null constant color");
        }

        return buildBlend(source, destination, equation, color);
    }

    /**
     * Create a model index property
     *
     * @param name The name of the model index variable in the shader
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name is invalid
     */
    public static Property createModel(@NonNull String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a model property with a null or empty name");
        }

        return buildModel(name);
    }

    /**
     * Create a model-view matrix property
     *
     * @param name The name of the model-view matrix variable in the shader
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name is invalid
     */
    @NonNull
    public static Property createModelView(@NonNull String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a model-view property with a null or empty name");
        }

        return buildModelView(name);
    }

    /**
     * Create a model-view-projection matrix property
     *
     * @param name The name of the model-view-projection matrix variable in the shader
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name is invalid
     */
    @NonNull
    public static Property createModelViewProjection(@NonNull String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a model-view-projection property with a null or empty name");
        }

        return buildModelViewProjection(name);
    }

    /**
     * Create a view matrix property
     *
     * @param name The name of the view matrix variable in the shader
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name is invalid
     */
    @NonNull
    public static Property createView(@NonNull String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a view property with a null or empty name");
        }

        return buildView(name);
    }

    /**
     * Create a projection matrix property
     *
     * @param name The name of the view matrix variable in the shader
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name is invalid
     */
    @NonNull
    public static Property createProjection(@NonNull String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a projection property with a null or empty name");
        }

        return buildProjection(name);
    }

    /**
     * Create a color property
     *
     * @param name The name of the color variable in the shader
     * @param color The color of the property
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name or color are invalid
     */
    @NonNull
    public static Property createColor(@NonNull String name, @NonNull Color color) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a color property with a null or empty name");
        }

        if (color == null) {

            throw new IllegalArgumentException("Unable to create a color property with a null color");
        }

        return buildColor(name, color);
    }

    /**
     * Create a depth test property
     *
     * @return The new property
     */
    @NonNull
    public static Property createDepth() {

        return buildDepth();
    }

    /**
     * Create a vertex position property
     *
     * @param name The name of the vertex position variable in the shader
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name is invalid
     */
    @NonNull
    public static Property createPosition(@NonNull String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a position property with a null or empty name");
        }

        return buildPosition(name);
    }

    /**
     * Create a texture sampler property
     *
     * @param name The name of the texture sampler variable in the shader
     * @param texture The texture to sample
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name or texture are invalid
     */
    @NonNull
    public static Property createTexture(@NonNull String name, @NonNull Texture texture) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create a texture property with a null or empty name");
        }

        if (texture == null) {

            throw new IllegalArgumentException("Unable to create a texture property with a null texture");
        }

        return buildTexture(name, texture);
    }

    /**
     * Create a blend property with a constant color without validation
     *
     * @param source The function used to sample the source
     * @param destination The function used to sample the destination
     * @param equation The equation used to blend the source and destination samples
     * @param color The constant color sample
     *
     * @return The new property
     */
    static Property buildBlend(Function source, Function destination, Equation equation, Color color) {

        return new BlendProperty(source, destination, equation, color);
    }

    /**
     * Create a model index property without validation
     *
     * @param name The name of the model index variable in the shader
     *
     * @return The new property
     */
    static Property buildModel( String name) {

        return new ModelProperty(name);
    }

    /**
     * Create a model-view matrix property without validation
     *
     * @param name The name of the model-view matrix variable in the shader
     *
     * @return The new property
     */
    static Property buildModelView(String name) {

        return new ModelViewProperty(name);
    }

    /**
     * Create a model-view-projection matrix property without validation
     *
     * @param name The name of the model-view-projection matrix variable in the shader
     *
     * @return The new property
     */
    static Property buildModelViewProjection(String name) {

        return new ModelViewProjectionProperty(name);
    }

    /**
     * Create a view matrix property without validation
     *
     * @param name The name of the view matrix variable in the shader
     *
     * @return The new property
     */
    static Property buildView(String name) {

        return new ViewProperty(name);
    }

    /**
     * Create a projection matrix property without validation
     *
     * @param name The name of the view matrix variable in the shader
     *
     * @return The new property
     */
    static Property buildProjection(String name) {

        return new ProjectionProperty(name);
    }

    /**
     * Create a color property without validation
     *
     * @param name The name of the color variable in the shader
     * @param color The color of the property
     *
     * @return The new property
     *
     * @throws IllegalArgumentException If the variable name or color are invalid
     */
    static Property buildColor(String name,  Color color) {

        return new ColorProperty(name, color);
    }

    /**
     * Create a depth test property without validation
     *
     * @return The new property
     */
    static Property buildDepth() {

        return new DepthProperty();
    }

    /**
     * Create a vertex position property without validation
     *
     * @param name The name of the vertex position variable in the shader
     *
     * @return The new property
     */
    static Property buildPosition(String name) {

        return new PositionProperty(name);
    }

    /**
     * Create a texture sampler property without validation
     *
     * @param name The name of the texture sampler variable in the shader
     * @param texture The texture to sample
     *
     * @return The new property
     */
    static Property buildTexture(String name, Texture texture) {

        return new TextureProperty(name, texture);
    }
}
