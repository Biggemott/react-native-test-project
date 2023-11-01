package com.testproject.view

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.opengl.GLSurfaceView
import android.util.Log
import androidx.core.content.ContextCompat
import com.testproject.MainActivity
import com.testproject.MainApplication.LOG_TAG
import com.testproject.R
import com.testproject.view.renderer.NativeRenderer
import com.testproject.view.renderer.ShapeRenderer


class SimpleDrawingView(context: Context) : GLSurfaceView(context) {

    private val renderer = ShapeRenderer()
//    private val renderer = NativeRenderer()

    init {
        setEGLContextClientVersion(2)
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY
        renderer.setBackgroundColor(
            ContextCompat.getColor(getThemedContext(resources.configuration), R.color.main_background)
        )
    }

    var figureRotation = 0f
        set(value) {
            field = value
            renderer.angle = value
            requestRender()
        }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(LOG_TAG, "onAttachedToWindow")
        getActivity()?.bindGLSurfaceView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(LOG_TAG, "onDetachedFromWindow")
        getActivity()?.unbindGLSurfaceView(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(LOG_TAG, "onConfigurationChanged")
        renderer.setBackgroundColor(
            ContextCompat.getColor(getThemedContext(newConfig), R.color.main_background)
        )
        requestRender()
    }

    private fun getThemedContext(config: Configuration): Context {
        return context.createConfigurationContext(config)
    }

    private fun getActivity(): MainActivity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is MainActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }
}