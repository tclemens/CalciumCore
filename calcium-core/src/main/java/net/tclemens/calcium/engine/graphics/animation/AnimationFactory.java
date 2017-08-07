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

package net.tclemens.calcium.engine.graphics.animation;

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.animation.transformation.Transformation;
import net.tclemens.calcium.math.matrix.Matrix3D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class is responsible for creating and initializing animations
 *
 * @author Tim Clemens
 */
public final class AnimationFactory {

    private AnimationFactory() {
    }

    /**
     * Create a static animation
     *
     * @param matrix The transformation matrix of the animation
     *
     * @return The new animation
     *
     * @throws IllegalArgumentException If the transformation matrix is invalid
     */
    @NonNull
    public static Animation createStatic(@NonNull Matrix3D matrix) {

        if (matrix == null) {

            throw new IllegalArgumentException("Unable to create an animation with a null matrix");
        }

        return buildStatic(matrix);
    }

    /**
     * Create a dynamic animation
     *
     * @param transformation The transformation of the animation
     *
     * @return The new animation
     *
     * @throws IllegalArgumentException If the transformation is invalid
     */
    @NonNull
    public static Animation createDynamic(@NonNull Transformation transformation) {

        if (transformation == null) {

            throw new IllegalArgumentException("Unable to create an animation with a null transformation");
        }

        return buildDynamic(transformation);
    }

    /**
     * Create a composite animation from a collection of animations
     *
     * @param animations The collection of animations
     *
     * @return The new animation
     *
     * @throws IllegalArgumentException If any of the composite animations are invalid
     */
    @NonNull
    public static Animation createComposite(@NonNull Collection<Animation> animations) {

        if (animations == null) {

            throw new IllegalArgumentException("Unable to create an animation with a null animation collection");
        }

        if (animations.contains(null)) {

            throw new IllegalArgumentException("Unable to create an animation with null animations");
        }

        animations = Collections.unmodifiableCollection(new ArrayList<>(animations));

        return buildComposite(animations);
    }

    /**
     * Create a static animation
     *
     * @param matrix The transformation matrix of the animation
     *
     * @return The new animation
     */
    static Animation buildStatic(Matrix3D matrix) {

        return new StaticAnimation(matrix);
    }

    /**
     * Create a dynamic animation
     *
     * @param transformation The transformation of the animation
     *
     * @return The new animation
     */
    static Animation buildDynamic(Transformation transformation) {

        return new DynamicAnimation(transformation);
    }

    /**
     * Create a composite animation from a collection of animations
     *
     * @param animations The collection of animations
     *
     * @return The new animation
     */
    static Animation buildComposite(Collection<Animation> animations) {

        return new CompositeAnimation(animations);
    }
}
