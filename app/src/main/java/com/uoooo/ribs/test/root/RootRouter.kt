package com.uoooo.ribs.test.root

import androidx.core.view.contains
import com.uoooo.ribs.test.main.MainBuilder
import com.uoooo.ribs.test.sub.SubBuilder
import com.uoooo.ribs.test.sub.SubRouter

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
  private val mainBuilder: MainBuilder,
  private val subBuilder: SubBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  fun attachMain() {
    val mainRouter = mainBuilder.build(view)
    attachChild(mainRouter)
    view.addView(mainRouter.view)
  }

  private var subRouter: SubRouter? = null

  fun attachSub() {
    subRouter = subBuilder.build(view).also {
      attachChild(it)
      view.addView(it.view)
    }
  }

  fun detachSub() {
    subRouter = subRouter?.let {
      detachChild(it)
      view.removeView(it.view)
      return@let null
    }
  }
}
