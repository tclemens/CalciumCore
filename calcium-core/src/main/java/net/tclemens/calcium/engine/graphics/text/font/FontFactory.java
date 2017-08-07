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

package net.tclemens.calcium.engine.graphics.text.font;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.tclemens.calcium.engine.graphics.texture.atlas.Atlas;
import net.tclemens.calcium.engine.graphics.texture.atlas.AtlasFactory;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.engine.graphics.texture.region.RegionFactory;

/**
 * This class is responsible for creating and initializing fonts
 *
 * @author Tim Clemens
 */
public final class FontFactory {

    private FontFactory() {
    }

    /**
     * Create a font
     *
     * @param typeface The typeface of the font
     * @param symbols The symbols included in the font
     * @param size The scale of the font in pixels
     * @param padding The horizontal padding of the font in pixels
     *
     * @return The new font
     *
     * @throws IllegalArgumentException If the typeface, symbols, scale, or padding is invalid
     */
    @NonNull
    public static Font createFont(@NonNull Typeface typeface, @NonNull Collection<Character> symbols, float size, float padding) {

        if (typeface == null) {

            throw new IllegalArgumentException("Unable to create a font with a null typeface");
        }

        if (symbols == null) {

            throw new IllegalArgumentException("Unable to create a font with a null symbol collection");
        }

        if (symbols.contains(null)) {

            throw new IllegalArgumentException("Unable to create a font with null symbols");
        }

        if (size <= 0f) {

            throw new IllegalArgumentException("Unable to create a font with a zero or negative size");
        }

        if (padding < 0f) {

            throw new IllegalArgumentException("Unable to create a font with a negative padding");
        }

        Paint paint = new Paint();

        paint.setTypeface(typeface);
        paint.setTextSize(size);

        Atlas atlas = generateAtlas(paint, symbols);

        return buildFont(paint, atlas, padding);
    }

    /**
     *
     * Create a font
     *
     * @param paint The visual properties of the font
     * @param atlas The texture regions for each glyph of the font
     * @param padding The horizontal padding of the font int pixels
     *
     * @return The new font
     */
    static Font buildFont(Paint paint, Atlas atlas, float padding) {

        return new Font(paint, atlas, padding);
    }

    /**
     * Create a texture atlas for the font
     *
     * @param paint The visual properties of the font
     * @param symbols The symbols included in the font
     *
     * @return The texture atlas for the font
     */
    private static Atlas generateAtlas(Paint paint, Collection<Character> symbols) {

        float width = 0f;
        float height = paint.descent() - paint.ascent();

        for (Character symbol : symbols) {

            width = Math.max(width, paint.measureText(symbol.toString()));
        }

        int root = (int) Math.ceil(Math.sqrt(symbols.size()));
        int size = (int) Math.ceil(Math.max(width, height)) * root;
        int cell = 0;

        Map<String, Region> regions = new HashMap<>();

        for (Character symbol : symbols) {

            String name = symbol.toString();

            float x = (cell % root);
            float y = (cell / root);

            float bottom = y / root;
            float top = bottom + (height / size);
            float left = x / root;
            float right = left + (paint.measureText(name) / size);

            regions.put(name, RegionFactory.createRegion(bottom, top, left, right));

            cell++;
        }

        return AtlasFactory.createAtlas(regions, size);
    }
}
