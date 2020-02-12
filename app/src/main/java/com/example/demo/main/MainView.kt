package com.example.demo.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.demo.AnotherActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
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
//    button.setOnClickListener {
//      button.text = "CLICKED!!!"
//      (context as Activity).startActivityForResult(Intent(context, AnotherActivity::class.java), 0)
//    }
  }

  override fun showSubView(): Observable<Unit> {
    return button.clicks()
  }

  override fun onSaveInstanceState(): Parcelable? {
    val state = super.onSaveInstanceState()
    val newState = TestState(state)
    newState.value = this.button.text.toString()
    return newState
  }

  override fun onRestoreInstanceState(state: Parcelable?) {
    val testState = state as TestState
    super.onRestoreInstanceState(testState.superState)
    button.text = testState.value
  }

  private class TestState : BaseSavedState {
    var value: String? = null

    constructor(superState: Parcelable?) : super(superState)

    constructor(`in`: Parcel) : super(`in`) {
      value = `in`.readString()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
      super.writeToParcel(out, flags)
      out.writeString(value)
    }

    companion object CREATOR : Parcelable.Creator<TestState> {
      override fun createFromParcel(parcel: Parcel): TestState {
        return TestState(parcel)
      }

      override fun newArray(size: Int): Array<TestState?> {
        return arrayOfNulls(size)
      }
    }
  }
}
