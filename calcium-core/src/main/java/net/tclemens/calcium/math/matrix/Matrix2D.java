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

import net.tclemens.calcium.math.vector.Vector2D;
import net.tclemens.calcium.math.vector.VectorFactory;

/**
 * This class represents an affine two-dimensional matrix
 *
 * @author Tim Clemens
 */
public final class Matrix2D {

    /** The <tt>x</tt> component of the left vector */
    private final float ax;

    /** The <tt>y</tt> component of the left vector */
    private final float ay;

    /** The <tt>w</tt> component of the left vector */
    private final float aw;

    /** The <tt>x</tt> component of the left-middle vector */
    private final float bx;

    /** The <tt>y</tt> component of the left-middle vector */
    private final float by;

    /** The <tt>w</tt> component of the left-middle vector */
    private final float bw;

    /** The <tt>x</tt> component of the right-middle vector */
    private final float cx;

    /** The <tt>y</tt> component of the right-middle vector */
    private final float cy;

    /** The <tt>w</tt> component of the right-middle vector */
    private final float cw;

    /**
     * @param ax The <tt>x</tt> component of the left vector
     * @param bx The <tt>x</tt> component of the left-middle vector
     * @param cx The <tt>x</tt> component of the right-middle vector
     * @param ay The <tt>y</tt> component of the left vector
     * @param by The <tt>y</tt> component of the left-middle vector
     * @param cy The <tt>y</tt> component of the right-middle vector
     * @param aw The <tt>w</tt> component of the left vector
     * @param bw The <tt>w</tt> component of the left-middle vector
     * @param cw The <tt>w</tt> component of the right-middle vector
     */
    Matrix2D(float ax, float bx, float cx,
             float ay, float by, float cy,
             float aw, float bw, float cw) {

        this.ax = ax;
        this.ay = ay;
        this.aw = aw;

        this.bx = bx;
        this.by = by;
        this.bw = bw;

        this.cx = cx;
        this.cy = cy;
        this.cw = cw;
    }

    /**
     * Get the <tt>x</tt> component of the left vector
     *
     * @return The <tt>x</tt> component of the left vector
     */
    public final float getAX() {

        return ax;
    }

    /**
     * Get the <tt>y</tt> component of the left vector
     *
     * @return The <tt>y</tt> component of the left vector
     */
    public final float getAY() {

        return ay;
    }

    /**
     * Get the <tt>w</tt> component of the left vector
     *
     * @return The <tt>w</tt> component of the left vector
     */
    public final float getAW() {

        return aw;
    }

    /**
     * Get the <tt>x</tt> component of the left-middle vector
     *
     * @return The <tt>x</tt> component of the left-middle vector
     */
    public final float getBX() {

        return bx;
    }

    /**
     * Get the <tt>y</tt> component of the left-middle vector
     *
     * @return The <tt>y</tt> component of the left-middle vector
     */
    public final float getBY() {

        return by;
    }

    /**
     * Get the <tt>w</tt> component of the left-middle vector
     *
     * @return The <tt>w</tt> component of the left-middle vector
     */
    public final float getBW() {

        return bw;
    }

    /**
     * Get the <tt>x</tt> component of the right-middle vector
     *
     * @return The <tt>x</tt> component of the right-middle vector
     */
    public final float getCX() {

        return cx;
    }

    /**
     * Get the <tt>y</tt> component of the right-middle vector
     *
     * @return The <tt>y</tt> component of the right-middle vector
     */
    public final float getCY() {

        return cy;
    }

    /**
     * Get the <tt>w</tt> component of the right-middle vector
     *
     * @return The <tt>w</tt> component of the right-middle vector
     */
    public final float getCW() {

        return cw;
    }

    /**
     * Calculate the determinant of the matrix
     *
     * @return The determinant of the matrix
     */
    public final float determinant() {

        float rax = by * cw - cy * bw;
        float ray = cy * aw - ay * cw;
        float raw = ay * bw - by * aw;

        return ax * rax + bx * ray + cx * raw;
    }

    /**
     * Calculate the product of the matrix and the vector
     *
     * @param vector The right-hand vector
     *
     * @return The product of the matrix and the vector
     */
    @NonNull
    public final Vector2D multiply(@NonNull Vector2D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a product with a null vector");
        }

        float x = ax * vector.getX() + bx * vector.getY() + cx * vector.getW();
        float y = ay * vector.getX() + by * vector.getY() + cy * vector.getW();
        float w = aw * vector.getX() + bw * vector.getY() + cw * vector.getW();

