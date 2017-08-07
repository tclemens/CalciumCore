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
import net.tclemens.calcium.math.vector.VectorFactory;

/**
 * This class represents an affine three-dimensional matrix
 *
 * @author Tim Clemens
 */
public final class Matrix3D {

    /** The <tt>x</tt> component of the left vector */
    private final float ax;

    /** The <tt>y</tt> component of the left vector */
    private final float ay;

    /** The <tt>z</tt> component of the left vector */
    private final float az;

    /** The <tt>w</tt> component of the left vector */
    private final float aw;

    /** The <tt>x</tt> component of the left-middle vector */
    private final float bx;

    /** The <tt>y</tt> component of the left-middle vector */
    private final float by;

    /** The <tt>z</tt> component of the left-middle vector */
    private final float bz;

    /** The <tt>w</tt> component of the left-middle vector */
    private final float bw;

    /** The <tt>x</tt> component of the right-middle vector */
    private final float cx;

    /** The <tt>y</tt> component of the right-middle vector */
    private final float cy;

    /** The <tt>z</tt> component of the right-middle vector */
    private final float cz;

    /** The <tt>w</tt> component of the right-middle vector */
    private final float cw;

    /** The <tt>x</tt> component of the right vector */
    private final float dx;

    /** The <tt>y</tt> component of the right vector */
    private final float dy;

    /** The <tt>z</tt> component of the left vector */
    private final float dz;

    /** The <tt>w</tt> component of the right vector */
    private final float dw;

