package com.uoooo.ribs.test.root

import androidx.core.view.contains
import com.uoooo.ribs.test.main.MainBuilder
import com.uoooo.ribs.test.sub.SubBuilder
import com.uoooo.ribs.test.sub.SubRouter

import com.uber.rib.core.ViewRouter
import com.uoooo.ribs.test.main.MainRouter
import com.uoooo.ribs.test.transition.DefaultTransition
import com.uoooo.ribs.test.transition.Transition
import com.uoooo.ribs.test.transition.Views

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

  private var mainRouter: MainRouter? = null
  private var subRouter: SubRouter? = null

  fun attachMain() {
    mainRouter = mainBuilder.build(view).also {
      attachChild(it)
      view.addView(it.view)
    }
  }

  fun attachSub() {
    subRouter = subBuilder.build(view).also {
      attachChild(it)
      view.addView(it.view)
      Views.whenMeasured(it.view, Views.OnMeasured {
        DefaultTransition(DefaultTransition.NavigationType.TYPE_A).animate(mainRouter?.view, it.view, Transition.Direction.FORWARD, null)
      })
    }
  }

  fun detachSub() {
    subRouter = subRouter?.let {
      detachChild(it)
      Views.whenMeasured(it.view, Views.OnMeasured {
        DefaultTransition(DefaultTransition.NavigationType.TYPE_A).animate(it.view, mainRouter?.view, Transition.Direction.BACKWARD, object : Transition.Callback {
          override fun onAnimationEnd() {
            view.removeView(it.view)
          }
        })
      })
      return@let null
    }
  }
}
