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
 * This class represents an action which is being performed
 *
 * @author Tim Clemens
 */
final class PendingAction extends Action {

    /** The events to generate for the action */
    private final Collection<Event> events;

    /**
     * @param events The events to generate for the action
     */
    PendingAction(Collection<Event> events) {

        this.events = events;
    }

    @NonNull
    @Override
    public final Collection<Event> getEvents() {

        return events;
    }

    @Override
    public final boolean isPending() {

        return true;
    }

    @NonNull
    @Override
    public final Action update(long time) {

        return ActionFactory.buildComplete();
    }
}
