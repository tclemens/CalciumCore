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

package net.tclemens.calcium.engine.saves.base;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * This interface is required to allow the engine to save a state
 *
 * @author Tim Clemens
 * @since  1.0
 */
public interface Savable {

    /**
     * Allow the current state to save itself
     *
     * @param context The application context
     */
    void save(@NonNull Context context);
}
