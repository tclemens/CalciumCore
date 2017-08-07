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

package net.tclemens.calcium.engine.updates.base;

import android.content.Context;
import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.updates.event.Event;

/**
 * This interface is required to allow the engine to update a state
 *
 * @author Tim Clemens
 * @since  1.0
 */
public interface Updatable {

    /**
     * Update the current state of the engine
     *
     * @param context The application state
     * @param event The event from the engine
     *
     * @return The updated state
     */
    @NonNull
    Updatable update(@NonNull Context context, @NonNull Event event);
}
