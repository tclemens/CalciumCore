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

/**
 * This class represents a dynamic transformation matrix
 *
 * @author Tim Clemens
 */
final class DynamicAnimation extends Animation {

    /** The transformation of the animation */
    private final Transformation transformation;

    /**
     * @param transformation The transformation of the animation
     */
    DynamicAnimation(Transformation transformation) {

        this.transformation = transformation;
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @NonNull
    @Override
    public final Matrix3D getMatrix() {

        return transformation.getMatrix();
    }

    @NonNull
    @Override
    public final Animation update(long time) {

        Transformation transformation = this.transformation.update(time);

        if (transformation.isDynamic()) {

            return AnimationFactory.buildDynamic(transformation);
        }

        return AnimationFactory.buildStatic(transformation.getMatrix());
    }

    @NonNull
    @Override
    public final Animation finish() {

        Transformation transformation = this.transformation.finish();

        if (transformation.isDynamic()) {

            return AnimationFactory.buildDynamic(transformation);
        }

        return AnimationFactory.buildStatic(transformation.getMatrix());
    }
}