        return VectorFactory.createVector2D(x, y, w);
    }

    /**
     * Calculate the product of the two matrices
     *
     * @param matrix The right-hand matrix
     *
     * @return The product of the matrices
     */
    @NonNull
    public final Matrix2D multiply(@NonNull Matrix2D matrix) {

        if (matrix == null) {

            throw new IllegalArgumentException("Unable to calculate a product with a null matrix");
        }

        float ax = this.ax * matrix.ax + this.bx * matrix.ay + this.cx * matrix.aw;
        float ay = this.ay * matrix.ax + this.by * matrix.ay + this.cy * matrix.aw;
        float aw = this.aw * matrix.ax + this.bw * matrix.ay + this.cw * matrix.aw;

        float bx = this.ax * matrix.bx + this.bx * matrix.by + this.cx * matrix.bw;
        float by = this.ay * matrix.bx + this.by * matrix.by + this.cy * matrix.bw;
        float bw = this.aw * matrix.bx + this.bw * matrix.by + this.cw * matrix.bw;

        float cx = this.ax * matrix.cx + this.bx * matrix.cy + this.cx * matrix.cw;
        float cy = this.ay * matrix.cx + this.by * matrix.cy + this.cy * matrix.cw;
        float cw = this.aw * matrix.cx + this.bw * matrix.cy + this.cw * matrix.cw;

        return MatrixFactory.createMatrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }

    /**
     * Calculate the scalar product of the matrix
     *
     * @param factor The scale factor
     *
     * @return The scalar product of the matrix
     */
    @NonNull
    public final Matrix2D multiply(float factor) {

        float ax = this.ax * factor;
        float ay = this.ay * factor;
        float aw = this.aw * factor;

        float bx = this.ax * factor;
        float by = this.ay * factor;
        float bw = this.aw * factor;

        float cx = this.ax * factor;
        float cy = this.ay * factor;
        float cw = this.aw * factor;

        return MatrixFactory.buildMatrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }

    /**
     * Invert the matrix
     *
     * @return The inverted matrix
     */
    @NonNull
    public final Matrix2D invert() {

        float cax = by * cw - cy * bw;
        float cbx = cy * aw - ay * cw;
        float ccx = ay * bw - by * aw;

        float d = ax * cax + bx * cbx + cx * ccx;

        if (d == 0f) {

            throw new IllegalStateException("Unable to invert a matrix with a zero determinant");
        }

        float cay = cx * bw - bx * cw;
        float cby = ax * cw - cx * aw;
        float ccy = bx * aw - ax * bw;

        float caw = bx * cy - cx * by;
        float cbw = cx * ay - ax * cy;
        float ccw = ax * by - bx * ay;

        float id = 1f / d;

        return MatrixFactory.buildMatrix2D(
                cax * id, cay * id, caw * id,
                cbx * id, cby * id, cbw * id,
                ccx * id, ccy * id, ccw * id);
    }

    /**
     * Transpose the matrix
     *
     * @return The transposed matrix
     */
    @NonNull
    public final Matrix2D transpose() {

        return MatrixFactory.buildMatrix2D(
                ax, ay, aw,
                bx, by, bw,
                cx, cy, cw);
    }

    /**
     * Normalize the matrix
     *
     * @return The normalized matrix
     */
    @NonNull
    public final Matrix2D normalize() {

        float d = determinant();

        if (d == 0f) {

            throw new IllegalStateException("Unable to normalize a matrix with a zero determinant");
        }

        float id = 1f / d;

        return MatrixFactory.buildMatrix2D(
                ax * id, bx * id, cx * id,
                ay * id, by * id, cy * id,
                aw * id, bw * id, cw * id);
    }

    /**
     * Rotate the matrix
     *
     * @param a The rotation angle
     *
     * @return The rotated matrix
     */
    @NonNull
    public final Matrix2D rotate(float a) {

        a *= (float) (Math.PI / 180f);

        float sa = (float) Math.sin(a);
        float ca = (float) Math.cos(a);

        float ax = this.ax * ca + this.bx * sa;
        float ay = this.ay * ca + this.by * sa;
        float aw = this.aw * ca + this.bw * sa;

        float bx = this.ax * -sa + this.bx * ca;
        float by = this.ay * -sa + this.by * ca;
        float bw = this.aw * -sa + this.bw * ca;

        return MatrixFactory.buildMatrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }

    /**
     * Scale the matrix
     *
     * @param x The <tt>x</tt> scale factor
     * @param y The <tt>y</tt> scale factor
     *
     * @return The scaled matrix
     */
    @NonNull
    public final Matrix2D scale(float x, float y) {

        float ax = this.ax * x;
        float ay = this.ay * x;

        float bx = this.bx * y;
        float by = this.by * y;

        return MatrixFactory.buildMatrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }

    /**
     * Translate the matrix
     *
     * @param x The <tt>x</tt> translation offset
     * @param y The <tt>y</tt> translation offset
     *
     * @return The translated matrix
     */
    @NonNull
    public final Matrix2D translate(float x, float y) {

        float cx = ax * x + bx * y;
        float cy = ax * x + bx * y;

        return MatrixFactory.buildMatrix2D(
                ax, bx, cx,
                ay, by, cy,
                aw, bw, cw);
    }
}
