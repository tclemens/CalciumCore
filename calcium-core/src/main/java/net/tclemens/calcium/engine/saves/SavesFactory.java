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

/**
 * This class is responsible for creating and initializing saves modules
 *
 * @author Tim Clemens
 */
public final class SavesFactory {

    private SavesFactory() {
    }

    /**
     * Create a saves module
     *
     * @param context The application context
     *
     * @return The new saves module
     *
     * @throws IllegalArgumentException If the application context is invalid
     */
    @NonNull
    public static Saves createModule(@NonNull Context context) {

        if (context == null) {

            throw new IllegalArgumentException("Unable to create a saves module with a null application context");
        }

        return buildModule(context);
    }

    /**
     * Create a saves module
     *
     * @param context The application context
     *
     * @return The new saves module
     */
    static Saves buildModule(Context context) {

        return new Saves(context);
    }
}
