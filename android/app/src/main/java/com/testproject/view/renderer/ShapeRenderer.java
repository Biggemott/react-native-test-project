package com.testproject.view.renderer;

import static com.testproject.MainApplication.LOG_TAG;

import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ShapeRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = LOG_TAG + ":Renderer";
    private Triangle mTriangle;
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    private float mAngle;

    private Integer mBackgroundColor = null;
    private boolean isBackgroundDirty = false;

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        drawBackground();
        mTriangle = new Triangle();
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        if (isBackgroundDirty) drawBackground();

        float[] scratch = new float[16];
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, 1.0f);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        mTriangle.draw(scratch);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        isBackgroundDirty = true;
    }

    private void drawBackground() {
        if (mBackgroundColor != null) {
            GLES20.glClearColor(
                    Color.red(mBackgroundColor) / 255f,
                    Color.green(mBackgroundColor) / 255f,
                    Color.blue(mBackgroundColor) / 255f,
                    Color.alpha(mBackgroundColor) / 255f
            );
        } else {
            GLES20.glClearColor(1f, 1f, 1f, 1f);
        }
        isBackgroundDirty = false;
    }
}