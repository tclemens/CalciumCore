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

import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.updates.input.Key;
import net.tclemens.calcium.engine.updates.input.Touch;

/**
 * This class is responsible for creating and initializing engine events
 *
 * @author Tim Clemens
 */
public final class EventFactory {

    private EventFactory() {
    }

    /**
     * Create an animation event
     *
     * @param time The time of the event in milliseconds
     *
     * @return The new event
     *
     * @throws IllegalArgumentException If the event time is invalid
     */
    @NonNull
    public static Event createAnimation(long time) {

        if (time < 0) {

            throw new IllegalArgumentException("Unable to create an event with a negative time");
        }

        return new AnimationEvent(time);
    }

    /**
     * Create a key input event
     *
     * @param time The time of the event in milliseconds
     * @param code The code of the key
     * @param input The type of input performed on the key
     *
     * @return The new event
     *
     * @throws IllegalArgumentException If the event time, key code or input type is invalid
     */
    @NonNull
    public static Event createKey(long time, int code, @NonNull Key input) {

        if (time < 0) {

            throw new IllegalArgumentException("Unable to create an event with a negative time");
        }

        if (input == null) {

            throw new IllegalArgumentException("Unable to create an event with a null input type");
        }

        return new KeyEvent(time, code, input);
    }

    /**
     * Create a scheduled action event
     *
     * @param time The time of the event in milliseconds
     * @param name The name of the scheduled action
     *
     * @return The new event
     *
     * @throws IllegalArgumentException If the event time or scheduled action name is invalid
     */
    @NonNull
    public static Event createAction(long time, @NonNull String name) {

        if (time < 0) {

            throw new IllegalArgumentException("Unable to create an event with a negative time");
        }

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Unable to create an event with a null or empty action name");
        }

        return new ActionEvent(time, name);
    }

    /**
     * Create a touch input event
     *
     * @param time The time of the event in milliseconds
     * @param x The x-coordinate of the touch input
     * @param y The y-coordinate of the touch input
     * @param input The type of the touch input
     *
     * @return The new event
     *
     * @throws IllegalArgumentException If the event time, touch coordinates, or input type is invalid
     */
    @NonNull
    public static Event createTouch(long time, float x, float y, @NonNull Touch input) {

        if (time < 0) {

            throw new IllegalArgumentException("Unable to create an event with a negative time");
        }

        if (input == null) {

            throw new IllegalArgumentException("Unable to create an event with a null input type");
        }

        return new TouchEvent(time, x, y, input);
    }

    /**
     * Create a view event
     *
     * @param time The time of the event in milliseconds
     * @param width The width of the view
     * @param height The height of the view
     *
     * @return The new event
     *
     * @throws IllegalArgumentException If the event time or view dimensions are invalid
     */
    @NonNull
    public static Event createView(long time, int width, int height) {

        if (time < 0) {

            throw new IllegalArgumentException("Unable to create an event with a negative time");
        }

        if (width <= 0) {

            throw new IllegalArgumentException("Unable to create an event with a zero or negative width");
        }

        if (height <= 0) {

            throw new IllegalArgumentException("Unable to create an event with a zero or negative height");
        }

        return new ViewEvent(time, width, height);
    }
}
