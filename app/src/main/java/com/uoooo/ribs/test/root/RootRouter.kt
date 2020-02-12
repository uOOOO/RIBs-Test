package com.uoooo.ribs.test.root

import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bangarharshit.ribsscreenstack.ScreenStack
import com.uoooo.ribs.test.main.MainBuilder
import com.uoooo.ribs.test.sub.SubBuilder
import com.uoooo.ribs.test.sub.SubRouter

import com.uber.rib.core.ViewRouter
import com.uber.rib.core.screenstack.ViewProvider
import com.uoooo.ribs.test.main.MainRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val screenStack: ScreenStack,
    private val mainBuilder: MainBuilder,
    private val subBuilder: SubBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  companion object {
    val TAG = RootRouter::class.java.simpleName
  }

  fun attachMain() {
    screenStack.pushScreen(object : ViewProvider() {
      private var mainRouter: MainRouter? = null

      override fun buildView(parentView: ViewGroup?): View {
        if (mainRouter == null) {
          mainRouter = mainBuilder.build(view)
          attachChild(mainRouter)
        }
        return mainRouter!!.view
      }

      override fun doOnViewRemoved() {
        mainRouter?.run { detachChild(this) }
      }
    })
  }

  fun attachSub() {
    screenStack.pushScreen(object : ViewProvider() {
      private var subRouter: SubRouter? = null

      override fun buildView(parentView: ViewGroup?): View {
        if (subRouter == null) {
          subRouter = subBuilder.build(view)
          attachChild(subRouter)
        }
        return subRouter!!.view
      }

      override fun doOnViewRemoved() {
        subRouter?.run { detachChild(this) }
      }
    })
  }

  fun popStack(): Boolean {
    return (screenStack.size() > 0).also {
      screenStack.popScreen()
    }
  }
}
