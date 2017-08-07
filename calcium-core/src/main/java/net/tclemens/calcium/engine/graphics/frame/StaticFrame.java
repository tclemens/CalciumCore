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

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.scene.Scene;

/**x
 * This class represents a frame with static scenes
 *
 * @author Tim Clemens
 */
final class StaticFrame extends Frame {

    /**
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     */
    StaticFrame(Collection<Scene> scenes, Color color, int width, int height) {

        super(scenes, color, width, height);
    }

    @Override
    public final long getDuration() {

        return 0;
    }

    @Override
    public final boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public final Frame update(long time) {

        return this;
    }
}
