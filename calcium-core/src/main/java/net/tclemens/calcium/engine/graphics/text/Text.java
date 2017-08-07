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

package net.tclemens.calcium.engine.graphics.text;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

import net.tclemens.calcium.engine.graphics.mesh.Mesh;
import net.tclemens.calcium.engine.graphics.mesh.MeshFactory;
import net.tclemens.calcium.engine.graphics.text.font.Font;
import net.tclemens.calcium.engine.graphics.texture.atlas.Atlas;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.math.vector.Vector2D;
import net.tclemens.calcium.math.vector.Vector3D;
import net.tclemens.calcium.math.vector.VectorFactory;

/**
 * This class represents "billboard" text
 *
 * @author Tim Clemens
 */
public final class Text {

    /** The position of the text */
    private final Vector3D position;

    /** The font of the text */
    private final Font font;

    /** The symbols of the text */
    private final String symbols;

    /** The height of the text */
    private final float height;

    /**
     * @param position The position of the text
     * @param font The font of the text
     * @param symbols The symbols of the text
     * @param height The height of the text
     */
    Text(Vector3D position, Font font, String symbols, float height) {

        this.position = position;
        this.font = font;
        this.symbols = symbols;
        this.height = height;
    }

    /**
     * Get the position of the text
     *
     * @return The position of the text
     */
    @NonNull
    public final Vector3D getPosition() {

        return position;
    }

    /**
     * Get the font of the text
     *
     * @return The font of the text
     */
    @NonNull
    public final Font getFont() {

        return font;
    }

    /**
     * Get the symbols of the text
     *
     * @return The symbols of the text
     */
    @NonNull
    public final String getSymbols() {

        return symbols;
    }

    /**
     * Get the height of the text
     *
     * @return The height of the text
     */
    public final float getHeight() {

        return height;
    }

    /**
     * Get the width of the text
     *
     * @return The width of the text
     */
    public final float getWidth() {

        return height * font.getWidth(symbols) / font.getHeight();
    }

    /**
     * Change the position of the text
     *
     * @param position The position of the text
     *
     * @return The modified text
     *
     * @throws IllegalArgumentException If the position is invalid
     */
    @NonNull
    public final Text setPosition(@NonNull Vector3D position) {

        return TextFactory.createText(position, font, symbols, height);
    }

    /**
     * Change the font of the text
     *
     * @param font The font of the text
     *
     * @return The modified text
     *
     * @throws IllegalArgumentException If the font is invalid
     */
    @NonNull
    public final Text setFont(@NonNull Font font) {

        return TextFactory.createText(position, font, symbols, height);
    }

    /**
     * Change the symbols of the text
     *
     * @param symbols The symbols of the text
     *
     * @return The modified text
     *
     * @throws IllegalArgumentException If the symbols are invalid
     */
    @NonNull
    public final Text setSymbols(@NonNull String symbols) {

        return TextFactory.createText(position, font, symbols, height);
    }

    /**
     * Change the height of the text
     *
     * @param height The height of the text
     *
     * @return The modified text
     *
     * @throws IllegalArgumentException If the height is invalid
     */
    @NonNull
    public final Text setHeight(float height) {

        return TextFactory.createText(position, font, symbols, height);
    }

    /**
     * Change the width of the text
     *
     * @param width The width of the text
     *
     * @return The modified text
     *
     * @throws IllegalArgumentException If the height is invalid
     */
    @NonNull
    public final Text setWidth(float width) {

        float height = width * font.getHeight() / font.getWidth(symbols);

        return TextFactory.createText(position, font, symbols, height);
    }

    /**
     * Convert the text to a drawable mesh
     *
     * @return The drawable mesh
     */
    @NonNull
    public final Mesh toMesh() {

        Collection<Vector3D> positions = new ArrayList<>(symbols.length() * 4);
        Collection<Vector2D> coordinates = new ArrayList<>(symbols.length() * 4);
        Collection<Integer> indices = new ArrayList<>(symbols.length() * 6);

        float scale = height / font.getHeight();

        float left = position.getX();
        float bottom = position.getY() - font.getDescent() * scale;

        Atlas atlas = font.getAtlas();

        for (Character symbol : symbols.toCharArray()) {

            indices.add(positions.size());
            indices.add(positions.size() + 1);
            indices.add(positions.size() + 2);
            indices.add(positions.size());
            indices.add(positions.size() + 2);
            indices.add(positions.size() + 3);

            float right = left + font.getWidth(symbol) * scale;
            float top = bottom + height;

            positions.add(VectorFactory.createPosition3D(left, bottom, position.getZ()));
            positions.add(VectorFactory.createPosition3D(right, bottom, position.getZ()));
            positions.add(VectorFactory.createPosition3D(right, top, position.getZ()));
            positions.add(VectorFactory.createPosition3D(left, top, position.getZ()));

            Region glyph = atlas.getRegion(symbol.toString());

            coordinates.add(VectorFactory.createPosition2D(glyph.getLeft(), glyph.getBottom()));
            coordinates.add(VectorFactory.createPosition2D(glyph.getRight(), glyph.getBottom()));
            coordinates.add(VectorFactory.createPosition2D(glyph.getRight(), glyph.getTop()));
            coordinates.add(VectorFactory.createPosition2D(glyph.getLeft(), glyph.getTop()));

            left = right + font.getPadding();
        }

        return MeshFactory.createMesh(positions, coordinates, indices);
    }
}
