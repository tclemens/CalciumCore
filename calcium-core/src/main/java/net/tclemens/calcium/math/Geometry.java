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

package net.tclemens.calcium.math;

/**
 * This class implements common geometric algorithms
 *
 * @author Tim Clemens
 */
public final class Geometry {

    private Geometry() {
    }

    /**
     * Check if the points A, B and C are collinear
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     *
     * @return True if A, B and C are collinear
     */
    public static boolean collinear2D(float ax, float ay,
                                      float bx, float by,
                                      float cx, float cy) {

        // The points are collinear if they for an empty triangle
        return ax * (by - cy) + bx * (cy - ay) + cx * (ay - by) == 0f;
    }

    /**
     * Check if the points A, B and C are collinear
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     * @param cz The z coordinate of point C
     *
     * @return True if A, B and C are collinear
     */
    public static boolean collinear3D(float ax, float ay, float az,
                                      float bx, float by, float bz,
                                      float cx, float cy, float cz) {

        // Find B - A
        float bax = bx - ax;
        float bay = by - ay;
        float baz = bz - az;

        // Find C - A
        float cax = cx - ax;
        float cay = cy - ay;
        float caz = cz - az;

        // Find the cross product of (B - A) and (C - A)
        float abcx = (bay * caz) - (baz * cay);
        float abcy = (baz * cax) - (bax * caz);
        float abcz = (bax * cay) - (bay * cax);

        // The points are collinear if they for an empty triangle
        return (abcx * abcx) + (abcy * abcy) + (abcz * abcz) == 0f;
    }

    /**
     * Find the minimum distance between the line segment AB and the point C
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     *
     * @return The minimum distance between AB and C
     */
    public static float distance2D(float ax, float ay,
                                   float bx, float by,
                                   float cx, float cy) {

        float abx = ax - bx;
        float aby = ay - by;

        // Find the squared distance from A to B
        float abd = (abx * abx) + (aby * aby);

        // Check if the line segment has zero length
        if (abd == 0f) {

            return distance2D(abx, aby);
        }

        float t = (cx - ax) * (bx - ax) + (cy - ay) * (by - ay);
        float dt = Math.max(0f, Math.min(1f, t / abd));

        float dx = ax + dt * (bx - ax);
        float dy = ay + dt * (by - ay);

        return distance2D(cx, cy, dx, dy);
    }

    /**
     * Find the minimum distance between the line segment AB and the point C
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     * @param cz The z coordinate of point C
     *
     * @return The minimum distance between AB and C
     */
    public static float distance3D(float ax, float ay, float az,
                                   float bx, float by, float bz,
                                   float cx, float cy, float cz) {

        float abx = ax - bx;
        float aby = ay - by;
        float abz = az - bz;

        // Find the distance from point A to point B squared
        float abd = (abx * abx) + (aby * aby) + (abz * abz);

        // Check if the line segment has zero length
        if (abd == 0f) {

            return distance3D(abx, aby, abz);
        }

        float t = (cx - ax) * (bx - ax) + (cy - ay) * (by - ay) + (cz - az) * (bz - az);
        float dt = Math.max(0f, Math.min(1f, t / abd));

        float dx = ax + dt * (bx - ax);
        float dy = ay + dt * (by - ay);
        float dz = az + dt * (bz - az);

        return distance3D(cx, cy, cz, dx, dy, dz);
    }

    /**
     * Find the distance between the points A and B
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @return The distance between the points A and B
     */
    public static float distance2D(float ax, float ay,
                                   float bx, float by) {

        float x = ax - bx;
        float y = ay - by;

        return distance2D(x, y);
    }

    /**
     * Find the distance between the points A and B
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @return The distance between the points A and B
     */
    public static float distance3D(float ax, float ay, float az,
                                   float bx, float by, float bz) {

        float x = ax - bx;
        float y = ay - by;
        float z = az - bz;

        return distance3D(x, y, z);
    }

