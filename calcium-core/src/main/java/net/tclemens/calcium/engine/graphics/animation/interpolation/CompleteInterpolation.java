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

package net.tclemens.calcium.engine.graphics.animation.interpolation;

import android.support.annotation.NonNull;

/**
 * This class represents an complete interpolation from 0 to 1
 *
 * @author Tim Clemens
 */
final class CompleteInterpolation extends Interpolation {

    CompleteInterpolation() {
    }

    @Override
    public final float getValue() {

        return 1f;
    }

    @Override
    public final boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public final Interpolation update(long time) {

        return this;
    }

    @NonNull
    @Override
    public final Interpolation finish() {

        return this;
    }
}
