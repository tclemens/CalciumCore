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

package net.tclemens.calcium.engine.updates.event;

import android.support.annotation.NonNull;

/**
 * This class represents the occurrence of a scheduled action
 *
 * @author Tim Clemens
 */
public final class ActionEvent extends Event {

    /** The name of the scheduled action */
    private final String name;

    /**
     * @param time The time of the event in milliseconds
     * @param name The name of the scheduled action
     */
    ActionEvent(long time, String name) {

        super(time);

        this.name = name;
    }

    /**
     * Get the name of the scheduled action
     *
     * @return The name of the scheduled action
     */
    @NonNull
    public final String getName() {

        return name;
    }
}
