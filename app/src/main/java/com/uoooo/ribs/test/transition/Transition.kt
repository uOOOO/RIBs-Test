package com.uoooo.ribs.test.transition

import android.view.View

interface Transition {
  fun animate(from: View?, to: View?, direction: Direction, callback: Callback?)

  interface Callback {
    fun onAnimationEnd()
  }

  enum class Direction(private val sign: Int) {
    FORWARD(1),
    BACKWARD(-1);

    fun sign(): Int {
      return sign
    }
  }
}