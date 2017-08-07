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

package net.tclemens.calcium.engine.graphics.scene;

import android.support.annotation.NonNull;

import java.util.Collection;

import net.tclemens.calcium.engine.graphics.batch.Batch;
import net.tclemens.calcium.engine.graphics.camera.Camera;

/**
 * This class represents a scene with a static camera and static batches
 *
 * @author Tim Clemens
 */
final class StaticScene extends Scene {

    /**
     * @param camera The camera used to view the scene
     * @param batches The batches to draw in the scene
     */
    StaticScene(Camera camera, Collection<Batch> batches) {

        super(camera, batches);
    }

    @Override
    public final boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public final Scene update(long time) {

        return this;
    }
}
