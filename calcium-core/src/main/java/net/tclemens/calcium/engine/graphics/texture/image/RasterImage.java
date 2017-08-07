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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * This class represents a raster image
 *
 * @author Tim Clemens
 * @version 1.0
 */
final class RasterImage extends Image {

    /** The asset manager with access to the image */
    private final AssetManager assets;

    /** The path of each mipmap in the image */
    private final List<String> paths;

    /**
     * @param assets The asset manager with access to the image
     * @param paths The path of each mipmap in the image
     */
    RasterImage(AssetManager assets, List<String> paths) {

        this.assets = assets;
        this.paths = paths;
    }

    @Override
    public final int getLevels() {

        return paths.size();
    }

    @NonNull
    @Override
    public final Bitmap getLevel(int level) {

        if (level >= 0 && level < paths.size()) {

            try {

                String path = paths.get(level);
                InputStream stream = assets.open(path);

                return BitmapFactory.decodeStream(stream);
            }
            catch (IOException ignored) {
            }
        }

        return null;
    }

    @Override
    public final int hashCode() {

        int result = assets == null ? 0 : assets.hashCode();

        result = 31 * result + (paths == null || paths.isEmpty() ? 0 : paths.get(0).hashCode());

        return result;
    }
}
