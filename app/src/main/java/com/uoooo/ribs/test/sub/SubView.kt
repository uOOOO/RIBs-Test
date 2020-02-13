package com.uoooo.ribs.test.sub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.FrameLayout
import com.uoooo.ribs.test.AnotherActivity
import kotlinx.android.synthetic.main.sub_rib.view.*

/**
 * Top level view for {@link SubBuilder.SubScope}.
 */
class SubView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), SubInteractor.SubPresenter {
  override fun onFinishInflate() {
    super.onFinishInflate()
    button.setOnClickListener {
      button.text = "SUB CLICKED!!!"
      (context as Activity).startActivityForResult(Intent(context, AnotherActivity::class.java), 0)
    }
  }
}
