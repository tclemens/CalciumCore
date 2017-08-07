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

package net.tclemens.calcium.math.vector;

/**
 * This class is responsible for creating and initializing vectors
 *
 * @author Tim Clemens
 */
public final class VectorFactory {

    private VectorFactory() {
    }

    /**
     * Create an affine two-dimensional vector with a <tt>w</tt> component of zero
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     *
     * @return The new vector
     */
    public static Vector2D createDirection2D(float x, float y) {

        return buildVector2D(x, y, 0f);
    }

    /**
     * Create an affine three-dimensional vector with a <tt>w</tt> component of zero
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param z The <tt>z</tt> component of the vector
     *
     * @return The new vector
     */
    public static Vector3D createDirection3D(float x, float y, float z) {

        return buildVector3D(x, y, z, 0f);
    }

    /**
     * Create an affine two-dimensional vector with a <tt>w</tt> component of one
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     *
     * @return The new vector
     */
    public static Vector2D createPosition2D(float x, float y) {

        return buildVector2D(x, y, 1f);
    }

    /**
     * Create an affine three-dimensional vector with a <tt>w</tt> component of one
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param z The <tt>z</tt> component of the vector
     *
     * @return The new vector
     */
    public static Vector3D createPosition3D(float x, float y, float z) {

        return buildVector3D(x, y, z, 1f);
    }

    /**
     * Create an affine two-dimensional vector with the specified <tt>w</tt> component
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param w The <tt>w</tt> component of the vector
     *
     * @return The new vector
     */
    public static Vector2D createVector2D(float x, float y, float w) {

        return buildVector2D(x, y, w);
    }

    /**
     * Create an affine three-dimensional vector with the specified <tt>w</tt> component
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param z The <tt>z</tt> component of the vector
     * @param w The <tt>w</tt> component of the vector
     *
     * @return The new vector
     */
    public static Vector3D createVector3D(float x, float y, float z, float w) {

        return buildVector3D(x, y, z, w);
    }

    /**
     * Create an affine two-dimensional vector with the specified <tt>w</tt> component
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param w The <tt>w</tt> component of the vector
     *
     * @return The new vector
     */
    static Vector2D buildVector2D(float x, float y, float w) {

        return new Vector2D(x, y, w);
    }

    /**
     * Create an affine three-dimensional vector with the specified <tt>w</tt> component
     *
     * @param x The <tt>x</tt> component of the vector
     * @param y The <tt>y</tt> component of the vector
     * @param z The <tt>z</tt> component of the vector
     * @param w The <tt>w</tt> component of the vector
     *
     * @return The new vector
     */
    static Vector3D buildVector3D(float x, float y, float z, float w) {

        return new Vector3D(x, y, z, w);
    }
}
