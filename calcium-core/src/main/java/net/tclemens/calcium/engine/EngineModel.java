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

import net.tclemens.calcium.engine.graphics.Graphics;
import net.tclemens.calcium.engine.saves.Saves;
import net.tclemens.calcium.engine.schedules.Schedules;
import net.tclemens.calcium.engine.updates.Updates;
import net.tclemens.calcium.engine.updates.event.Event;

/**
 * This class represents the internal state of the engine
 *
 * @author Tim Clemens
 */
final class EngineModel {

    /** The updates module of the engine */
    private final Updates updates;

    /** The saves module of the engine */
    private final Saves saves;

    /** The schedules module of the engine */
    private final Schedules schedules;

    /** The graphics module of the engine */
    private final Graphics graphics;

    /**
     * @param updates The updates module of the engine
     * @param saves The saves module of the engine
     * @param schedules The schedules module of the engine
     * @param graphics The graphics module of the engine
     */
    EngineModel(Updates updates,
                Saves saves,
                Schedules schedules,
                Graphics graphics) {

        this.updates = updates;
        this.saves = saves;
        this.schedules = schedules;
        this.graphics = graphics;
    }

    /**
     * Update the current state of the engine
     *
     * @param event The event to handle
     */
    final void update(Event event) {

        updates.handle(event);
    }

    /**
     * Draw the current state of the engine
     */
    final void draw() {

        graphics.draw();
    }

    /**
     * Stop each module of the engine
     */
    final void stop() {

        updates.stop();
        saves.stop();
        schedules.stop();
        graphics.stop();
    }
}
