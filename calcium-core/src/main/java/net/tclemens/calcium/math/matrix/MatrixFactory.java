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

package net.tclemens.calcium.math.matrix;

import android.support.annotation.NonNull;

import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class is responsible for creating and initializing matrices
 *
 * @author Tim Clemens
 */
public final class MatrixFactory {

    /** The affine two dimensional idenity matrix */
    private static final Matrix2D IDENTITY_2D = buildMatrix2D(
            1f, 0f, 0f,
            0f, 1f, 0f,
            0f, 0f, 1f);

    /** The affine three dimensional idenity matrix */
    private static final Matrix3D IDENTITY_3D = buildMatrix3D(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            0f, 0f, 0f, 1f);

    private MatrixFactory() {
    }

    /**
     * Create an affine two-dimensional identity matrix
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix2D createIdentity2D() {

        return IDENTITY_2D;
    }

    /**
     * Create an affine three-dimensional identity matrix
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix3D createIdentity3D() {

        return IDENTITY_3D;
    }

    /**
     * Create an affine three-dimensional view matrix
     *
     * @param eye The position the camera
     * @param center The focal point of the camera
     * @param up The upward orientation from the origin of the camera
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix3D createView3D(@NonNull Vector3D eye,
                                        @NonNull Vector3D center,
                                        @NonNull Vector3D up) {

        if (eye == null) {

            throw new IllegalArgumentException("Unable to create a view matrix with a null eye vector");
        }

        if (center == null) {

            throw new IllegalArgumentException("Unable to create a view matrix with a null center vector");
        }

        if (up == null) {

            throw new IllegalArgumentException("Unable to create a view matrix with a null up vector");
        }

        // TODO Perform these operations locally to avoid excessive allocations

        Vector3D f = center.subtract(eye).normalize();
        Vector3D s = f.cross(up).normalize();
        Vector3D u = s.cross(f);

        float ax =  s.getX();
        float ay =  u.getX();
        float az = -f.getX();

        float bx =  s.getY();
        float by =  u.getY();
        float bz = -f.getY();

        float cx =  s.getZ();
        float cy =  u.getZ();
        float cz = -f.getZ();

        float x = -eye.getX();
        float y = -eye.getY();
        float z = -eye.getZ();

        float dx = ax * x + bx * y + cx * z;
        float dy = ay * x + by * y + cy * z;
        float dz = az * x + bz * y + cz * z;

        return buildMatrix3D(
                ax, bx, cx, dx,
                ay, by, cy, dy,
                az, bz, cz, dz,
                0f, 0f, 0f, 1f);
    }

    /**
     * Create an affine three-dimensional orthographic projection matrix
     *
     * @param left The left boundary of the projection
     * @param right The right boundary of the projection
     * @param bottom The bottom boundary of the projection
     * @param top The top boundary of the projection
     * @param near The near boundary of the projection
     * @param far The far boundary of the projection
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix3D createOrthographic3D(float left, float right,
                                                float bottom, float top,
                                                float near, float far) {

        float width = right - left;
        float height = top - bottom;
        float depth = far - near;

        if (width == 0f) {

            throw new IllegalArgumentException("Unable to create a projection with zero width");
        }

        if (height == 0f) {

            throw new IllegalArgumentException("Unable to create a projection with zero height");
        }

        if (depth == 0f) {

            throw new IllegalArgumentException("Unable to create a projection with zero depth");
        }

        float iw = 1f / width;
        float ih = 1f / height;
        float id = 1f / depth;

        float ax =  2f * iw;
        float by =  2f * ih;
        float cz = -2f * id;

        float dx = -(right + left) * iw;
        float dy = -(top + bottom) * ih;
        float dz = -(far + near) * id;

        return buildMatrix3D(
                ax, 0f, 0f, dx,
                0f, by, 0f, dy,
                0f, 0f, cz, dz,
                0f, 0f, 0f, 1f);
    }

    /**
     * Create an affine three-dimensional perspective projection matrix
     *
     * @param fov The field of view of the perspective
     * @param aspect The aspect ratio of the perspective
     * @param near The near boundary of the perspective
     * @param far The far boundary of the perspective
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix3D createPerspective3D(float fov, float aspect, float near, float far) {

        float it = 1f / (float) Math.tan(fov * (Math.PI / 360d));
        float id = 1f / (near - far);

        float ax = it / aspect;
        float cz = id * (far + near);
        float dz = id * far * near * 2f;

        return buildMatrix3D(
                ax, 0f,  0f, 0f,
                0f, it,  0f, 0f,
                0f, 0f,  cz, dz,
                0f, 0f, -1f, 0f);
    }

    /**
     * Create an affine two-dimensional rotation matrix
     *
     * @param a The rotation angle
     *
     * @return the new matrix
     */
    @NonNull
    public static Matrix2D createRotate2D(float a) {

        a *= (float) Math.PI / 180f;

        float sa = (float) Math.sin(a);
        float ca = (float) Math.cos(a);

        return buildMatrix2D(
                ca, -sa, 0f,
                sa,  ca, 0f,
                0f,  0f, 1f);
    }

