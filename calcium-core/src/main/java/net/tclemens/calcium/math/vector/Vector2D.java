/*
 * Copyright (C) 2007 The Android Open Source Project
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
 *
 * Modifications Copyright (C) 2017 Tim Clemens
 */

package net.tclemens.calcium.math.vector;

import android.support.annotation.NonNull;

import net.tclemens.calcium.math.Geometry;

/**
 * This class represents an affine two-dimensional vector
 *
 * @author Tim Clemens
 */
public final class Vector2D {

    /** The <tt>x</tt> component of the vector */
    private final float x;

    /** The <tt>y</tt> component of the vector */
    private final float y;

    /** The <tt>w</tt> component of the vector */
    private final float w;

    /**
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param w The <tt>w</tt> component of the vector
     */
    Vector2D(float x, float y, float w) {

        this.x = x;
        this.y = y;
        this.w = w;
    }

    /**
     * Get the <tt>x</tt> component of the vector
     *
     * @return The <tt>x</tt> component of the vector
     */
    public final float getX() {

        return x;
    }

    /**
     * Get the <tt>y</tt> component of the vector
     *
     * @return The <tt>y</tt> component of the vector
     */
    public final float getY() {

        return y;
    }

    /**
     * Get the <tt>w</tt> component of the vector
     *
     * @return The <tt>w</tt> component of the vector
     */
    public final float getW() {

        return w;
    }

    /**
     * Get the length of the vector
     *
     * @return The length of the vector
     */
    public final float length() {

        return Geometry.distance2D(x, y);
    }

    /**
     * Calculate the dot product of two vectors
     *
     * @param vector The other vector
     *
     * @return The dot product of the vectors
     */
    public final float dot(@NonNull Vector2D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a dot product with a null vector");
        }

        return x * vector.x + y * vector.y;
    }

    /**
     * Calculate the scalar product of the vector
     *
     * @param factor The scale factor
     *
     * @return The scalar product of the vector
     */
    public final Vector2D multiply(float factor) {

        return VectorFactory.buildVector2D(this.x * factor, this.y * factor, w);
    }

    /**
     * Calculate the sum of the two vectors
     *
     * @param vector The other vector
     *
     * @return The sum of the vectors
     */
    public final Vector2D add(@NonNull Vector2D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a sum with a null vector");
        }

        return VectorFactory.buildVector2D(this.x + vector.x, this.y + vector.y, w);
    }

    /**
     * Calculate the difference of the two vectors
     *
     * @param vector The other vector
     *
     * @return The difference of the vectors
     */
    public final Vector2D subtract(@NonNull Vector2D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a difference with a null vector");
        }

        return VectorFactory.buildVector2D(this.x - vector.x, this.y - vector.y, w);
    }

    /**
     * Normalize the vector
     *
     * @return The normalized vector
     */
    public final Vector2D normalize() {

        float il = 1f / Geometry.distance2D(x, y);

        return VectorFactory.buildVector2D(x * il, y * il, w);
    }

    /**
     * Rotate the vector
     *
     * @param a The rotation angle in degrees
     *
     * @return The rotated vector
     */
    public final Vector2D rotate(float a) {

        a *= (float) Math.PI / 180f;

        float sa = (float) Math.sin(a);
        float ca = (float) Math.cos(a);

        float x = this.x * ca + this.y * -sa;
        float y = this.x * sa + this.y * ca;

        return VectorFactory.buildVector2D(x, y, w);
    }

    /**
     * Scale the vector
     *
     * @param x The <tt>x</tt> scale factor
     * @param y The <tt>y</tt> scale factor
     *
     * @return The scaled vector
     */
    public final Vector2D scale(float x, float y) {

        return VectorFactory.buildVector2D(this.x * x, this.y * y, w);
    }

    /**
     * Translate the vector
     *
     * @param x The <tt>x</tt> translation offset
     * @param y The <tt>y</tt> translation offset
     *
     * @return The translated vector
     */
    public final Vector2D translate(float x, float y) {

        return VectorFactory.buildVector2D(this.x + x, this.y + y, w);
    }

    /**
     * Convert the vector from two dimensions to three dimensions
     *
     * @return The new vector
     */
    public final Vector3D toVector3D() {

        return VectorFactory.createVector3D(x, y, 0f, w);
    }

    @Override
    public final boolean equals(Object object) {

        if (this == object) {

            return true;
        }

        if (object == null || getClass() != object.getClass()) {

            return false;
        }

        Vector2D other = (Vector2D) object;

        return (x == other.x &&
                y == other.y &&
                w == other.w);
    }

    @Override
    public final int hashCode() {

        int result = (x == 0f ? 0 : Float.floatToIntBits(x));

        result = 31 * result + (y == 0f ? 0 : Float.floatToIntBits(y));
        result = 31 * result + (w == 0f ? 0 : Float.floatToIntBits(w));

        return result;
    }
}
