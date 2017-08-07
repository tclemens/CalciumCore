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

import net.tclemens.calcium.engine.updates.base.Updatable;

/**
 * This class is responsible for creating and initializing updates modules
 *
 * @author Tim Clemens
 */
public final class UpdatesFactory {

    private UpdatesFactory() {
    }

    /**
     * Create an updates module
     *
     * @param context The application context
     * @param state The initial state the engine
     *
     * @return The new updates module
     *
     * @throws IllegalArgumentException If the application context or initial state is invalid
     */
    @NonNull
    public static Updates createModule(@NonNull Context context, @NonNull Updatable state) {

        if (context == null) {

            throw new IllegalArgumentException("Application context must exist");
        }

        if (state == null) {

            throw new IllegalArgumentException("Initial state must exist");
        }

        return buildModule(context, state);
    }

    /**
     * Create an updates module
     *
     * @param context The application context
     * @param state The initial state the engine
     *
     * @return The new updates module
     */
    static Updates buildModule(Context context, Updatable state) {

        return new Updates(context, state);
    }
}
