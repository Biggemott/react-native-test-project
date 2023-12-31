package com.testproject

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext

class ViewPackage : ReactPackage {

    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ) = mutableListOf(SimpleDrawingViewManager())

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = mutableListOf()
}