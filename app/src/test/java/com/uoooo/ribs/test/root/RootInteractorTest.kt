package com.uoooo.ribs.test.root

import androidx.lifecycle.Lifecycle
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper
import com.uber.rib.core.lifecycle.ActivityCallbackEvent
import com.uber.rib.core.lifecycle.ActivityLifecycleEvent
import io.reactivex.Observable

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RootInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: RootInteractor.RootPresenter
  @Mock internal lateinit var router: RootRouter
  @Mock internal lateinit var activityLifecycle: Lifecycle
  @Mock internal lateinit var activityLifecycleEvent: Observable<ActivityLifecycleEvent>
  @Mock internal lateinit var activityCallbackEvent: Observable<ActivityCallbackEvent>

  private var interactor: RootInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestRootInteractor.create(
      presenter,
      activityLifecycle,
      activityLifecycleEvent,
      activityCallbackEvent
    )
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<RootInteractor.RootPresenter, RootRouter>(
      interactor!!,
      presenter,
      router,
      null
    )
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}