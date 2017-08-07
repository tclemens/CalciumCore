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

package net.tclemens.calcium.engine.graphics;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import net.tclemens.calcium.engine.graphics.base.Renderable;
import net.tclemens.calcium.engine.graphics.frame.Frame;
import net.tclemens.calcium.engine.updates.Updates;
import net.tclemens.calcium.engine.updates.base.Notifiable;
import net.tclemens.calcium.engine.updates.base.Updatable;
import net.tclemens.calcium.engine.updates.event.EventFactory;

/**
 * This class is responsible for refreshing graphics for the engine
 *
 * @author Tim Clemens
 */
public final class Graphics implements Notifiable {

    /**
     * This class represents an asynchronous worker which renders the current state
     */
    private class RenderWorker implements Runnable {

        private RenderWorker() {
        }

        @Override
        public void run() {

            try {

                key = state.render();

                try {

                    animateExecutor.submit(new AnimateWorker(key, key));
                }
                catch (RejectedExecutionException ignored) {

                    throw new IllegalStateException("Unable to process graphics after the module is shutdown");
                }
            }
            catch (Exception e) {

                Log.e("Graphics", "An unhandled exception occurred", e);
            }
            finally {

                renderLock.release();
            }
        }
    }

    /**
     * This class represents an asynchronous worker which attempts to animate the current frame
     */
    private class AnimateWorker implements Runnable {

        /** The first frame of the animation */
        private final Frame first;

        /** The current frame of the animation */
        private final Frame current;

        /**
         * @param first The first frame of the animation
         * @param current The current frame of the animation
         */
        private AnimateWorker(Frame first, Frame current) {

            this.first = first;
            this.current = current;
        }

        @Override
        public void run() {

            try {

                long time = System.currentTimeMillis();

                if (key == first) {

                    frame = current.update(time);

                    if (frame.isDynamic()) {

                        long delay = time + frame.getDuration() - System.currentTimeMillis();

                        try {

                            animateExecutor.schedule(new AnimateWorker(first, frame), delay, TimeUnit.MILLISECONDS);
                        }
                        catch (RejectedExecutionException ignored) {

                            throw new IllegalStateException("Unable to process graphics after the module is shutdown");
                        }
                    }
                    else {

                        updates.handle(EventFactory.createAnimation(time));
                    }

                    frameLock.drainPermits();
                    frameLock.release();
                }
            }
            catch (Exception e) {

                Log.e("Graphics", "An unhandled exception occurred", e);
            }
        }
    }

    /** The thread pool used to asynchronously render states */
    private final ExecutorService renderExecutor = Executors.newSingleThreadExecutor();

    /** The thread pool used to asynchronously animate frames */
    private final ScheduledExecutorService animateExecutor = Executors.newSingleThreadScheduledExecutor();

    /** The lock used to synchronize rendering states */
    private final Semaphore renderLock = new Semaphore(2);

    /** The lock used to prevent redrawing frames */
    private final Semaphore frameLock = new Semaphore(0);

    /** The updates module for the engine */
    private final Updates updates;

    /** The current renderable state */
    private volatile Renderable state;

    /** The current key frame */
    private volatile Frame key;

    /** The current drawable frame */
    private volatile Frame frame;

    /**
     * @param updates The updates module for the engine
     */
    Graphics(Updates updates) {

        this.updates = updates;
    }

    @Override
    public final void notify(@NonNull Updatable state) {

        if (state == null) {

            throw new IllegalArgumentException("Unable to process a null state");
        }

        if (this.state != state && state instanceof Renderable) {

            this.state = (Renderable) state;

            try {

                renderExecutor.submit(new RenderWorker());
            }
            catch (RejectedExecutionException ignored) {

                renderLock.release();

                throw new IllegalStateException("Unable to process graphics after the module is shutdown");
            }
        }
    }

    /**
     * Attempt to draw a new frame in the active render context
     */
    public final void draw() {

        if (frameLock.tryAcquire() && frame != null) {

            frame.draw();
        }
    }

    /**
     * Shutdown any active threads
     */
    public final void stop() {

        renderExecutor.shutdown();
        animateExecutor.shutdown();
    }
}
