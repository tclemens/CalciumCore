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

package net.tclemens.calcium.engine.graphics.frame;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.scene.Scene;

/**
 * This class is responsible for creating and initializing frames
 *
 * @author Tim Clemens
 */
public final class FrameFactory {

    private FrameFactory() {
    }

    /**
     * Create a static frame
     *
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     *
     * @return The new frame
     *
     * @throws IllegalArgumentException If the frame scenes, color, width, or height is invalid
     */
    @NonNull
    public static Frame createStatic(@NonNull Collection<Scene> scenes, @NonNull Color color, int width, int height) {

        if (scenes == null) {

            throw new IllegalArgumentException("Unable to create a frame with a null scene collection");
        }

        if (scenes.contains(null)) {

            throw new IllegalArgumentException("Unable to create a frame with null scenes");
        }

        if (color == null) {

            throw new IllegalArgumentException("Unable to create a frame with a null background color");
        }

        if (width <= 0) {

            throw new IllegalArgumentException("Unable to create a frame with a zero or negative width");
        }

        if (height <= 0) {

            throw new IllegalArgumentException("Unable to create a frame with a zero or negative height");
        }

        scenes = Collections.unmodifiableCollection(new ArrayList<>(scenes));

        return buildStatic(scenes, color, width, height);
    }

    /**
     * Create a dynamic frame
     *
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     * @param duration The duration of the frame (if dynamic)
     *
     * @return The new frame
     *
     * @throws IllegalArgumentException If the frame scenes, color, width, height, or duration is invalid
     */
    @NonNull
    public static Frame createDynamic(@NonNull Collection<Scene> scenes, @NonNull Color color, int width, int height, long duration) {

        if (scenes == null) {

            throw new IllegalArgumentException("Unable to create a frame with a null scene collection");
        }

        if (scenes.contains(null)) {

            throw new IllegalArgumentException("Unable to create a frame with null scenes");
        }

        if (color == null) {

            throw new IllegalArgumentException("Unable to create a frame with a null background color");
        }

        if (width <= 0) {

            throw new IllegalArgumentException("Unable to create a frame with a zero or negative width");
        }

        if (height <= 0) {

            throw new IllegalArgumentException("Unable to create a frame with a zero or negative height");
        }

        if (duration < 0L) {

            throw new IllegalArgumentException("Unable to create a frame with a negative duration");
        }

        scenes = Collections.unmodifiableCollection(new ArrayList<>(scenes));

        return buildDynamic(scenes, color, width, height, duration);
    }

    /**
     * Create a static frame
     *
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     *
     * @return The new frame
     */
    static Frame buildStatic(Collection<Scene> scenes, Color color, int width, int height) {

        return new StaticFrame(scenes, color, width, height);
    }

    /**
     * Create a dynamic frame
     *
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     * @param duration The duration of the frame (if dynamic)
     *
     * @return The new frame
     */
    static Frame buildDynamic(Collection<Scene> scenes, Color color, int width, int height, long duration) {

        return new DynamicFrame(scenes, color, width, height, duration);
    }
}
