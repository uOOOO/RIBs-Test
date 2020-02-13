package com.uoooo.ribs.test

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.*
import com.uoooo.ribs.test.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import com.uber.rib.core.lifecycle.ActivityCallbackEvent
import com.uber.rib.core.lifecycle.ActivityLifecycleEvent
import io.reactivex.Observable
import kotlinx.android.parcel.Parcelize

class RootActivity : RibActivity() {
  companion object {
    val TAG = RootActivity::class.java.simpleName
  }

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

    override fun activityCallbackEvent(): Observable<ActivityCallbackEvent> {
      return this@RootActivity.callbacks()
    }
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    Log.d(TAG, "onRestoreInstanceState")
  }

  private lateinit var model: TestViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    model = ViewModelProvider(
      this,
      SavedStateViewModelFactory(application, this)
    ).get(TestViewModel::class.java)
    model.data.observe(this, Observer {
      Log.d(TAG, "value = $it")
    })
    Log.d(TAG, "model = $model, ${model.get()}")
    model.data.postValue(model.data.value?.plus(1))
  }

  override fun onStart() {
    super.onStart()
    model.set()
  }

  @Parcelize
  data class TestData(val data: String) : Parcelable

  class TestViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    companion object {
      val TAG = TestViewModel::class.java.simpleName
      const val KEY_DATA = "key_data"
    }

    val data = MutableLiveData(0)

    fun set() {
      savedStateHandle.set(KEY_DATA, arrayListOf(TestData("data")))
    }

    fun get(): List<TestData>? = savedStateHandle.get<List<TestData>>(KEY_DATA)

    override fun onCleared() {
      super.onCleared()
      Log.d(TAG, "onCleared")
    }
  }
}