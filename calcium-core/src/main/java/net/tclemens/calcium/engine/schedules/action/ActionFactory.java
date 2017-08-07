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

package net.tclemens.calcium.engine.schedules.action;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.engine.updates.event.Event;

/**
 * This class is responsible for creating and initializing scheduled actions
 *
 * @author Tim Clemens
 */
public final class ActionFactory {

    /** The singleton for a complete action */
    private static final Action COMPLETE_ACTION = new CompleteAction();

    private ActionFactory() {
    }

    /**
     * Create an immediate action
     *
     * @param name The name of the action
     *
     * @return The new action
     *
     * @throws IllegalArgumentException If the action name is invalid
     */
    @NonNull
    public static Action createImmediate(@NonNull String name) {

        if (name == null) {

            throw new IllegalArgumentException("Unable to create an action with a null name string");
        }

        return new ImmediateAction(name);
    }

    /**
     * Create a delayed action
     *
     * @param name The name of the action
     * @param time The time for the action to occur
     *
     * @return The new action
     *
     * @throws IllegalArgumentException If the action name is invalid
     */
    @NonNull
    public static Action createDelayed(@NonNull String name, long time) {

        if (name == null) {

            throw new IllegalArgumentException("Unable to create an action with a null name string");
        }

        if (time < 0) {

            throw new IllegalArgumentException("Unable to create an action with a negative time");
        }

        return new DelayedAction(name, time);
    }

    /**
     * Create a composite action
     *
     * @param actions The composite actions
     *
     * @return The new action
     *
     * @throws IllegalArgumentException If the action name is invalid
     */
    @NonNull
    public static Action createComposite(@NonNull Collection<Action> actions) {

        if (actions == null) {

            throw new IllegalArgumentException("Unable to create an action with a null action collection");
        }

        if (actions.contains(null)) {

            throw new IllegalArgumentException("Unable to create an action null actions");
        }

        Collection<Event> events = Collections.emptyList();

        actions = Collections.unmodifiableCollection(actions);

        return buildComposite(actions, events);
    }

    /**
     * Create an immediate action
     *
     * @return The new action
     */
    @NonNull
    static Action buildImmediate(String name) {

        return new ImmediateAction(name);
    }

    /**
     * Create a delayed action
     *
     * @return The new action
     */
    @NonNull
    static Action buildDelayed(String name, long time) {

        return new DelayedAction(name, time);
    }

    /**
     * Create a composite action
     *
     * @return The new action
     */
    @NonNull
    static Action buildComposite(Collection<Action> actions, Collection<Event> events) {

        return new CompositeAction(actions, events);
    }

    /**
     * Create a pending action
     *
     * @return The new action
     */
    @NonNull
    static Action buildPending(Collection<Event> events) {

        return new PendingAction(events);
    }

    /**
     * Create a complete action
     *
     * @return The new action
     */
    @NonNull
    static Action buildComplete() {

        return COMPLETE_ACTION;
    }
}
