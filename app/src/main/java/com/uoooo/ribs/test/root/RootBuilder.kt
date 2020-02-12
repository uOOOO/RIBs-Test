package com.uoooo.ribs.test.root

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.bangarharshit.ribsscreenstack.ScreenStack
import com.bangarharshit.ribsscreenstack.transition.DefaultTransition
import com.bangarharshit.ribsscreenstack.transition.Transition
import com.uoooo.ribs.test.main.MainBuilder
import com.uoooo.ribs.test.main.MainInteractor
import com.uoooo.ribs.test.sub.SubBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import com.uber.rib.core.lifecycle.ActivityCallbackEvent
import com.uber.rib.core.lifecycle.ActivityLifecycleEvent
import com.uoooo.ribs.test.R
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import io.reactivex.Observable
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link RootScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RootBuilder(dependency: ParentComponent) :
    ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RootRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RootRouter].
   */
  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.rootRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.root_rib, parentViewGroup, false) as RootView?
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
    fun activityLifecycle(): Lifecycle

    fun activityLifecycleEvent(): Observable<ActivityLifecycleEvent>
    fun activityCallbackEvent(): Observable<ActivityCallbackEvent>
  }

  @dagger.Module
  abstract class Module {

    @RootScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

    @dagger.Module
    companion object {

      @RootScope
      @Provides
      @JvmStatic
      internal fun mainViewListener(interactor: RootInteractor): MainInteractor.MainViewListener {
        return interactor.MainViewListenerImpl()
      }

      @RootScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RootView,
          interactor: RootInteractor,
          screenStack: ScreenStack
      ): RootRouter {
        return RootRouter(view, interactor, component, screenStack, MainBuilder(component),
            SubBuilder(component))
      }

      @RootScope
      @Provides
      @JvmStatic
      internal fun screenStack(view: RootView): ScreenStack {
        return ScreenStack(view,
            Provider<Transition> { DefaultTransition(DefaultTransition.NavigationType.SHOW) })
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @RootScope
  @dagger.Component(
      modules = [Module::class],
      dependencies = [ParentComponent::class]
  )
  interface Component : InteractorBaseComponent<RootInteractor>, BuilderComponent,
      MainBuilder.ParentComponent, SubBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun rootRouter(): RootRouter
  }

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class RootScope

  @Qualifier
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class RootInternal
}
