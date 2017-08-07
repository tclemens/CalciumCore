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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.tclemens.calcium.engine.updates.event.Event;
import net.tclemens.calcium.engine.updates.event.EventFactory;

/**
 * This class represents an action to be performed in the future
 *
 * @author Tim Clemens
 */
final class DelayedAction extends Action {

    /** The name of the action */
    private final String name;

    /** The time for the action to occur */
    private final long time;

    /**
     * @param name The name of the action
     * @param time The time for the action to occur
     */
    DelayedAction(String name, long time) {

        this.name = name;
        this.time = time;
    }

    @NonNull
    @Override
    public final Collection<Event> getEvents() {

        return Collections.emptyList();
    }

    @Override
    public final boolean isPending() {

        return true;
    }

    @NonNull
    @Override
    public final Action update(long time) {

        if (time < this.time) {

            return this;
        }

        Collection<Event> events = new ArrayList<>(1);
        events.add(EventFactory.createAction(time, name));
        events = Collections.unmodifiableCollection(events);

        return ActionFactory.buildPending(events);
    }
}
