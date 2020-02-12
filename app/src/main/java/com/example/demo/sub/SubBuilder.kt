package com.example.demo.sub

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.demo.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link SubScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class SubBuilder(dependency: ParentComponent) :
    ViewBuilder<SubView, SubRouter, SubBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [SubRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [SubRouter].
   */
  fun build(parentViewGroup: ViewGroup): SubRouter {
    val view = createView(parentViewGroup)
    val interactor = SubInteractor()
    val component = DaggerSubBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.subRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SubView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.sub_rib, parentViewGroup, false) as SubView?
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @SubScope
    @Binds
    internal abstract fun presenter(view: SubView): SubInteractor.SubPresenter

    @dagger.Module
    companion object {

      @SubScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: SubView,
          interactor: SubInteractor): SubRouter {
        return SubRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @SubScope
  @dagger.Component(modules = arrayOf(Module::class),
      dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<SubInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: SubInteractor): Builder

      @BindsInstance
      fun view(view: SubView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun subRouter(): SubRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class SubScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class SubInternal
}
