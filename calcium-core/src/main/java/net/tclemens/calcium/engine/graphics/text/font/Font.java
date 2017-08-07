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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.engine.graphics.texture.atlas.Atlas;
import net.tclemens.calcium.engine.graphics.texture.region.Region;

/**
 * This class contains the typeset information required to format text in a given font
 *
 * @author Tim Clemens
 */
public final class Font {

    /** The visual properties of the font */
    private final Paint paint;

    /** The texture atlas to each glyph in the font */
    private final Atlas atlas;

    /** The horizontal padding of the font int pixels */
    private final float padding;

    /**
     * @param paint The visual properties of the font
     * @param atlas The texture regions for each glyph of the font
     * @param padding The horizontal padding of the font int pixels
     */
    Font(Paint paint, Atlas atlas, float padding) {

        this.paint = paint;
        this.atlas = atlas;
        this.padding = padding;
    }

    /**
     * Get the texture atlas to each glyph in the font
     *
     * @return The texture atlas to each glyph in the font
     */
    @NonNull
    public final Atlas getAtlas() {

        return atlas;
    }

    /**
     * Get the typeface of the font
     *
     * @return The typeface of the font
     */
    @NonNull
    public final Typeface getTypeface() {

        return paint.getTypeface();
    }

    /**
     * Get the scale of the font in pixels
     *
     * @return The scale of the font in pixels
     */
    public final float getScale() {

        return paint.getTextSize();
    }

    /**
     * Get the width of the specified symbols in pixels
     *
     * @param symbols The specified symbol
     *
     * @return The width of the specified symbols in pixels
     */
    public final float getWidth(String symbols) {

        float width = 0f;

        for (Character symbol : symbols.toCharArray()) {

            width += getWidth(symbol);
        }

        return width;
    }

    /**
     * Get the width of the specified symbol in pixels
     *
     * @param symbol The specified symbol
     *
     * @return The width of the specified symbol in pixels
     */
    public final float getWidth(Character symbol) {

        Region glyph = atlas.getRegion(symbol.toString());

        return glyph.getWidth() * atlas.getSize();
    }

    /**
     * Get the height of the font in pixels
     *
     * @return The height of the font in pixels
     */
    public final float getHeight() {

        return paint.descent() - paint.ascent();
    }

    /**
     * Get the ascent of the font in pixels
     *
     * @return The ascent of the font in pixels
     */
    public final float getAscent() {

        return -paint.ascent();
    }

    /**
     * Get the descent of the font in pixels
     *
     * @return The descent of the font in pixels
     */
    public final float getDescent() {

        return paint.descent();
    }

    /**
     * Get the padding of the font in pixels
     *
     * @return The padding of the font in pixels
     */
    public final float getPadding() {

        return padding;
    }
}