    /**
     * Create an affine three-dimensional rotation matrix
     *
     * @param x The <tt>x</tt> rotation angle
     * @param y The <tt>y</tt> rotation angle
     * @param z The <tt>z</tt> rotation angle
     *
     * @return the new matrix
     */
    @NonNull
    public static Matrix3D createRotate3D(float x, float y, float z) {

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

        return buildMatrix3D(
                ax, bx, cx, 0f,
                ay, by, cy, 0f,
                az, bz, cz, 0f,
                0f, 0f, 0f, 1f);
    }

    /**
     * Create an affine three-dimensional scale matrix
     *
     * @param x The <tt>x</tt> scale factor
     * @param y The <tt>y</tt> scale factor
     *
     * @return the new matrix
     */
    @NonNull
    public static Matrix2D createScale2D(float x, float y) {

        return buildMatrix2D(
                x,  0f, 0f,
                0f, y,  0f,
                0f, 0f, 1f);
    }

    /**
     * Create an affine three-dimensional scale matrix
     *
     * @param x The <tt>x</tt> scale factor
     * @param y The <tt>y</tt> scale factor
     * @param z The <tt>z</tt> scale factor
     *
     * @return the new matrix
     */
    @NonNull
    public static Matrix3D createScale3D(float x, float y, float z) {

        return buildMatrix3D(
                x,  0f, 0f, 0f,
                0f, y,  0f, 0f,
                0f, 0f, z,  0f,
                0f, 0f, 0f, 1f);
    }

    /**
     * Create an affine two-dimensional translation matrix
     *
     * @param x The <tt>x</tt> translation offset
     * @param y The <tt>y</tt> translation offset
     *
     * @return the new matrix
     */
    @NonNull
    public static Matrix2D createTranslate2D(float x, float y) {

        return buildMatrix2D(
                1f, 0f, x,
                0f, 1f, y,
                0f, 0f, 1f);
    }

    /**
     * Create an affine three-dimensional translation matrix
     *
     * @param x The <tt>x</tt> translation offset
     * @param y The <tt>y</tt> translation offset
     * @param z The <tt>z</tt> translation offset
     *
     * @return the new matrix
     */
    @NonNull
    public static Matrix3D createTranslate3D(float x, float y, float z) {

        return buildMatrix3D(
                1f, 0f, 0f, x,
                0f, 1f, 0f, y,
                0f, 0f, 1f, z,
                0f, 0f, 0f, 1f);
    }

