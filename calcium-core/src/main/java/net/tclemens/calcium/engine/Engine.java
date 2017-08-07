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
import android.view.View;

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
 * This class represents an instance of an engine
 *
 * @author Tim Clemens
 */
public final class Engine {

    private final EngineView view;

    Engine(EngineView view) {

        this.view = view;
    }

    /**
     * Get the view for the engine
     *
     * @return The view for the engine
     */
    @NonNull
    public final View getView() {

        return view;
    }

    /**
     * Start the engine
     *
     * @param state The initial state of the engine
     *
     * @throws IllegalArgumentException If the initial state is null
     */
    public final void start(@NonNull Updatable state) {

        if (state == null) {

            throw new IllegalArgumentException("Unable to start an engine with a null initial state");
        }

        Context context = view.getContext();
        Updates updates = UpdatesFactory.createModule(context, state);
        Saves saves = SavesFactory.createModule(context);
        Schedules schedules = SchedulesFactory.createModule(updates);
        Graphics graphics = GraphicsFactory.createModule(updates);

        updates.register(graphics);
        updates.register(schedules);
        updates.register(saves);

        EngineModel model = new EngineModel(updates, saves, schedules, graphics);

        view.setModel(model);
        view.onResume();
    }

    /**
     * Stop the engine
     */
    public final void stop() {

        view.onPause();
    }
}
