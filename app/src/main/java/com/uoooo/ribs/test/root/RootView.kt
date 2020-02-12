package com.uoooo.ribs.test.root

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.uoooo.ribs.test.sub.SubView

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), RootInteractor.RootPresenter {

  fun isSubViewOnTop(): Boolean {
    return getChildAt(childCount - 1) is SubView
  }
}
