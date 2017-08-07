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
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import net.tclemens.calcium.engine.updates.event.EventFactory;
import net.tclemens.calcium.engine.updates.input.Key;
import net.tclemens.calcium.engine.updates.input.Touch;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * This class represents the external view of the engine
 *
 * @author Tim Clemens
 */
final class EngineView extends GLSurfaceView implements GLSurfaceView.Renderer {

    /** The model of the engine */
    private volatile EngineModel model;

    /**
     * @param context The application context of the engine
     */
    EngineView(Context context) {

        super(context);

        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public final void onResume() {

        super.onResume();
    }

    @Override
    public final void onPause() {

        if (model != null) {

            model.stop();
        }

        super.onPause();
    }

    @Override
    public final boolean onTouchEvent(MotionEvent event) {

        try {

            long time = event.getEventTime();

            float x = event.getX();
            float y = event.getY();

            model.update(EventFactory.createTouch(time, x, y, Touch.parseEvent(event)));
        }
        catch (Exception e) {

            Log.e("Engine", "An unhandled exception occurred", e);
        }

        return true;
    }

    @Override
    public final boolean onKeyDown(int code, KeyEvent event) {

        try {

            if (model != null) {

                long time = event.getEventTime();

                model.update(EventFactory.createKey(time, code, Key.parseEvent(event)));
            }
        }
        catch (Exception e) {

            Log.e("Engine", "An unhandled exception occurred", e);
        }

        return true;
    }

    @Override
    public final boolean onKeyUp(int code, KeyEvent event) {

        try {

            if (model != null) {

                long time = event.getEventTime();

                model.update(EventFactory.createKey(time, code, Key.parseEvent(event)));
            }
        }
        catch (Exception e) {

            Log.e("Engine", "An unhandled exception occurred", e);
        }

        return true;
    }

    @Override
    public final void onSurfaceCreated(GL10 gl, EGLConfig config) {
    }

    @Override
    public final void onSurfaceChanged(GL10 gl, int width, int height) {

        try {

            if (model != null) {

                long time = System.currentTimeMillis();

                model.update(EventFactory.createView(time, width, height));
            }
        }
        catch (Exception e) {

            Log.e("Engine", "An unhandled exception occurred", e);
        }
    }

    @Override
    public final void onDrawFrame(GL10 gl) {

        try {

            if (model != null) {

                model.draw();
            }
        }
        catch (Exception e) {

            Log.e("Engine", "An unhandled exception occurred", e);
        }
    }

    /**
     * Set the model of the engine
     */
    final void setModel(EngineModel model) {

        if (this.model != null) {

            this.model.stop();
        }

        this.model = model;
    }
}
