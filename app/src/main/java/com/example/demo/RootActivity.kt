package com.example.demo

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.demo.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import com.uber.rib.core.lifecycle.ActivityLifecycleEvent
import dagger.Provides
import io.reactivex.Observable

class RootActivity : RibActivity() {
  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
    val rootBuilder = RootBuilder(Component())
    return rootBuilder.build(parentViewGroup)
  }

  private inner class Component : RootBuilder.ParentComponent {
    override fun activityLifecycle(): Lifecycle {
      return this@RootActivity.lifecycle
    }

    override fun activityLifecycleEvent(): Observable<ActivityLifecycleEvent> {
      return this@RootActivity.lifecycle()
    }
  }
}