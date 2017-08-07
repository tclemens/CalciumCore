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

import net.tclemens.calcium.math.vector.Vector2D;
import net.tclemens.calcium.math.vector.Vector3D;

/**
 * This class is responsible for creating and initializing meshes
 *
 * @author Tim Clemens
 */
public final class MeshFactory {

    private MeshFactory() {
    }

    /**
     * Create a drawable mesh from a collection of existing meshes
     *
     * @param meshes A collection of existing meshes
     *
     * @return The new mesh
     *
     * @throws IllegalArgumentException If the composite meshes are invalid
     */
    @NonNull
    public static Mesh createMesh(@NonNull Collection<Mesh> meshes) {

        if (meshes == null) {

            throw new IllegalArgumentException("Unable to create a mesh with a null mesh collection");
        }

        if (meshes.contains(null)) {

            throw new IllegalArgumentException("Unable to create a mesh with null meshes");
        }

        Collection<Vector3D> positions = new ArrayList<>(countPositions(meshes));
        Collection<Vector2D> coordinates = new ArrayList<>(countCoordinates(meshes));
        Collection<java.lang.Integer> indices = new ArrayList<>(countIndices(meshes));

        for (Mesh mesh : meshes) {

            for (Integer index : mesh.getIndices()) {

                indices.add(positions.size() + index);
            }

            positions.addAll(mesh.getPositions());
            coordinates.addAll(mesh.getCoordinates());
        }

        positions = Collections.unmodifiableCollection(positions);
        coordinates = Collections.unmodifiableCollection(coordinates);
        indices = Collections.unmodifiableCollection(indices);

        return buildMesh(positions, coordinates, indices);
    }

    /**
     * Create a drawable mesh
     *
     * @param positions The position of each vertex in the mesh
     * @param coordinates The texture coordinates of each vertex in the mesh
     * @param indices The vertex indices of each triangle in the mesh
     *
     * @return The new mesh
     *
     * @throws IllegalArgumentException If the vertex positions, texture coordinates, or indices are invalid
     */
    @NonNull
    public static Mesh createMesh(@NonNull Collection<Vector3D> positions,
                                  @NonNull Collection<Vector2D> coordinates,
                                  @NonNull Collection<Integer> indices) {

        if (positions == null) {

            throw new IllegalArgumentException("Unable to create a mesh with a null position collection");
        }

        if (positions.contains(null)) {

            throw new IllegalArgumentException("Unable to create a mesh with null positions");
        }

        if (coordinates == null) {

            throw new IllegalArgumentException("Unable to create a mesh with a null texture coordinate collection");
        }

        if (coordinates.contains(null)) {

            throw new IllegalArgumentException("Unable to create a mesh with null texture coordinates");
        }

        if (indices == null) {

            throw new IllegalArgumentException("Unable to create a mesh with a null index collection");
        }

        if (indices.contains(null)) {

            throw new IllegalArgumentException("Unable to create a mesh with null indices");
        }

        if (coordinates.size() != positions.size()) {

            throw new IllegalArgumentException("Unable to create a mesh with a different number of positions and texture coordinates");
        }

        if (indices.size() > 0 && indices.size() % 3 > 0) {

            throw new IllegalArgumentException("Unable to create a mesh with non-triangular indices");
        }

        positions = Collections.unmodifiableCollection(new ArrayList<>(positions));
        coordinates = Collections.unmodifiableCollection(new ArrayList<>(coordinates));
        indices = Collections.unmodifiableCollection(new ArrayList<>(indices));

        return buildMesh(positions, coordinates, indices);
    }

    /**
     * Create a drawable mesh
     *
     * @param positions The position of each vertex in the mesh
     * @param coordinates The texture coordinates of each vertex in the mesh
     * @param indices The vertex indices of each triangle in the mesh
     *
     * @return The new mesh
     *
     * @throws IllegalArgumentException If the vertex positions, texture coordinates, or indices are invalid
     */
    static Mesh buildMesh(Collection<Vector3D> positions,
                          Collection<Vector2D> coordinates,
                          Collection<Integer> indices) {

        return new Mesh(positions, coordinates, indices);
    }

    /**
     * Count the number of positions in the mesh collection
     *
     * @param meshes The meshes
     *
     * @return The number of positions
     */
    private static int countPositions(Collection<Mesh> meshes) {

        int count = 0;

        for (Mesh mesh : meshes) {

            count += mesh.getPositions().size();
        }

        return count;
    }

    /**
     * Count the number of texture coordinates in the mesh collection
     *
     * @param meshes The meshes
     *
     * @return The number of texture coordinates
     */
    private static int countCoordinates(Collection<Mesh> meshes) {

        int count = 0;

        for (Mesh mesh : meshes) {

            count += mesh.getCoordinates().size();
        }

        return count;
    }

    /**
     * Count the number of indices in the mesh collection
     *
     * @param meshes The meshes
     *
     * @return The number of indices
     */
    private static int countIndices(Collection<Mesh> meshes) {

        int count = 0;

        for (Mesh mesh : meshes) {

            count += mesh.getIndices().size();
        }

        return count;
    }
}
