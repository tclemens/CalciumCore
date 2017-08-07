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

package net.tclemens.calcium.engine.graphics.animation.interpolation;

import android.support.annotation.NonNull;

/**
 * This class represents an linear interpolation from 0 to 1
 *
 * @author Tim Clemens
 */
final class LinearInterpolation extends Interpolation {

    /** The current time in milliseconds */
    private final long time;

    /** The start time of the interpolation in milliseconds */
    private final long start;

    /** The end time of the interpolation in milliseconds */
    private final long end;

    /**
     * @param time The current time in milliseconds
     * @param start The start time of the interpolation in milliseconds
     * @param end The end time of the interpolation in milliseconds
     */
    LinearInterpolation(long time, long start, long end) {

        this.time = time;
        this.start = start;
        this.end = end;
    }

    @Override
    public final float getValue() {

        if (time < start) {

            return 0f;
        }

        float elapsed = time - start;
        float duration = end - start;

        return elapsed / duration;
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @NonNull
    @Override
    public final Interpolation update(long time) {

        if (time < end) {

            return InterpolationFactory.buildLinear(time, start, end);
        }

        return InterpolationFactory.buildComplete();
    }

    @NonNull
    @Override
    public final Interpolation finish() {

        return InterpolationFactory.buildComplete();
    }
}
