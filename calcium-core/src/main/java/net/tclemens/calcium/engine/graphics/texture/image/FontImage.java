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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.text.font.Font;
import net.tclemens.calcium.engine.graphics.texture.atlas.Atlas;
import net.tclemens.calcium.engine.graphics.texture.region.Region;

/**
 * This class represents a font image
 */
final class FontImage extends Image {

    /** The font for the image */
    private final Font font;

    /** The color of the font for the image */
    private final Color color;

    /** The number of mipmap levels for the image */
    private final int levels;

    /**
     * @param font The font for the image
     * @param color The color of the font for the image
     * @param levels The number of mipmap levels for the image
     */
    FontImage(Font font, Color color, int levels) {

        this.font = font;
        this.color = color;
        this.levels = levels;
    }

    @Override
    public final int getLevels() {

        return levels;
    }

    @NonNull
    @Override
    public final Bitmap getLevel(int level) {

        Atlas atlas = font.getAtlas();
        Paint paint = new Paint();

        float scale = (levels - level) / (1f + level);

        paint.setAntiAlias(true);
        paint.setLinearText(true);
        paint.setFilterBitmap(true);
        paint.setTextSize(font.getScale() * scale);
        paint.setTypeface(font.getTypeface());
        paint.setColor(color.toARGB());

        int size = (int) (Math.ceil(atlas.getSize()) * scale);

        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        bitmap.eraseColor(0);

        float descent = paint.descent();

        for (String name : atlas.getNames()) {

            Region region = atlas.getRegion(name);

            float x = atlas.getSize() * region.getLeft();
            float y = atlas.getSize() - (atlas.getSize() * region.getBottom()) - descent;

            canvas.drawText(name, x, y, paint);
        }

        return bitmap;
    }

    @Override
    public final int hashCode() {

        int result = font == null ? 0 : font.hashCode();

        result = 31 * result + (color == null ? 0 : color.hashCode());
        result = 31 * result + levels;

        return result;
    }
}
