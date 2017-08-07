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
 * This class represents a value interpolated from 0 to 1
 *
 * @author Tim Clemens
 */
public abstract class Interpolation {

    Interpolation() {
    }

    /**
     * Get the value of the interpolation
     *
     * @return The value of the interpolation
     */
    public abstract float getValue();

    /**
     * Check if the interpolation is dynamic
     *
     * @return <tt>true</tt> if the interpolation is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Attempt to update the interpolation of the value
     *
     * @param time The current time in milliseconds
     *
     * @return The updated interpolation
     */
    @NonNull
    public abstract Interpolation update(long time);

    /**
     * Apply the full interpolation of the value
     *
     * @return The updated interpolation
     */
    @NonNull
    public abstract Interpolation finish();
}
