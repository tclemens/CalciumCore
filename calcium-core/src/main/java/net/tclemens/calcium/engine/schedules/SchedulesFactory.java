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

import net.tclemens.calcium.engine.updates.Updates;

/**
 * This class is responsible for creating and initializing schedules modules
 *
 * @author Tim Clemens
 */
public final class SchedulesFactory {

    private SchedulesFactory() {
    }

    /**
     * Create a schedules module
     *
     * @param updates The updates module for the engine
     *
     * @return The new schedules module
     *
     * @throws IllegalArgumentException If the updates module is invalid
     */
    @NonNull
    public static Schedules createModule(@NonNull Updates updates) {

        if (updates == null) {

            throw new IllegalArgumentException("Unable to create a schedules module with a null updates module");
        }

        return buildModule(updates);
    }

    /**
     * Create a schedules module
     *
     * @param updates The updates module for the engine
     *
     * @return The new schedules module
     */
    static Schedules buildModule(Updates updates) {

        return new Schedules(updates);
    }
}
