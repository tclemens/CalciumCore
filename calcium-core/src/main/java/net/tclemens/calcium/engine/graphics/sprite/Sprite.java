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

package net.tclemens.calcium.engine.graphics.sprite;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

import net.tclemens.calcium.engine.graphics.mesh.Mesh;
import net.tclemens.calcium.engine.graphics.mesh.MeshFactory;
import net.tclemens.calcium.engine.graphics.texture.region.Region;
import net.tclemens.calcium.math.vector.Vector2D;
import net.tclemens.calcium.math.vector.Vector3D;
import net.tclemens.calcium.math.vector.VectorFactory;

/**
 * This class represents a "billboard" sprite
 *
 * @author Tim Clemens
 */
public final class Sprite {

    /** The position of the sprite */
    private final Vector3D position;

    /** The texture region of the sprite */
    private final Region region;

    /** The width of the sprite */
    private final float width;

    /** The height of the sprite */
    private final float height;

    /**
     * @param position The position of the sprite
     * @param region The texture region of the sprite
     * @param width The width of the sprite
     * @param height The height of the sprite
     */
    Sprite(Vector3D position, Region region, float width, float height) {

        this.position = position;
        this.region = region;
        this.width = width;
        this.height = height;
    }

    /**
     * Get the position of the sprite
     *
     * @return The position of the sprite
     */
    @NonNull
    public final Vector3D getPosition() {

        return position;
    }

    /**
     * Get the texture region of the sprite
     *
     * @return The texture region of the sprite
     */
    @NonNull
    public final Region getRegion() {

        return region;
    }

    /**
     * Get the width of the sprite
     *
     * @return The width of the sprite
     */
    public final float getWidth() {

        return width;
    }

    /**
     * Get the height of the sprite
     *
     * @return The height of the sprite
     */
    public final float getHeight() {

        return height;
    }

    /**
     * Change the position of the sprite
     *
     * @param position The position of the sprite
     *
     * @return The modified sprite
     */
    @NonNull
    public final Sprite setPosition(@NonNull Vector3D position) {

        return SpriteFactory.createSprite(position, region, width, height);
    }

    /**
     * Change the texture region of the sprite
     *
     * @param region The texture region of the sprite
     *
     * @return The modified sprite
     */
    @NonNull
    public final Sprite setRegion(@NonNull Region region) {

        return SpriteFactory.createSprite(position, region, width, height);
    }

    /**
     * Change the width of the sprite
     *
     * @param width The width of the sprite
     *
     * @return The modified sprite
     */
    @NonNull
    public final Sprite setWidth(float width) {

        return SpriteFactory.createSprite(position, region, width, height);
    }

    /**
     * Set the height of the sprite
     *
     * @param height The height of the sprite
     *
     * @return The modified sprite
     */
    @NonNull
    public final Sprite setHeight(float height) {

        return SpriteFactory.createSprite(position, region, width, height);
    }

    /**
     * Convert the sprite to a drawable mesh
     *
     * @return The drawable mesh
     */
    @NonNull
    public final Mesh toMesh() {

        Collection<Vector3D> positions = new ArrayList<>(4);
        Collection<Vector2D> coordinates = new ArrayList<>(4);
        Collection<Integer> indices = new ArrayList<>(6);

        float left = position.getX() - (width / 2f);
        float right = left + width;
        float bottom = position.getY() - (height / 2f);
        float top = bottom + height;

        positions.add(VectorFactory.createPosition3D(left, bottom, position.getZ()));
        positions.add(VectorFactory.createPosition3D(right, bottom, position.getZ()));
        positions.add(VectorFactory.createPosition3D(right, top, position.getZ()));
        positions.add(VectorFactory.createPosition3D(left, top, position.getZ()));

        coordinates.add(VectorFactory.createPosition2D(region.getLeft(), region.getBottom()));
        coordinates.add(VectorFactory.createPosition2D(region.getRight(), region.getBottom()));
        coordinates.add(VectorFactory.createPosition2D(region.getRight(), region.getTop()));
        coordinates.add(VectorFactory.createPosition2D(region.getLeft(), region.getTop()));

        indices.add(0);
        indices.add(1);
        indices.add(2);
        indices.add(0);
        indices.add(2);
        indices.add(3);

        return MeshFactory.createMesh(positions, coordinates, indices);
    }
}
