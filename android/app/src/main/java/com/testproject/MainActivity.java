package com.testproject;

import static com.testproject.MainApplication.LOG_TAG;

import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.util.Log;

import androidx.lifecycle.Lifecycle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends ReactActivity {

    private Set<GLSurfaceView> surfaceViews = new HashSet<>();

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "TestProject";
    }

    /**
     * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
     * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
     * (aka React 18) with two boolean flags.
     */
    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new DefaultReactActivityDelegate(
                this,
                getMainComponentName(),
                // If you opted-in for the New Architecture, we enable the Fabric Renderer.
                DefaultNewArchitectureEntryPoint.getFabricEnabled());
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (GLSurfaceView view : surfaceViews) {
            callOnResume(view);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (GLSurfaceView view : surfaceViews) {
            callOnPause(view);
        }
    }

    public void bindGLSurfaceView(GLSurfaceView view) {
        Log.d(LOG_TAG, "bindGLSurfaceView");
        surfaceViews.add(view);

        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            callOnResume(view);
        } else {
            callOnPause(view);
        }
    }

    public void unbindGLSurfaceView(GLSurfaceView view) {
        Log.d(LOG_TAG, "unbindGLSurfaceView");
        surfaceViews.remove(view);
    }

    private void callOnResume(GLSurfaceView view) {
        Log.d(LOG_TAG, "calling view onResume");
        view.onResume();
    }

    private void callOnPause(GLSurfaceView view) {
        Log.d(LOG_TAG, "calling view onPause");
        view.onPause();
    }
}
