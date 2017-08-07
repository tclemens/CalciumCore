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

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.texture.image.Image;
import net.tclemens.calcium.engine.graphics.texture.parameter.Filter;
import net.tclemens.calcium.engine.graphics.texture.parameter.Wrapping;

/**
 * This class is responsible for loading and unloading a texture image and it's parameters
 *
 * @author Tim Clemens
 */
public final class Texture {

    /** The image of the texture */
    private final Image image;

    /** The minification filter for the texture */
    private final Filter minification;

    /** The magnification filter for the texture */
    private final Filter magnification;

    /** The horizontal wrapping for the texture */
    private final Wrapping horizontal;

    /** The vertical wrapping for the texture */
    private final Wrapping vertical;

    /**
     * @param image The image of the texture
     * @param minification The minification filter for the texture
     * @param magnification The magnification filter for the texture
     * @param horizontal The horizontal wrapping for the texture
     * @param vertical The vertical wrapping for the texture
     */
    Texture(Image image,
            Filter minification,
            Filter magnification,
            Wrapping horizontal,
            Wrapping vertical) {

        this.image = image;
        this.minification = minification;
        this.magnification = magnification;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    /**
     * Get the image of the texture
     *
     * @return The image of the texture
     */
    @NonNull
    public final Image getImage() {

        return image;
    }

    /**
     * Get the minification filter for the texture
     *
     * @return The minification filter for the texture
     */
    @NonNull
    public final Filter getMinification() {

        return minification;
    }

    /**
     * Get the magnification filter for the texture
     *
     * @return The magnification filter for the texture
     */
    @NonNull
    public final Filter getMagnification() {

        return magnification;
    }

    /**
     * Get the horizontal wrapping for the texture
     *
     * @return The horizontal wrapping for the texture
     */
    @NonNull
    public final Wrapping getHorizontal() {

        return horizontal;
    }

    /**
     * Get the vertical wrapping for the texture
     *
     * @return The vertical wrapping for the texture
     */
    @NonNull
    public final Wrapping getVertical() {

        return vertical;
    }

    /**
     * Load the texture image and it's parameters
     *
     * @return The texture handle
     */
    public final int load() {

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        int levels = minification == Filter.LINEAR || magnification == Filter.LINEAR ? image.getLevels() : 1;
        int handle = TextureCache.load(image, levels);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, handle);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, minification.toInteger());
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, magnification.toInteger());

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, horizontal.toInteger());
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, vertical.toInteger());

        return handle;
    }

    /**
     * Unload the texture image and it's parameters
     */
    public final void unload() {

        TextureCache.unload(image);
    }
}
