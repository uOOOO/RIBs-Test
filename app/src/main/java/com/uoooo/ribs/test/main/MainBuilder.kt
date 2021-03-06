package com.uoooo.ribs.test.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import com.uoooo.ribs.test.R
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link MainScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class MainBuilder(dependency: ParentComponent) :
    ViewBuilder<MainView, MainRouter, MainBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MainRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MainRouter].
   */
  fun build(parentViewGroup: ViewGroup): MainRouter {
    val view = createView(parentViewGroup)
    val interactor = MainInteractor()
    val component = DaggerMainBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.mainRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MainView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.main_rib, parentViewGroup, false) as MainView?
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
    fun listener(): MainInteractor.MainViewListener
  }

  @dagger.Module
  abstract class Module {

    @MainScope
    @Binds
    internal abstract fun presenter(view: MainView): MainInteractor.MainPresenter

    @dagger.Module
    companion object {

      @MainScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: MainView,
          interactor: MainInteractor
      ): MainRouter {
        return MainRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @MainScope
  @dagger.Component(
      modules = [Module::class],
      dependencies = [ParentComponent::class]
  )
  interface Component : InteractorBaseComponent<MainInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: MainInteractor): Builder

      @BindsInstance
      fun view(view: MainView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun mainRouter(): MainRouter
  }

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class MainScope

  @Qualifier
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class MainInternal
}
