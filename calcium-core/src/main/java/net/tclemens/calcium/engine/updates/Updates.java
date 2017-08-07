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

package net.tclemens.calcium.engine.updates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import net.tclemens.calcium.engine.updates.base.Notifiable;
import net.tclemens.calcium.engine.updates.base.Updatable;
import net.tclemens.calcium.engine.updates.event.Event;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * This class is responsible for updating states for the engine
 *
 * @author Tim Clemens
 */
public final class Updates {

    /**
     * This class represents an asynchronous worker which updates the current state
     */
    private final class UpdateWorker implements Runnable {

        /** The event for current state to process */
        private final Event event;

        /**
         * @param event The event for current state to process
         */
        private UpdateWorker(Event event) {

            this.event = event;
        }

        @Override
        public void run() {

            try {

                state = state.update(context, event);

                for (Notifiable module : modules) {

                    module.notify(state);
                }
            }
            catch (Exception e) {

                Log.e("Updates", "An unhandled exception occurred", e);
            }
        }
    }

    /** The thread pool used to update the state */
    private final ExecutorService updateExecutor = Executors.newSingleThreadExecutor();

    /** the modules registered for notifications on state changes */
    private final Set<Notifiable> modules = new HashSet<>();

    /** The application context */
    private final Context context;

    /** The current updatable state */
    private volatile Updatable state;

    /**
     * @param context The application context
     * @param state The initial state the engine
     */
    Updates(Context context, Updatable state) {

        this.context = context;
        this.state = state;
    }

    /**
     * Register a module for notifications on state changes
     *
     * @param module The module to register
     */
    public final void register(@NonNull Notifiable module) {

        if (module == null) {

            throw new IllegalArgumentException("Unable to register a null module");
        }

        modules.add(module);
    }

    /**
     * Publish an event asynchronously
     *
     * @param event The event to handle
     *
     * @throws IllegalArgumentException If the event is invalid
     * @throws IllegalStateException If the module is shutdown
     */
    public final void handle(@NonNull Event event) {

        if (event == null) {

            throw new IllegalArgumentException("Unable to process a null event");
        }

        try {

            updateExecutor.submit(new UpdateWorker(event));
        }
        catch (RejectedExecutionException ignored) {

            throw new IllegalStateException("Unable to process updates after the module is shutdown");
        }
    }

    /**
     * Shutdown any active threads
     */
    public final void stop() {

        updateExecutor.shutdown();
    }
}
