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

package net.tclemens.calcium.engine.graphics.texture;

import net.tclemens.calcium.engine.graphics.texture.image.Image;
import net.tclemens.calcium.engine.graphics.texture.parameter.Filter;
import net.tclemens.calcium.engine.graphics.texture.parameter.Wrapping;

/**
 * This class is responsible for creating and initializing textures
 *
 * @author Tim Clemens
 */
public final class TextureFactory {

    private TextureFactory() {
    }

    /**
     * Create a texture
     *
     * @param image The image of the texture
     * @param minification The minification filter for the texture
     * @param magnification The magnification filter for the texture
     * @param horizontal The horizontal wrapping for the texture
     * @param vertical The vertical wrapping for the texture
     *
     * @return The new texture
     *
     * @throws IllegalArgumentException If the texture image, filters, or wrappings are invalid
     */
    public static Texture createTexture(Image image,
                                        Filter minification,
                                        Filter magnification,
                                        Wrapping horizontal,
                                        Wrapping vertical) {

        if (image == null) {

            throw new IllegalArgumentException("Unable to create a texture with a null image");
        }

        if (minification == null) {

            throw new IllegalArgumentException("Unable to create a texture with a null minification filter");
        }

        if (magnification == null) {

            throw new IllegalArgumentException("Unable to create a texture with a null magnification filter");
        }

        if (horizontal == null) {

            throw new IllegalArgumentException("Unable to create a texture with a null horizontal wrapping");
        }

        if (vertical == null) {

            throw new IllegalArgumentException("Unable to create a texture with a null vertical wrapping");
        }

        return buildTexture(image, minification, magnification, horizontal, vertical);
    }

    /**
     * Create a texture
     *
     * @param image The image of the texture
     * @param minification The minification filter for the texture
     * @param magnification The magnification filter for the texture
     * @param horizontal The horizontal wrapping for the texture
     * @param vertical The vertical wrapping for the texture
     *
     * @return The new texture
     */
    static Texture buildTexture(Image image,
                                Filter minification,
                                Filter magnification,
                                Wrapping horizontal,
                                Wrapping vertical) {

        return new Texture(image, minification, magnification, horizontal, vertical);
    }
}
