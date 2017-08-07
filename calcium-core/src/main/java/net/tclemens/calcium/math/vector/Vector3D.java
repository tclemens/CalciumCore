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
 * This class represents an affine three-dimensional vector
 *
 * @author Tim Clemens
 */
public final class Vector3D {

    /** The <tt>x</tt> component of the vector */
    private final float x;

    /** The <tt>y</tt> component of the vector */
    private final float y;

    /** The <tt>z</tt> component of the vector */
    private final float z;

    /** The <tt>w</tt> component of the vector */
    private final float w;

    /**
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param z The <tt>z</tt> component of the vector
     * @param w The <tt>w</tt> component of the vector
     */
    Vector3D(float x, float y, float z, float w) {

        this.x = x;
        this.y = y;
        this.z = z;
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
     * Get the <tt>z</tt> component of the vector
     *
     * @return The <tt>z</tt> component of the vector
     */
    public final float getZ() {

        return z;
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

        return Geometry.distance3D(x, y, z);
    }

    /**
     * Calculate the dot product of two vectors
     *
     * @param vector The right-hand vector
     *
     * @return The dot product of the vectors
     */
    public final float dot(@NonNull Vector3D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a dot product with a null vector");
        }

        return x * vector.x + y * vector.y + z * vector.z;
    }

    /**
     * Calculate the cross product of two vectors
     *
     * @param vector The right-hand vector
     *
     * @return The cross product of the vectors
     */
    public final Vector3D cross(@NonNull Vector3D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a cross product with a null vector");
        }

        float rx = this.y * vector.z - this.z * vector.y;
        float ry = this.z * vector.x - this.x * vector.z;
        float rz = this.x * vector.y - this.y * vector.x;

        return VectorFactory.buildVector3D(rx, ry, rz, w);
    }

    /**
     * Calculate the scalar product of the vector
     *
     * @param factor The scale factor
     *
     * @return The scalar product of the vector
     */
    public final Vector3D multiply(float factor) {

        return VectorFactory.buildVector3D(this.x * factor, this.y * factor, this.z * factor, w);
    }

    /**
     * Calculate the sum of the two vectors
     *
     * @param vector The right-hand vector
     *
     * @return The sum of the vectors
     */
    public final Vector3D add(@NonNull Vector3D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a sum with a null vector");
        }

        return VectorFactory.buildVector3D(this.x + vector.x, this.y + vector.y, this.z + vector.z, w);
    }

    /**
     * Calculate the difference of the two vectors
     *
     * @param vector The right-hand vector
     *
     * @return The difference of the vectors
     */
    public final Vector3D subtract(@NonNull Vector3D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a difference with a null vector");
        }

        return VectorFactory.buildVector3D(this.x - vector.x, this.y - vector.y, this.z - vector.z, w);
    }

    /**
     * Normalize the vector
     *
     * @return The normalized vector
     */
    public final Vector3D normalize() {

        float il = 1f / Geometry.distance3D(x, y, z);

        return VectorFactory.buildVector3D(x * il, y * il, z * il, w);
    }

    /**
     * Rotate the vector
     *
     * @param x The <tt>x</tt> rotation angle in degrees
     * @param y The <tt>y</tt> rotation angle in degrees
     * @param z The <tt>z</tt> rotation angle in degrees
     *
     * @return The rotated vector
     */
    public final Vector3D rotate(float x, float y, float z) {

        float a = (float) Math.PI / 180f;

        float xa = x * a;
        float ya = y * a;
        float za = z * a;

        float cxa = (float) Math.cos(xa);
        float cya = (float) Math.cos(ya);
        float cza = (float) Math.cos(za);

        float sxa = (float) Math.sin(xa);
        float sya = (float) Math.sin(ya);
        float sza = (float) Math.sin(za);

        float ax = cya * cza;
        float ay = -cya * sza;
        float az = sya;

        float bx = cxa * sya * cza + cxa * sza;
        float by = -cxa * sya * sza + cxa * cza;
        float bz = -sxa * cya;

        float cx = -sxa * sya * cza + sxa * sza;
        float cy = sxa * sya * sza + sxa * cza;
        float cz = cxa * cya;

        float rx = ax * this.x + bx * this.y + cx * this.z;
        float ry = ay * this.x + by * this.y + cy * this.z;
        float rz = az * this.x + bz * this.y + cz * this.z;

        return VectorFactory.buildVector3D(rx, ry, rz, w);
    }

    /**
     * Scale the vector
     *
     * @param x The <tt>x</tt> scale factor
     * @param y The <tt>x</tt> scale factor
     * @param z The <tt>z</tt> scale factor
     *
     * @return The scaled vector
     */
    public final Vector3D scale(float x, float y, float z) {

        return VectorFactory.buildVector3D(this.x * x, this.y * y, this.z * z, w);
    }

    /**
     * Translate the vector
     *
     * @param x The <tt>x</tt> translation offset
     * @param y The <tt>x</tt> translation offset
     * @param z The <tt>z</tt> translation offset
     *
     * @return The translated vector
     */
    public final Vector3D translate(float x, float y, float z) {

        return VectorFactory.buildVector3D(this.x + x, this.y + y, this.z + z, w);
    }

    @Override
    public final boolean equals(Object object) {

        if (this == object) {

            return true;
        }

        if (object == null || getClass() != object.getClass()) {

            return false;
        }

        Vector3D other = (Vector3D) object;

        return (x == other.x &&
                y == other.y &&
                z == other.z &&
                w == other.w);
    }

    @Override
    public final int hashCode() {

        int result = (x == 0f ? 0 : Float.floatToIntBits(x));

        result = 31 * result + (y == 0f ? 0 : Float.floatToIntBits(y));
        result = 31 * result + (z == 0f ? 0 : Float.floatToIntBits(z));
        result = 31 * result + (w == 0f ? 0 : Float.floatToIntBits(w));

        return result;
    }
}
