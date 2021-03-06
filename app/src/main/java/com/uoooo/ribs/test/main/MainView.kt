package com.uoooo.ribs.test.main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
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
  }

  override fun showSubView(): Observable<Unit> {
    return button.clicks()
      .doOnNext {
        button.text = "MAIN CLICKED!!!"
      }
  }

//  override fun onSaveInstanceState(): Parcelable? {
//    val state = super.onSaveInstanceState()
//    val newState = TestState(state)
//    newState.value = this.button.text.toString()
//    return newState
//  }
//
//  override fun onRestoreInstanceState(state: Parcelable?) {
//    val testState = state as TestState
//    super.onRestoreInstanceState(testState.superState)
//    button.text = testState.value
//  }
//
//  private class TestState : BaseSavedState {
//    var value: String? = null
//
//    constructor(superState: Parcelable?) : super(superState)
//
//    constructor(`in`: Parcel) : super(`in`) {
//      value = `in`.readString()
//    }
//
//    override fun writeToParcel(out: Parcel, flags: Int) {
//      super.writeToParcel(out, flags)
//      out.writeString(value)
//    }
//
//    companion object CREATOR : Parcelable.Creator<TestState> {
//      override fun createFromParcel(parcel: Parcel): TestState {
//        return TestState(parcel)
//      }
//
//      override fun newArray(size: Int): Array<TestState?> {
//        return arrayOfNulls(size)
//      }
//    }
//  }
}
