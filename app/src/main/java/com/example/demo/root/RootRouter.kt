package com.example.demo.root

import android.view.View
import com.example.demo.main.MainBuilder

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val mainBuilder: MainBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  fun attachMain() {
    val mainRouter = mainBuilder.build(view)
    attachChild(mainRouter)
    view.addView(mainRouter.view)
  }
}
