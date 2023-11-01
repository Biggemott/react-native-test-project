package com.testproject

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.testproject.view.SimpleDrawingView

class SimpleDrawingViewManager(
) : SimpleViewManager<SimpleDrawingView>() {

    companion object {
        const val REACT_CLASS = "SimpleDrawingView"
    }

    override fun getName() = REACT_CLASS
    override fun createViewInstance(context: ThemedReactContext): SimpleDrawingView {
        return SimpleDrawingView(context)
    }

    @ReactProp(name = "figure_rotation", defaultFloat = 0f)
    fun setFigureRotation(view: SimpleDrawingView, value: Float) {
        view.figureRotation = value
    }
}