    /**
     * Create an affine two-dimensional matrix
     *
     * @param ax The <tt>x</tt> component of the left vector
     * @param bx The <tt>x</tt> component of the left-middle vector
     * @param cx The <tt>x</tt> component of the right-middle vector
     * @param ay The <tt>y</tt> component of the left vector
     * @param by The <tt>y</tt> component of the left-middle vector
     * @param cy The <tt>y</tt> component of the right-middle vector
     * @param aw The <tt>w</tt> component of the left vector
     * @param bw The <tt>w</tt> component of the left-middle vector
     * @param cw The <tt>w</tt> component of the right-middle vector
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix2D createMatrix2D(float ax, float bx, float cx,
                                          float ay, float by, float cy,
                                          float aw, float bw, float cw) {

        return buildMatrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }

    /**
     * Create an affine three-dimensional matrix
     *
     * @param ax The <tt>x</tt> component of the left vector
     * @param bx The <tt>x</tt> component of the left-middle vector
     * @param cx The <tt>x</tt> component of the right-middle vector
     * @param dx The <tt>x</tt> component of the right vector
     * @param ay The <tt>y</tt> component of the left vector
     * @param by The <tt>y</tt> component of the left-middle vector
     * @param cy The <tt>y</tt> component of the right-middle vector
     * @param dy The <tt>y</tt> component of the right vector
     * @param az The <tt>z</tt> component of the left vector
     * @param bz The <tt>z</tt> component of the left-middle vector
     * @param cz The <tt>z</tt> component of the right-middle vector
     * @param dz The <tt>z</tt> component of the right vector
     * @param aw The <tt>w</tt> component of the left vector
     * @param bw The <tt>w</tt> component of the left-middle vector
     * @param cw The <tt>w</tt> component of the right-middle vector
     * @param dw The <tt>w</tt> component of the right vector
     *
     * @return The new matrix
     */
    @NonNull
    public static Matrix3D createMatrix3D(float ax, float bx, float cx, float dx,
                                          float ay, float by, float cy, float dy,
                                          float az, float bz, float cz, float dz,
                                          float aw, float bw, float cw, float dw) {

        return buildMatrix3D(
                ax, bx, cx, dx,
                ay, by, cy, dy,
                az, bz, cz, dz,
                aw, bw, cw, dw);
    }

    /**
     * Create an affine two-dimensional matrix
     *
     * @param ax The <tt>x</tt> component of the left vector
     * @param bx The <tt>x</tt> component of the left-middle vector
     * @param cx The <tt>x</tt> component of the right-middle vector
     * @param ay The <tt>y</tt> component of the left vector
     * @param by The <tt>y</tt> component of the left-middle vector
     * @param cy The <tt>y</tt> component of the right-middle vector
     * @param aw The <tt>w</tt> component of the left vector
     * @param bw The <tt>w</tt> component of the left-middle vector
     * @param cw The <tt>w</tt> component of the right-middle vector
     *
     * @return The new matrix
     */
    static Matrix2D buildMatrix2D(float ax, float bx, float cx,
                                  float ay, float by, float cy,
                                  float aw, float bw, float cw) {

        return new Matrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }

    /**
     * Create an affine three-dimensional matrix
     *
     * @param ax The <tt>x</tt> component of the left vector
     * @param bx The <tt>x</tt> component of the left-middle vector
     * @param cx The <tt>x</tt> component of the right-middle vector
     * @param dx The <tt>x</tt> component of the right vector
     * @param ay The <tt>y</tt> component of the left vector
     * @param by The <tt>y</tt> component of the left-middle vector
     * @param cy The <tt>y</tt> component of the right-middle vector
     * @param dy The <tt>y</tt> component of the right vector
     * @param az The <tt>z</tt> component of the left vector
     * @param bz The <tt>z</tt> component of the left-middle vector
     * @param cz The <tt>z</tt> component of the right-middle vector
     * @param dz The <tt>z</tt> component of the right vector
     * @param aw The <tt>w</tt> component of the left vector
     * @param bw The <tt>w</tt> component of the left-middle vector
     * @param cw The <tt>w</tt> component of the right-middle vector
     * @param dw The <tt>w</tt> component of the right vector
     *
     * @return The new matrix
     */
    static Matrix3D buildMatrix3D(float ax, float bx, float cx, float dx,
                                  float ay, float by, float cy, float dy,
                                  float az, float bz, float cz, float dz,
                                  float aw, float bw, float cw, float dw) {

        return new Matrix3D(
                ax, bx, cx, dx,
                ay, by, cy, dy,
                az, bz, cz, dz,
                aw, bw, cw, dw);
    }
}