    /**
     * Find the distance between a point and the origin
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     *
     * @return The distance between the point and the origin
     */
    public static float distance2D(float x, float y) {

        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Find the distance between a point and the origin
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @param z The z coordinate of the point
     *
     * @return The distance between the point and the origin
     */
    public static float distance3D(float x, float y, float z) {

        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Check if the point intersects the axis-aligned area
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     *
     * @param left The lower bound for z coordinate
     * @param right The upper bound for z coordinate
     *
     * @param bottom The lower bound for y coordinate
     * @param top The upper bound for y coordinate
     *
     * @return True if the point intersects the area
     */
    public static boolean intersectArea2D(float x, float y,
                                          float left, float right,
                                          float bottom, float top) {

        throw new UnsupportedOperationException("Geometry: intersectArea2D is not implemented");
    }

    /**
     * Check if the line segment AB intersects the axis-aligned area
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @param left The lower bound for z coordinate
     * @param right The upper bound for z coordinate
     *
     * @param bottom The lower bound for y coordinate
     * @param top The upper bound for y coordinate
     *
     * @return True if AB intersects the area
     */
    public static boolean intersectArea2D(float ax, float ay,
                                          float bx, float by,
                                          float left, float right,
                                          float bottom, float top) {

        throw new UnsupportedOperationException("Geometry: intersectArea2D is not implemented");
    }

    /**
     * Check if the point intersects the axis-aligned volume
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @param z The z coordinate of the point
     *
     * @param left The lower bound for z coordinate
     * @param right The upper bound for z coordinate
     *
     * @param bottom The lower bound for y coordinate
     * @param top The upper bound for y coordinate
     *
     * @param near The lower bound for z coordinate
     * @param far The upper bound for z coordinate
     *
     * @return True if the point intersects the volume
     */
    public static boolean intersectVolume3D(float x, float y, float z,
                                            float left, float right,
                                            float bottom, float top,
                                            float near, float far) {

        return (x >= left &&
                x <= right &&
                y >= bottom &&
                y <= top &&
                z >= near &&
                z <= far);
    }

    /**
     * Check if the line segment AB intersects the axis-aligned volume
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @param left The lower bound for z coordinate
     * @param right The upper bound for z coordinate
     *
     * @param bottom The lower bound for y coordinate
     * @param top The upper bound for y coordinate
     *
     * @param near The lower bound for z coordinate
     * @param far The upper bound for z coordinate
     *
     * @return True if AB intersects the volume
     */
    public static boolean intersectVolume3D(float ax, float ay, float az,
                                            float bx, float by, float bz,
                                            float left, float right,
                                            float bottom, float top,
                                            float near, float far) {

        // The line intersects the volume if it intersects each plane
        return (intersectArea2D(ax, ay, bx, by, left, right, bottom, top) &&
                intersectArea2D(ax, az, bx, bz, left, right, near, far) &&
                intersectArea2D(ay, az, by, bz, bottom, top, near, far));
    }

    /**
     * Check if the line segment AB and the point C intersect
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     *
     * @return True if AB intersects C
     */
    public static boolean intersectPosition2D(float ax, float ay,
                                              float bx, float by,
                                              float cx, float cy) {

        // Check if the points are collinear
        if (collinear2D(ax, ay, bx, by, cx, cy)) {

            // Find the dimensions of the bounding area for AB
            float abl = Math.min(ax, bx);
            float abr = Math.max(ax, bx);
            float abb = Math.min(ay, by);
            float abt = Math.max(ay, by);

            // Determine if C is inside the bounding area of AB
            return intersectArea2D(cx, cy, abl, abr, abb, abt);
        }

        // The point intersects the line segment if and only if they are collinear
        return false;
    }

    /**
     * Check if the line segment AB and the point C intersect
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     * @param cz The z coordinate of point C
     *
     * @return True if AB intersects C
     */
    public static boolean intersectPosition3D(float ax, float ay, float az,
                                              float bx, float by, float bz,
                                              float cx, float cy, float cz) {

        // Check if the points are collinear
        if (collinear3D(ax, ay, az, bx, by, bz, cx, cy, cz)) {

            // Find the dimensions of the bounding volume for AB
            float abl = Math.min(ax, bx);
            float abr = Math.max(ax, bx);
            float abb = Math.min(ay, by);
            float abt = Math.max(ay, by);
            float abn = Math.min(az, bz);
            float abf = Math.max(az, bz);

            // Determine if C is inside the bounding volume of AB
            return intersectVolume3D(cx, cy, cz, abl, abr, abb, abt, abn, abf);
        }

        // The point intersects the line segment if and only if they are collinear
        return false;
    }

    /**
     * Check if the line segments AB and CD intersect
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     *
     * @param dx The x coordinate of point D
     * @param dy The y coordinate of point D
     *
     * @return True if AB intersects CD
     */
    public static boolean intersectLine2D(float ax, float ay,
                                          float bx, float by,
                                          float cx, float cy,
                                          float dx, float dy) {

        // Find the slopes of both lines
        float abs = (ay - by) / (ax - bx);
        float cds = (cy - dy) / (cx - dx);

        // Find the intercepts of the lines
        float aby = ay - (abs * ax);
        float cdy = cy - (cds * cx);

        // Find the left and right boundaries of the line segments
        float abl = Math.min(ax, bx);
        float abr = Math.max(ax, bx);
        float cdl = Math.min(cx, dx);
        float cdr = Math.max(cx, dx);

        // Check if the lines are parallel
        if (abs != cds) {

            // Find the point of intersection
            float x = (cdy - aby) / (cds - abs);
            float y = (abs * x) + aby;

            // Find the bottom and top boundaries of the line segments
            float abb = Math.min(ay, by);
            float abt = Math.max(ay, by);
            float cdb = Math.min(cy, dy);
            float cdt = Math.max(cy, dy);

            // Determine if the line segments contain the point of intersection
            return (intersectArea2D(x, y, abl, abr, abb, abt) &&
                    intersectArea2D(x, y, cdl, cdr, cdb, cdt));
        }

        // Check if the lines are collinear
        if (aby != cdy) {

            // Parallel lines intersect if and only if they are collinear
            return false;
        }

        // Determine if the collinear line segments overlap
        return (abr - abl) + (cdr - cdl) >= Math.max(abr, cdr) - Math.min(abl, cdl);
    }

    /**
     * Check if the line segments AB and CD intersect
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     * @param cz The z coordinate of point C
     *
     * @param dx The x coordinate of point D
     * @param dy The y coordinate of point D
     * @param dz The z coordinate of point D
     *
     * @return True if AB intersects CD
     */
    public static boolean intersectLine3D(float ax, float ay, float az,
                                          float bx, float by, float bz,
                                          float cx, float cy, float cz,
                                          float dx, float dy, float dz) {

        // Find the slopes for the x-y plane of both lines
        float abxys = (ay - by) / (ax - bx);
        float cdxys = (cy - dy) / (cx - dx);

        // Check if the lines are parallel
        if (abxys != cdxys) {

            // Determine if the lines intersect in the x-y plane
            return intersectLine2D(ax, ay, bx, by, cx, cy, dx, dy);
        }

        // Find the slopes for the x-z plane of both lines
        float abxzs = (az - bz) / (ax - bx);
        float cdxzs = (cz - dz) / (cx - dx);

        // Check if the lines are parallel
        if (abxzs != cdxzs) {

            // Determine if the lines intersect in the x-z plane
            return intersectLine2D(ax, az, bx, bz, cx, cz, dx, dz);
        }

        // Find the slopes for the y-z plane of both lines
        float abyzs = (az - bz) / (ay - by);
        float cdyzs = (cz - dz) / (cy - dy);

        // Check if the lines are parallel
        if (abyzs != cdyzs) {

            // Determine if the lines intersect in the y-z plane
            return intersectLine2D(ay, az, by, bz, cy, cz, dy, dz);
        }

        // Find the intercepts for the x-y plane of both lines
        float abxyy = ay - (abxys * ax);
        float abxzy = az - (abxzs * ax);
        float abyzy = az - (abyzs * ay);
        float cdxyy = cy - (cdxys * cx);
        float cdxzy = cz - (cdxzs * cx);
        float cdyzy = cz - (cdyzs * cy);

        // Check if the lines are collinear
        if (abxyy != cdxyy || abxzy != cdxzy || abyzy != cdyzy) {

            // Parallel lines intersect if and only if they are collinear
            return false;
        }

        // Find the left and right boundaries of the line segments
        float abl = Math.min(ax, bx);
        float abr = Math.max(ax, bx);
        float cdl = Math.min(cx, dx);
        float cdr = Math.max(cx, dx);

        // Determine if the collinear line segments overlap
        return (abr - abl) + (cdr - cdl) >= Math.max(abr, cdr) - Math.min(abl, cdl);
    }

    /**
     * Check if the line segments AB and CD are parallel
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     *
     * @param dx The x coordinate of point D
     * @param dy The y coordinate of point D
     *
     * @return True if AB is parallel to CD
     */
    public static boolean parallel2D(float ax, float ay,
                                     float bx, float by,
                                     float cx, float cy,
                                     float dx, float dy) {

        // Find the slope of both lines
        float abxys = (ay - by) / (ax - bx);
        float cdxys = (cy - dy) / (cx - dx);

        // The lines are parallel if and only if their slopes are equivalent
        return abxys == cdxys;
    }

    /**
     * Check if the line segments AB and CD are parallel
     *
     * @param ax The x coordinate of point A
     * @param ay The y coordinate of point A
     * @param az The z coordinate of point A
     *
     * @param bx The x coordinate of point B
     * @param by The y coordinate of point B
     * @param bz The z coordinate of point B
     *
     * @param cx The x coordinate of point C
     * @param cy The y coordinate of point C
     * @param cz The z coordinate of point C
     *
     * @param dx The x coordinate of point D
     * @param dy The y coordinate of point D
     * @param dz The z coordinate of point D
     *
     * @return True if AB is parallel to CD
     */
    public static boolean parallel3D(float ax, float ay, float az,
                                     float bx, float by, float bz,
                                     float cx, float cy, float cz,
                                     float dx, float dy, float dz) {

        // Find the slope for all planes of both lines
        float abxys = (ay - by) / (ax - bx);
        float abxzs = (az - bz) / (ax - bx);
        float abyzs = (az - bz) / (ay - by);
        float cdxys = (cy - dy) / (cx - dx);
        float cdxzs = (cz - dz) / (cx - dx);
        float cdyzs = (cz - dz) / (cy - dy);

        // The lines are parallel if and only if their slopes are equivalent in each plane
        return (abxys == cdxys &&
                abxzs == cdxzs &&
                abyzs == cdyzs);
    }
}
