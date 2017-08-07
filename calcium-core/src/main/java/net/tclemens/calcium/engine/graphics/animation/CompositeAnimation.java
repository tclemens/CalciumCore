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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.math.matrix.Matrix3D;
import net.tclemens.calcium.math.matrix.MatrixFactory;

/**
 * This class represents a composite of one or more animations
 *
 * @author Tim Clemens
 */
final class CompositeAnimation extends Animation {

    /** The composite animations */
    private final Collection<Animation> animations;

    /**
     * @param animations The composite animations
     */
    CompositeAnimation(Collection<Animation> animations) {

        this.animations = animations;
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @NonNull
    @Override
    public final Matrix3D getMatrix() {

        Matrix3D matrix = MatrixFactory.createIdentity3D();

        for (Animation animation : animations) {

            matrix = matrix.multiply(animation.getMatrix());
        }

        return matrix;
    }

    @NonNull
    @Override
    public final Animation update(long time) {

        Collection<Animation> animations = new ArrayList<>(this.animations.size());
        Matrix3D matrix = null;

        for (Animation animation : this.animations) {

            animation = animation.update(time);

            if (animation.isDynamic()) {

                if (matrix != null) {

                    animations.add(AnimationFactory.createStatic(matrix));
                    matrix = null;
                }

                animations.add(animation);
            }
            else if (matrix != null) {

                matrix = matrix.multiply(animation.getMatrix());
            }
            else {

                matrix = animation.getMatrix();
            }
        }

        if (animations.isEmpty()) {

            if (matrix == null) {

                matrix = MatrixFactory.createIdentity3D();
            }

            return AnimationFactory.buildStatic(matrix);
        }

        if (matrix != null) {

            animations.add(new StaticAnimation(matrix));
        }

        animations = Collections.unmodifiableCollection(new ArrayList<>(animations));

        return AnimationFactory.buildComposite(animations);
    }

    @NonNull
    @Override
    public final Animation finish() {

        Matrix3D matrix = MatrixFactory.createIdentity3D();

        for (Animation animation : animations) {

            animation = animation.finish();
            matrix = matrix.multiply(animation.getMatrix());
        }

        return AnimationFactory.buildStatic(matrix);
    }
}
