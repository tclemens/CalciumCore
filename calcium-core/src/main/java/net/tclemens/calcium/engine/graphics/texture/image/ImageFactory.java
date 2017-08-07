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

package net.tclemens.calcium.engine.graphics.texture.image;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import java.util.List;

import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.text.font.Font;

/**
 * This class is responsible for creating and initializing images
 *
 * @author Tim Clemens
 * @version 1.0
 */
public final class ImageFactory {

    private ImageFactory() {
    }

    /**
     * Create a raster image
     *
     * @param assets The asset manager with access to the image
     * @param paths The path of each mipmap in the image
     *
     * @return The new image
     *
     * @throws IllegalArgumentException If the asset manager, or paths are invalid
     */
    @NonNull
    public static Image createRaster(@NonNull AssetManager assets, @NonNull List<String> paths) {

        if (assets == null) {

            throw new IllegalArgumentException("Unable to create an image with a null asset manager");
        }

        if (paths == null || paths.isEmpty()) {

            throw new IllegalArgumentException("Unable to create an image with a null or empty path collection");
        }

        return buildRaster(assets, paths);
    }

    /**
     * Create a font image
     *
     * @param font The font for the image
     * @param color The color of the font for the image
     * @param levels The number of mipmap levels for the image
     *
     * @return The new image
     *
     * @throws IllegalArgumentException If the font, color, or mipmap levels are invalid
     */
    @NonNull
    public static Image createFont(@NonNull Font font, @NonNull Color color, int levels) {

        if (font == null) {

            throw new IllegalArgumentException("Unable to create an image with a null font");
        }

        if (color == null) {

            throw new IllegalArgumentException("Unable to create an image with a null color");
        }

        if (levels <= 0) {

            throw new IllegalArgumentException("Unable to create an image with zero or negative mipmap levels");
        }

        return buildFont(font, color, levels);
    }

    /**
     * Create a raster image
     *
     * @param assets The asset manager with access to the image
     * @param paths The path of each mipmap in the image
     *
     * @return The new image
     */
    static Image buildRaster(AssetManager assets, List<String> paths) {

        return new RasterImage(assets, paths);
    }

    /**
     * Create a font image
     *
     * @param font The font for the image
     * @param color The color of the font for the image
     * @param levels The number of mipmap levels for the image
     *
     * @return The new image
     */
    static Image buildFont(Font font, Color color, int levels) {

        return new FontImage(font, color, levels);
    }
}
