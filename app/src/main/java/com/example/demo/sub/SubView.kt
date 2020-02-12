package com.example.demo.sub

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Top level view for {@link SubBuilder.SubScope}.
 */
class SubView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), SubInteractor.SubPresenter
