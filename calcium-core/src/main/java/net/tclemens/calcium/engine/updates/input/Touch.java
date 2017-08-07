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

package net.tclemens.calcium.engine.updates.input;

import android.view.MotionEvent;

/**
 * This enumeration represents the available touch input types
 *
 * @author Tim Clemens
 */
public enum Touch {

    /** The touch started */
    DOWN,

    /** The touch changed positions */
    MOVE,

    /** The touch ended */
    UP;

    /**
     * Create a touch input from a motion event
     *
     * @param event The motion event to parse
     *
     * @return The touch input
     *
     * @throws UnsupportedOperationException If the input action is not supported
     */
    public static Touch parseEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                return DOWN;

            case MotionEvent.ACTION_MOVE:
                return MOVE;

            case MotionEvent.ACTION_UP:
                return UP;

            default:
                throw new UnsupportedOperationException("The requested input action is not supported");
        }
    }
}
