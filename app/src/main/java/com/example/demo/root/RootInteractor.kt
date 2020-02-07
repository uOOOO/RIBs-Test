package com.example.demo.root

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.example.demo.main.MainInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.lifecycle.ActivityCallbackEvent
import com.uber.rib.core.lifecycle.ActivityLifecycleEvent
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

  @Inject
  lateinit var presenter: RootPresenter

  @Inject
  lateinit var activityLifecycle: Lifecycle

  @Inject
  lateinit var activityLifecycleEvent: Observable<ActivityLifecycleEvent>

  @Inject
  lateinit var activityCallbackEvent: Observable<ActivityCallbackEvent>

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
    router.attachMain()
    activityCallbackEvent.subscribe {
      Log.d("RootInteractor", "event = ${it.type}")
    }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RootPresenter

  class TestListenerImpl : MainInteractor.TestListener {
    override fun test() {
      Log.d("TestListenerImpl", "test()")
    }
  }
}
