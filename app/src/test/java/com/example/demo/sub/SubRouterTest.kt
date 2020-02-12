package com.example.demo.sub

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SubRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: SubBuilder.Component
  @Mock internal lateinit var interactor: SubInteractor
  @Mock internal lateinit var view: SubView

  private var router: SubRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = SubRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

