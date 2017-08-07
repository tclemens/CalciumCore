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

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * This class represents an image with one or more mipmap levels which may be loaded into a texture
 *
 * @author Tim Clemens
 * @version 1.0
 */
public abstract class Image {

    Image() {
    }

    /**
     * Get the number of mipmap levels for the image
     *
     * @return The number of mipmap levels for the image
     */
    public abstract int getLevels();

    /**
     * Get the specified mipmap level of the image
     *
     * @param level The mipmap level
     *
     * @return The bitmap of the image at the specified level
     */
    @NonNull
    public abstract Bitmap getLevel(int level);
}
