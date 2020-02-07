package com.example.demo.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.demo.AnotherActivity
import kotlinx.android.synthetic.main.main_rib.view.*

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), MainInteractor.MainPresenter {

  override fun onFinishInflate() {
    super.onFinishInflate()
    button.setOnClickListener {
      (context as Activity).startActivityForResult(Intent(context, AnotherActivity::class.java), 0)
    }
  }
}
