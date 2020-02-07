package com.example.demo

import android.view.ViewGroup
import com.example.demo.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class RootActivity : RibActivity() {
  override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
    val rootBuilder = RootBuilder(object : RootBuilder.ParentComponent {})
    return rootBuilder.build(parentViewGroup)
  }
}