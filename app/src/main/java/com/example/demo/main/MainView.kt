package com.example.demo.main

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle), MainInteractor.MainPresenter
