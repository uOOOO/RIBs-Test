package com.uoooo.ribs.test.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.uoooo.ribs.test.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.list_item.view.*
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
    list.layoutManager = LinearLayoutManager(context)
    list.adapter = TestAdapter()
  }

  class TestAdapter : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {
    private val data: List<String> = mutableListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P")

    class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      fun bind(data: String) {
        itemView.text.text = data
      }
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
      return TestViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
      holder.bind(data[position])
    }
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
