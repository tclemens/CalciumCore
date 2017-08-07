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
 * This class is responsible for creating and initializing interpolations
 *
 * @author Tim Clemens
 */
public final class InterpolationFactory {

    /** The singleton for a complete interpolation */
    private static final Interpolation COMPLETE_INTERPOLATION = new CompleteInterpolation();

    private InterpolationFactory() {
    }

    /**
     * Create a complete interpolation
     *
     * @return The new interpolation
     */
    @NonNull
    public static Interpolation createComplete() {

        return buildComplete();
    }

    /**
     * Create a linear interpolation
     *
     * @param start The start time of the interpolation in milliseconds
     * @param duration The end time of the interpolation in milliseconds
     *
     * @return The new interpolation
     *
     * @throws IllegalArgumentException If the interpolation dimensions are invalid
     */
    @NonNull
    public static Interpolation createLinear(long start, long duration) {

        if (start < 0) {

            throw new IllegalArgumentException("Unable to create an interpolation with a negative start time");
        }

        if (duration < 0) {

            throw new IllegalArgumentException("Unable to create an interpolation with a negative duration");
        }

        return buildLinear(0, start, start + duration);
    }

    /**
     * Create a complete interpolation
     *
     * @return The new interpolation
     */
    static Interpolation buildComplete() {

        return COMPLETE_INTERPOLATION;
    }

    /**
     * Create a linear interpolation
     *
     * @param time The current time in milliseconds
     * @param start The start time of the interpolation in milliseconds
     * @param end The end time of the interpolation in milliseconds
     *
     * @return The new interpolation
     */
    static Interpolation buildLinear(long time, long start, long end) {

        return new LinearInterpolation(time, start, end);
    }
}
