package com.uoooo.ribs.test.main

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class MainInteractor : Interactor<MainInteractor.MainPresenter, MainRouter>() {

  @Inject
  lateinit var presenter: MainPresenter
  @Inject
  lateinit var mainViewListener: MainViewListener

  private val disposeBag = CompositeDisposable()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
    disposeBag.add(
        presenter.showSubView()
            .subscribe { mainViewListener.showSubView() }
    )
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    disposeBag.clear()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainPresenter {
    fun showSubView(): Observable<Unit>
  }

  interface MainViewListener {
    fun showSubView()
  }
}