    /**
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
     */
    Matrix3D(float ax, float bx, float cx, float dx,
             float ay, float by, float cy, float dy,
             float az, float bz, float cz, float dz,
             float aw, float bw, float cw, float dw) {

        this.ax = ax;
        this.ay = ay;
        this.az = az;
        this.aw = aw;

        this.bx = bx;
        this.by = by;
        this.bz = bz;
        this.bw = bw;

        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
        this.cw = cw;

        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.dw = dw;
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
     * Get the <tt>z</tt> component of the left vector
     *
     * @return The <tt>z</tt> component of the left vector
     */
    public final float getAZ() {

        return az;
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
     * Get the <tt>z</tt> component of the left-middle vector
     *
     * @return The <tt>z</tt> component of the left-middle vector
     */
    public final float getBZ() {

        return bz;
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
     * Get the <tt>z</tt> component of the right-middle vector
     *
     * @return The <tt>z</tt> component of the right-middle vector
     */
    public final float getCZ() {

        return cz;
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
     * Get the <tt>x</tt> component of the right vector
     *
     * @return The <tt>x</tt> component of the right vector
     */
    public final float getDX() {

        return dx;
    }

    /**
     * Get the <tt>y</tt> component of the right vector
     *
     * @return The <tt>y</tt> component of the right vector
     */
    public final float getDY() {

        return dy;
    }

    /**
     * Get the <tt>z</tt> component of the right vector
     *
     * @return The <tt>z</tt> component of the right vector
     */
    public final float getDZ() {

        return dz;
    }

    /**
     * Get the <tt>w</tt> component of the right vector
     *
     * @return The <tt>w</tt> component of the right vector
     */
    public final float getDW() {

        return dw;
    }

    /**
     * Calculate the determinant of the matrix
     *
     * @return The determinant of the matrix
     */
    public final float determinant() {

        float azbw = az * bw;
        float azcw = az * cw;
        float azdw = az * dw;

        float bzaw = bz * aw;
        float bzcw = bz * cw;
        float bzdw = bz * dw;

        float czaw = cz * aw;
        float czbw = cz * bw;
        float czdw = cz * dw;

        float dzaw = dz * aw;
        float dzbw = dz * bw;
        float dzcw = dz * cw;

        float cax = by * (czdw - dzcw) + cy * (dzbw - bzdw) + dy * (bzcw - czbw);
        float cbx = ay * (dzcw - czdw) + cy * (azdw - dzaw) + dy * (czaw - azcw);
        float ccx = ay * (bzdw - dzbw) + by * (dzaw - azdw) + dy * (azbw - bzaw);
        float cdx = ay * (czbw - bzcw) + by * (azcw - czaw) + cy * (bzaw - azbw);

        return ax * cax + bx * cbx + cx * ccx + dx * cdx;
    }

    /**
     * Calculate the product of the matrix and the vector
     *
     * @param vector The right-hand vector
     *
     * @return The product of the matrix and the vector
     */
    @NonNull
    public final Vector3D multiply(@NonNull Vector3D vector) {

        if (vector == null) {

            throw new IllegalArgumentException("Unable to calculate a product with a null vector");
        }

        float x = ax * vector.getX() + bx * vector.getY() + cx * vector.getZ() + dx * vector.getW();
        float y = ay * vector.getX() + by * vector.getY() + cy * vector.getZ() + dy * vector.getW();
        float z = az * vector.getX() + bz * vector.getY() + cz * vector.getZ() + dz * vector.getW();
        float w = aw * vector.getX() + bw * vector.getY() + cw * vector.getZ() + dw * vector.getW();

        return VectorFactory.createVector3D(x, y, z, w);
    }

    /**
     * Calculate the product of the two matrices
     *
     * @param matrix The right-hand matrix
     *
     * @return The product of the matrices
     */
    @NonNull
    public final Matrix3D multiply(@NonNull Matrix3D matrix) {

        if (matrix == null) {

            throw new IllegalArgumentException("Unable to calculate a product with a null vector");
        }
        
        float rax = ax * matrix.ax + bx * matrix.ay + cx * matrix.az + dx * matrix.aw;
        float ray = ay * matrix.ax + by * matrix.ay + cy * matrix.az + dy * matrix.aw;
        float raz = az * matrix.ax + bz * matrix.ay + cz * matrix.az + dz * matrix.aw;
        float raw = aw * matrix.ax + bw * matrix.ay + cw * matrix.az + dw * matrix.aw;

        float rbx = ax * matrix.bx + bx * matrix.by + cx * matrix.bz + dx * matrix.bw;
        float rby = ay * matrix.bx + by * matrix.by + cy * matrix.bz + dy * matrix.bw;
        float rbz = az * matrix.bx + bz * matrix.by + cz * matrix.bz + dz * matrix.bw;
        float rbw = aw * matrix.bx + bw * matrix.by + cw * matrix.bz + dw * matrix.bw;

        float rcx = ax * matrix.cx + bx * matrix.cy + cx * matrix.cz + dx * matrix.cw;
        float rcy = ay * matrix.cx + by * matrix.cy + cy * matrix.cz + dy * matrix.cw;
        float rcz = az * matrix.cx + bz * matrix.cy + cz * matrix.cz + dz * matrix.cw;
        float rcw = aw * matrix.cx + bw * matrix.cy + cw * matrix.cz + dw * matrix.cw;

        float rdx = ax * matrix.dx + bx * matrix.dy + cx * matrix.dz + dx * matrix.dw;
        float rdy = ay * matrix.dx + by * matrix.dy + cy * matrix.dz + dy * matrix.dw;
        float rdz = az * matrix.dx + bz * matrix.dy + cz * matrix.dz + dz * matrix.dw;
        float rdw = aw * matrix.dx + bw * matrix.dy + cw * matrix.dz + dw * matrix.dw;

        return MatrixFactory.buildMatrix3D(
                rax, rbx, rcx, rdx,
                ray, rby, rcy, rdy,
                raz, rbz, rcz, rdz,
                raw, rbw, rcw, rdw);
    }

    /**
     * Calculate the scalar product of the matrix
     *
     * @param factor The scale factor
     *
     * @return The scalar product of the matrix
     */
    @NonNull
    public final Matrix3D multiply(float factor) {

        return MatrixFactory.buildMatrix3D(
                ax * factor, bx * factor, cx * factor, dx * factor,
                ay * factor, by * factor, cy * factor, dy * factor,
                az * factor, bz * factor, cz * factor, dz * factor,
                aw * factor, bw * factor, cw * factor, dw * factor);
    }

    /**
     * Invert the matrix
     *
     * @return The inverted matrix
     */
    @NonNull
    public final Matrix3D invert() {

        float azbw = az * bw;
        float azcw = az * cw;
        float azdw = az * dw;

        float bzaw = bz * aw;
        float bzcw = bz * cw;
        float bzdw = bz * dw;

        float czaw = cz * aw;
        float czbw = cz * bw;
        float czdw = cz * dw;

        float dzaw = dz * aw;
        float dzbw = dz * bw;
        float dzcw = dz * cw;

        float cax = by * (czdw - dzcw) + cy * (dzbw - bzdw) + dy * (bzcw - czbw);
        float cbx = ay * (dzcw - czdw) + cy * (azdw - dzaw) + dy * (czaw - azcw);
        float ccx = ay * (bzdw - dzbw) + by * (dzaw - azdw) + dy * (azbw - bzaw);
        float cdx = ay * (czbw - bzcw) + by * (azcw - czaw) + cy * (bzaw - azbw);

        float d = ax * cax + bx * cbx + cx * ccx + dx * cdx;

        if (d == 0f) {

            throw new IllegalStateException("Unable to invert a matrix with a zero determinant");
        }

        float axby = ax * by;
        float axcy = ax * cy;
        float axdy = ax * dy;

        float bxay = bx * ay;
        float bxcy = bx * cy;
        float bxdy = bx * dy;

        float cxay = cx * ay;
        float cxby = cx * by;
        float cxdy = cx * dy;

        float dxay = dx * ay;
        float dxby = dx * by;
        float dxcy = dx * cy;

        float cay = bx * (dzcw - czdw) + cx * (bzdw - dzbw) + dx * (czbw - bzcw);
        float cby = ax * (czdw - dzcw) + cx * (dzaw - azdw) + dx * (azcw - czaw);
        float ccy = ax * (dzbw - bzdw) + bx * (azdw - dzaw) + dx * (bzaw - azbw);
        float cdy = ax * (bzcw - czbw) + bx * (czaw - azcw) + cx * (azbw - bzaw);

        float caz = bw * (cxdy - dxcy) + cw * (dxby - bxdy) + dw * (bxcy - cxby);
        float cbz = aw * (dxcy - cxdy) + cw * (axdy - dxay) + dw * (cxay - axcy);
        float ccz = aw * (bxdy - dxby) + bw * (dxay - axdy) + dw * (axby - bxay);
        float cdz = aw * (cxby - bxcy) + bw * (axcy - cxay) + cw * (bxay - axby);

        float caw = bz * (dxcy - cxdy) + cz * (bxdy - dxby) + dz * (cxby - bxcy);
        float cbw = az * (cxdy - dxcy) + cz * (dxay - axdy) + dz * (axcy - cxay);
        float ccw = az * (dxby - bxdy) + bz * (axdy - dxay) + dz * (bxay - axby);
        float cdw = az * (bxcy - cxby) + bz * (cxay - axcy) + cz * (axby - bxay);

        float id = 1f / d;

        return MatrixFactory.buildMatrix3D(
                cax * id, cay * id, caz * id, caw * id,
                cbx * id, cby * id, cbz * id, cbw * id,
                ccx * id, ccy * id, ccz * id, ccw * id,
                cdx * id, cdy * id, cdz * id, cdw * id);
    }

    /**
     * Transpose the matrix
     *
     * @return The transposed matrix
     */
    @NonNull
    public final Matrix3D transpose() {

        return MatrixFactory.buildMatrix3D(
                ax, ay, az, aw,
                bx, by, bz, bw,
                cx, cy, cz, cw,
                dx, dy, dz, dw);
    }

    /**
     * Normalize the matrix
     *
     * @return The normalized matrix
     */
    @NonNull
    public final Matrix3D normalize() {

        float d = determinant();

        if (d == 0f) {

            throw new IllegalStateException("Unable to normalize a matrix with a zero determinant");
        }

        float id = 1f / d;

        return MatrixFactory.buildMatrix3D(
                ax * id, bx * id, cx * id, dx * id,
                ay * id, by * id, cy * id, dy * id,
                az * id, bz * id, cz * id, dz * id,
                aw * id, bw * id, cw * id, dw * id);
    }

    /**
     * Rotate the matrix
     *
     * @param x The <tt>x</tt> rotation factor in degrees
     * @param y The <tt>x</tt> rotation factor in degrees
     * @param z The <tt>x</tt> rotation factor in degrees
     *
     * @return The rotated matrix
     */
    @NonNull
    public final Matrix3D rotate(float x, float y, float z) {

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

        float rax = cya * cza;
        float ray = -cya * sza;
        float raz = sya;

        float rbx = cxa * sya * cza + cxa * sza;
        float rby = -cxa * sya * sza + cxa * cza;
        float rbz = -sxa * cya;

        float rcx = -sxa * sya * cza + sxa * sza;
        float rcy = sxa * sya * sza + sxa * cza;
        float rcz = cxa * cya;
        
        float ax = this.ax * rax + this.bx * ray + this.cx * raz;
        float ay = this.ay * rax + this.by * ray + this.cy * raz;
        float az = this.az * rax + this.bz * ray + this.cz * raz;
        float aw = this.aw * rax + this.bw * ray + this.cw * raz;

        float bx = this.ax * rbx + this.bx * rby + this.cx * rbz;
        float by = this.ay * rbx + this.by * rby + this.cy * rbz;
        float bz = this.az * rbx + this.bz * rby + this.cz * rbz;
        float bw = this.aw * rbx + this.bw * rby + this.cw * rbz;

        float cx = this.ax * rcx + this.bx * rcy + this.cx * rcz;
        float cy = this.ay * rcx + this.by * rcy + this.cy * rcz;
        float cz = this.az * rcx + this.bz * rcy + this.cz * rcz;
        float cw = this.aw * rcx + this.bw * rcy + this.cw * rcz;

        return MatrixFactory.buildMatrix3D(
                ax, bx, cx, dx,
                ay, by, cy, dy,
                az, bz, cz, dz,
                aw, bw, cw, dw);
    }

    /**
     * Scale the matrix
     *
     * @param x The <tt>x</tt> scale factor
     * @param y The <tt>y</tt> scale factor
     * @param z The <tt>z</tt> scale factor
     *
     * @return The scaled matrix
     */
    @NonNull
    public final Matrix3D scale(float x, float y, float z) {

        float ax = this.ax * x;
        float ay = this.ay * x;
        float az = this.az * x;

        float bx = this.bx * y;
        float by = this.by * y;
        float bz = this.bz * y;

        float cx = this.cx * z;
        float cy = this.cy * z;
        float cz = this.cz * z;

        return MatrixFactory.buildMatrix3D(
                ax, bx, cx, dx,
                ay, by, cy, dy,
                az, bz, cz, dz,
                aw, bw, cw, dw);
    }

    /**
     * Translate the matrix
     *
     * @param x The <tt>x</tt> translation offset
     * @param y The <tt>y</tt> translation offset
     * @param z The <tt>z</tt> translation offset
     *
     * @return The translated matrix
     */
    @NonNull
    public final Matrix3D translate(float x, float y, float z) {

        float dx = ax * x + bx * y + cx * z;
        float dy = ax * x + bx * y + cx * z;
        float dz = ax * x + bx * y + cx * z;

        return MatrixFactory.buildMatrix3D(
                ax, bx, cx, dx,
                ay, by, cy, dy,
                az, bz, cz, dz,
                aw, bw, cw, dw);
    }
}
