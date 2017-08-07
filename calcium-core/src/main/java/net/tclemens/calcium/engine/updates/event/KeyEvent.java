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

import net.tclemens.calcium.engine.updates.input.Key;

/**
 * This class represents an input from a key
 *
 * @author Tim Clemens
 */
public final class KeyEvent extends Event {

    /** The code of the key */
    private final int code;

    /** The type of input performed on the key */
    private final Key input;

    /**
     * @param time The time of the event in milliseconds
     * @param code The code of the key
     * @param input The type of input performed on the key
     */
    KeyEvent(long time, int code, Key input) {

        super(time);

        this.code = code;
        this.input = input;
    }

    /**
     * Get the code of the key
     *
     * @return The code of the key
     */
    public final int getCode() {

        return code;
    }

    /**
     * Get the type of input performed on the key
     *
     * @return The type of input performed on the key
     */
    public final Key getInput() {

        return input;
    }
}
