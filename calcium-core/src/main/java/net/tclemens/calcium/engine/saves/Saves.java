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

package net.tclemens.calcium.engine.saves;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

import net.tclemens.calcium.engine.saves.base.Savable;
import net.tclemens.calcium.engine.updates.base.Notifiable;
import net.tclemens.calcium.engine.updates.base.Updatable;

/**
 * This class is responsible for saving states for the engine
 *
 * @author Tim Clemens
 */
public final class Saves implements Notifiable {

    /**
     * This class represents an asynchronous worker which saves the current state
     */
    private final class SaveWorker implements Runnable {

        private SaveWorker() {
        }

        @Override
        public void run() {

            try {

                state.save(context);
            }
            catch (Exception e) {

                Log.e("Saves", "An unhandled exception occurred", e);
            }
            finally {

                saveLock.release();
            }
        }
    }

    /** The thread pool used to asynchronously save states */
    private final ExecutorService saveExecutor = Executors.newSingleThreadExecutor();

    /** The lock used to synchronize saving states */
    private final Semaphore saveLock = new Semaphore(2);

    /** The application context */
    private final Context context;

    /** The current savable state */
    private volatile Savable state;

    /**
     * @param context The application context
     */
    Saves(Context context) {

        this.context = context;
    }

    @Override
    public void notify(@NonNull Updatable state) {

        if (state == null) {

            throw new IllegalArgumentException("Unable to process a null state");
        }

        if (this.state != state && state instanceof Savable) {

            this.state = (Savable) state;

            if (saveLock.tryAcquire()) {

                try {

                    saveExecutor.submit(new SaveWorker());
                }
                catch (RejectedExecutionException ignored) {

                    saveLock.release();

                    throw new IllegalStateException("Unable to process saves after the module is shutdown");
                }
            }
        }
    }

    /**
     * Shutdown any active threads
     */
    public final void stop() {

        saveExecutor.shutdown();
    }
}
