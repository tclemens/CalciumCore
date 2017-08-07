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

package net.tclemens.calcium.engine;

import android.content.Context;
import android.support.annotation.NonNull;

import net.tclemens.calcium.engine.graphics.Graphics;
import net.tclemens.calcium.engine.graphics.GraphicsFactory;
import net.tclemens.calcium.engine.saves.Saves;
import net.tclemens.calcium.engine.saves.SavesFactory;
import net.tclemens.calcium.engine.schedules.Schedules;
import net.tclemens.calcium.engine.schedules.SchedulesFactory;
import net.tclemens.calcium.engine.updates.Updates;
import net.tclemens.calcium.engine.updates.UpdatesFactory;
import net.tclemens.calcium.engine.updates.base.Updatable;

/**
 * This class is responsible for creating and initializing engines
 *
 * @author Tim Clemens
 */
public final class EngineFactory {

    private EngineFactory() {
    }

    /**
     * Create and validate an engine
     *
     * @param context The application context of the engine
     *
     * @return the new engine
     *
     * @throws IllegalArgumentException If the application context is null
     */
    @NonNull
    public static Engine createEngine(@NonNull Context context) {

        if (context == null) {

            throw new IllegalArgumentException("EngineFactory: Unable to create an engine with a null application context");
        }

        return buildEngine(context);
    }

    /**
     * Create and validate an engine
     *
     * @param context The application context of the engine
     *
     * @return the new engine
     */
    static Engine buildEngine(Context context) {

        EngineView view = new EngineView(context);

        return new Engine(view);
    }
}
