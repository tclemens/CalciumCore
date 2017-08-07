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

package net.tclemens.calcium.engine.updates.event;

import net.tclemens.calcium.engine.updates.input.Touch;

/**
 * This class represents an input from a touch
 *
 * @author Tim Clemens
 */
public final class TouchEvent extends Event {

    /** The x-coordinate of the touch input */
    private final float x;

    /** The y-coordinate of the touch input */
    private final float y;

    /** The type of input performed on the touch */
    private final Touch input;

    /**
     * @param time The time of the event in milliseconds
     * @param x The x-coordinate of the touch
     * @param y The y-coordinate of the touch
     * @param input The type of input performed on the touch
     */
    TouchEvent(long time, float x, float y, Touch input) {

        super(time);

        this.x = x;
        this.y = y;
        this.input = input;
    }

    /**
     * Get the x-coordinate of the touch input
     *
     * @return The x-coordinate of the touch input
     */
    public final float getX() {

        return x;
    }

    /**
     * Get the y-coordinate of the touch input
     *
     * @return The y-coordinate of the touch input
     */
    public final float getY() {

        return y;
    }

    /**
     * Get the type of input performed on the touch
     *
     * @return The type of input performed on the touch
     */
    public final Touch getInput() {

        return input;
    }
}
