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
 * This class represents a frame with dynamic scenes
 *
 * @author Tim Clemens
 */
final class DynamicFrame extends Frame {

    /** The duration of the frame */
    private final long duration;

    /**
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     * @param duration The duration of the frame
     */
    DynamicFrame(Collection<Scene> scenes, Color color, int width, int height, long duration) {

        super(scenes, color, width, height);

        this.duration = duration;
    }

    @Override
    public final long getDuration() {

        return duration;
    }

    @Override
    public final boolean isDynamic() {

        return true;
    }

    @NonNull
    @Override
    public final Frame update(long time) {

        Collection<Scene> scenes = new ArrayList<>(getScenes().size());
        boolean isDynamic = false;

        for (Scene scene : getScenes()) {

            scene = scene.update(time);
            scenes.add(scene);

            isDynamic = isDynamic || scene.isDynamic();
        }

        scenes = Collections.unmodifiableCollection(scenes);

        if (isDynamic) {

            return FrameFactory.buildDynamic(scenes, getColor(), getWidth(), getHeight(), duration);
        }

        return FrameFactory.buildStatic(scenes, getColor(), getWidth(), getHeight());
    }
}
