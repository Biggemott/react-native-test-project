package com.testproject.view.renderer

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class NativeRenderer: GLSurfaceView.Renderer {

    private companion object {
        init {
            System.loadLibrary("testproject")
        }
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        init(width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        step()
    }


    private external fun init(width: Int, height: Int)

    private external fun step()
}