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

import net.tclemens.calcium.engine.updates.event.Event;

/**
 * This class represents a set future events scheduled by the engine
 *
 * @author Tim Clemens
 */
public abstract class Action {

    Action() {
    }

    /**
     * Get the pending events for the action
     *
     * @return The pending events for the action
     */
    @NonNull
    public abstract Collection<Event> getEvents();

    /**
     * Check if the action is pending
     *
     * @return <tt>true</tt> if the action is pending, <tt>false</tt> otherwise
     */
    public abstract boolean isPending();

    /**
     * Attempt to update the action
     *
     * @param time The current time in milliseconds
     *
     * @return The updated action
     */
    @NonNull
    public abstract Action update(long time);
}
