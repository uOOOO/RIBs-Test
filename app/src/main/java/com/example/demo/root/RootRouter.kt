package com.example.demo.root

import android.view.View
import androidx.core.view.contains
import com.example.demo.main.MainBuilder
import com.example.demo.sub.SubBuilder
import com.example.demo.sub.SubRouter

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
    subRouter?.let {
      detachChild(it)
      view.removeView(it.view)
    }
  }

  override fun handleBackPress(): Boolean {
    subRouter?.let {
      if (view.contains(it.view)) {
        detachSub()
        return true
      }
    }
    return super.handleBackPress()
  }
}
