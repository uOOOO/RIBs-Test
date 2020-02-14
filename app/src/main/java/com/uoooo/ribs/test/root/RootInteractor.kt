package com.uoooo.ribs.test.root

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.uoooo.ribs.test.main.MainInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.lifecycle.ActivityCallbackEvent
import com.uber.rib.core.lifecycle.ActivityLifecycleEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
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

  private val disposeBag = CompositeDisposable()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
    router.attachMain()
    disposeBag.add(
        activityCallbackEvent.subscribe {
          Log.d("RootInteractor", "event = ${it.type}")
          if (it.type == ActivityCallbackEvent.Type.ACTIVITY_RESULT) {
            val result = it as ActivityCallbackEvent.ActivityResult
            Log.d("RootInteractor", "event = ${it.data}, ${it.requestCode}, ${it.resultCode}")
          }
        }
    )
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    disposeBag.clear()
  }

  override fun handleBackPress(): Boolean {
    if (router.view.isSubViewOnTop()) {
      router.detachSub()
      return true
    }
    return super.handleBackPress()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RootPresenter

  inner class MainViewListenerImpl : MainInteractor.MainViewListener {
    override fun showSubView() {
      Log.d("MainViewListenerImpl", "showSubView()")
      router.attachSub()
    }
  }
}
