#include <jni.h>
#include <android/log.h>

#define LOG_TAG "TEST_PROJECT_CPP"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C" {
    JNIEXPORT void JNICALL Java_com_testproject_view_renderer_NativeRenderer_init(
            JNIEnv * env, jobject obj, jint width, jint height)
    {
        LOGD("init (%d, %d)", width, height);
    }

    JNIEXPORT void JNICALL Java_com_testproject_view_renderer_NativeRenderer_step(
            JNIEnv * env, jobject obj)
    {
        LOGD("step");
    }
}