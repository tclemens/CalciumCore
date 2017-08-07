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

package net.tclemens.calcium.engine.graphics.mesh;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.math.matrix.Matrix3D;
import net.tclemens.calcium.math.vector.Vector2D;
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class represents a drawable mesh
 *
 * @author Tim Clemens
 */
public final class Mesh {

    /** The positions of each vertex in the mesh */
    private final Collection<Vector3D> positions;

    /** The texture coordinates of each vertex in the mesh */
    private final Collection<Vector2D> coordinates;

    /** The vertex indices of each triangle in the mesh */
    private final Collection<Integer> indices;

    /**
     * @param positions The positions of each vertex in the mesh
     * @param coordinates The texture coordinates of each vertex in the mesh
     * @param indices The vertex indices of each triangle in the mesh
     */
    Mesh(Collection<Vector3D> positions,
         Collection<Vector2D> coordinates,
         Collection<Integer> indices) {

        this.positions = positions;
        this.coordinates = coordinates;
        this.indices = indices;
    }

    /**
     * Get the position of each vertex in the mesh
     *
     * @return The position of each vertex in the mesh
     */
    @NonNull
    public final Collection<Vector3D> getPositions() {

        return positions;
    }

    /**
     * Get the texture coordinates of each vertex in the mesh
     *
     * @return The texture coordinates of each vertex in the mesh
     */
    @NonNull
    public final Collection<Vector2D> getCoordinates() {

        return coordinates;
    }

    /**
     * Get the vertex indices of each triangle in the mesh
     *
     * @return The vertex indices of each triangle in the mesh
     */
    @NonNull
    public final Collection<Integer> getIndices() {

        return indices;
    }

    /**
     * Apply a transformation matrix to the position of each vertex in the mesh
     *
     * @param matrix The transformation matrix to apply to the mesh
     *
     * @return The transformed mesh
     */
    @NonNull
    public final Mesh transform(@NonNull Matrix3D matrix) {

        if (matrix == null) {

            throw new IllegalArgumentException("Unable to transform a mesh with a null matrix");
        }

        Collection<Vector3D> positions = new ArrayList<>(this.positions.size());

        for (Vector3D position : this.positions) {

            positions.add(matrix.multiply(position));
        }

        positions = Collections.unmodifiableCollection(positions);

        return MeshFactory.buildMesh(positions, coordinates, indices);
    }
}
