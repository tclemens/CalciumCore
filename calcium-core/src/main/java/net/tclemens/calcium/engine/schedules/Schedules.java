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

package net.tclemens.calcium.engine.schedules;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import net.tclemens.calcium.engine.schedules.action.Action;
import net.tclemens.calcium.engine.schedules.action.ActionFactory;
import net.tclemens.calcium.engine.schedules.base.Schedulable;
import net.tclemens.calcium.engine.updates.Updates;
import net.tclemens.calcium.engine.updates.base.Notifiable;
import net.tclemens.calcium.engine.updates.base.Updatable;
import net.tclemens.calcium.engine.updates.event.Event;

/**
 * This class is responsible for scheduling future actions for the engine
 *
 * @author Tim Clemens
 */
public final class Schedules implements Notifiable {

    /**
     * This class represents an asynchronous worker which schedules actions from the current state
     */
    private final class ScheduleWorker implements Runnable {

        private ScheduleWorker() {
        }

        @Override
        public void run() {

            try {

                Action next = state.schedule();

                actionLock.acquire();

                try {

                    if (action == null) {

                        action = next;
                    }
                    else if (action != next) {

                        Collection<Action> actions = new ArrayList<>(2);

                        actions.add(action);
                        actions.add(next);

                        action = ActionFactory.createComposite(actions);
                    }

                    if (updateLock.tryAcquire()) {

                        try {

                            updateExecutor.submit(new UpdateWorker());
                        }
                        catch (RejectedExecutionException e) {

                            throw new IllegalStateException("Unable to process schedules after the module is shutdown");
                        }
                        finally {

                            updateLock.release();
                        }
                    }
                }
                finally {

                    actionLock.release();
                }
            }
            catch (InterruptedException ignored) {
            }
            catch (Exception e) {

                Log.e("Schedules", "An unhandled exception occurred", e);
            }
        }
    }

    /**
     * This class represents an asynchronous worker which handles events from scheduled actions on the current state
     */
    private final class UpdateWorker implements Runnable {

        private UpdateWorker() {
        }

        @Override
        public void run() {

            try {

                long time = System.currentTimeMillis();

                actionLock.acquire();

                try {

                    action = action.update(time);

                    for (Event event : action.getEvents()) {

                        updates.handle(event);
                    }

                    if (action.isPending()) {

                        long duration = 1000 / 60;
                        long delay = time + duration - System.currentTimeMillis();

                        try {

                            updateExecutor.schedule(new UpdateWorker(), delay, TimeUnit.MILLISECONDS);
                        }
                        catch (RejectedExecutionException e) {

                            updateLock.release();

                            throw new IllegalStateException("Unable to process schedules after the module is shutdown");
                        }
                    }
                    else {

                        updateLock.release();
                    }
                }
                finally {

                    actionLock.release();
                }
            }
            catch (InterruptedException ignored) {
            }
            catch (Exception e) {

                Log.e("Schedules", "An unhandled exception occurred", e);
            }
        }
    }

    /** The thread pool used to asynchronously schedule future actions */
    private final ExecutorService scheduleExecutor = Executors.newSingleThreadExecutor();

    /** The thread pool used to asynchronously perform scheduled actions */
    private final ScheduledExecutorService updateExecutor = Executors.newSingleThreadScheduledExecutor();

    /** The lock used to synchronize handling events from scheduled actions */
    private final Semaphore updateLock = new Semaphore(1);

    /** The lock used to synchronize access to scheduled actions */
    private final Semaphore actionLock = new Semaphore(1);

    /** The updates module for the engine */
    private final Updates updates;

    /** The current schedulable state */
    private volatile Schedulable state;

    /** The current scheduled action */
    private volatile Action action;

    /**
     * @param updates The updates module for the engine
     */
    Schedules(Updates updates) {

        this.updates = updates;
    }

    @Override
    public void notify(@NonNull Updatable state) {

        if (state == null) {

            throw new IllegalArgumentException("Unable to process a null state");
        }

        if (this.state != state && state instanceof Schedulable) {

            this.state = (Schedulable) state;

            try {

                scheduleExecutor.submit(new ScheduleWorker());
            }
            catch (RejectedExecutionException ignored) {

                throw new IllegalStateException("Unable to process schedules after the module is shutdown");
            }
        }
    }

    /**
     * Shutdown any active threads
     */
    public final void stop() {

        scheduleExecutor.shutdown();
        updateExecutor.shutdown();
    }
}
