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

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.util.HashMap;
import java.util.Map;

import net.tclemens.calcium.engine.graphics.texture.image.Image;

/**
 * This class is responsible for loading and unloading texture images in graphics memory
 *
 * @author Tim Clemens
 */
public final class TextureCache {

    /** The handles for each texture */
    private static final Map<Integer, Integer> textures = new HashMap<>();

    /** The loaded mipmap levels for each texture */
    private static final Map<Integer, Integer> mipmaps = new HashMap<>();

    private TextureCache() {
    }

    /**
     * Unload each texture image from graphics memory
     */
    public static void clear() {

        for (int handle : textures.values()) {

            int[] handles = new int[] { handle };

            GLES20.glDeleteTextures(1, handles, 0);
        }

        textures.clear();
        mipmaps.clear();
    }

    /**
     * Load the specified mipmap levels from the texture image into graphics memory if they are not loaded
     *
     * @param image The texture image
     * @param levels the mipmap levels to load from the texture image
     *
     * @return The handle to the texture
     */
    static int load(Image image, int levels) {

        int hash = image.hashCode();
        int limit = levels < image.getLevels() ? levels : image.getLevels();

        if (textures.containsKey(hash)) {

            int handle = textures.get(hash);

            if (GLES20.glIsTexture(handle)) {

                load(image, mipmaps.get(hash), limit);

                mipmaps.put(hash, limit);

                return handle;
            }
        }

        int[] handles = new int[1];

        GLES20.glGenTextures(1, handles, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, handles[0]);

        load(image, 0, limit);

        textures.put(hash, handles[0]);
        mipmaps.put(hash, limit);

        return handles[0];
    }

    /**
     * Unload the specified texture image from graphics memory
     *
     * @param image The texture image
     */
    static void unload(Image image) {

        int hash = image.hashCode();

        if (textures.containsKey(hash)) {

            int[] handles = new int[] { textures.get(hash) };

            GLES20.glDeleteTextures(1, handles, 0);

            textures.remove(hash);
            mipmaps.remove(hash);
        }
    }

    /**
     * Load each unloaded mipmap level from the texture image into graphics memory
     *
     * @param image The texture image
     * @param start The first level to load from the texture image
     * @param limit The last level to load from the texture image
     */
    private static void load(Image image, int start, int limit) {

        for (int level = start; level < limit; level++) {

            Bitmap bitmap = image.getLevel(level);

            if (bitmap != null) {

                GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, level, invertBitmap(bitmap), 0);
            }
        }
    }

    /**
     * Create a vertical inversion of the bitmap
     *
     * @param bitmap The bitmap to invert
     *
     * @return The inverted bitmap
     */
    private static Bitmap invertBitmap(Bitmap bitmap) {

        Matrix matrix = new Matrix();
        matrix.preScale(1f, -1f);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }
}
