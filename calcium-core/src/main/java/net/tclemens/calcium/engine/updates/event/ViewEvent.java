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

/**
 * This class represents a change to the dimensions of the view
 *
 * @author Tim Clemens
 */
public final class ViewEvent extends Event {

    /** The width of the view */
    private final int width;

    /** The height of the view */
    private final int height;

    /**
     * @param time The time of the event in milliseconds
     * @param width The width of the view
     * @param height The height of the view
     */
    ViewEvent(long time, int width, int height) {

        super(time);

        this.width = width;
        this.height = height;
    }

    /**
     * Get the width of the view
     *
     * @return The width of the view
     */
    public final int getWidth() {

        return width;
    }

    /**
     * Get the height of the view
     *
     * @return The height of the view
     */
    public final int getHeight() {

        return height;
    }
}
