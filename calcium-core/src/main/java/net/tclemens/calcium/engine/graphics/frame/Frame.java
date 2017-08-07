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

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.color.Color;
import net.tclemens.calcium.engine.graphics.scene.Scene;

/**
 * This class represents a collection of scenes to draw in the active render context
 *
 * @author Tim Clemens
 */
public abstract class Frame {

    /** The scenes to draw in the frame */
    private final Collection<Scene> scenes;

    /** The background color of the frame */
    private final Color color;

    /** The width of the frame */
    private final int width;

    /** The height of the frame */
    private final int height;

    /**
     * @param scenes The scenes to draw in the frame
     * @param color The background color of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     */
    Frame(Collection<Scene> scenes, Color color, int width, int height) {

        this.scenes = scenes;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    /**
     * Reset the view then draw each scene to the active render context
     */
    public final void draw() {

        GLES20.glViewport(0, 0, width, height);

        GLES20.glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        for (Scene scene : scenes) {

            scene.draw();
        }
    }

    /**
     * Get the scenes in the frame
     *
     * @return The scenes in the frame
     */
    @NonNull
    public final Collection<Scene> getScenes() {

        return scenes;
    }

    /**
     * Get the background color of the frame
     *
     * @return The background color of the frame
     */
    @NonNull
    public final Color getColor() {

        return color;
    }

    /**
     * Get the width of the frame
     *
     * @return The width of the frame
     */
    public final int getWidth() {

        return width;
    }

    /**
     * Get the height of the frame
     *
     * @return The height of the frame
     */
    public final int getHeight() {

        return height;
    }

    /**
     * Get the duration of the frame
     *
     * @return The duration of the frame in milliseconds
     */
    public abstract long getDuration();

    /**
     * Check if the frame is dynamic
     *
     * @return <tt>true</tt> if the frame is dynamic, <tt>false</tt> otherwise
     */
    public abstract boolean isDynamic();

    /**
     * Attempt to update each scene in the frame
     *
     * @param time The current time in milliseconds
     *
     * @return The updated frame
     */
    @NonNull
    public abstract Frame update(long time);
}
