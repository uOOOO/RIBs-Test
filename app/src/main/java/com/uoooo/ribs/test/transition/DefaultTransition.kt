package com.uoooo.ribs.test.transition

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View

class DefaultTransition(private val navigationType: NavigationType) : Transition {

  override fun animate(from: View?, to: View?, direction: Transition.Direction,
      callback: Transition.Callback?) {
    val animator = createAnimator(from, to, direction)
    animator.addListener(object : AnimatorListenerAdapter() {
      override fun onAnimationEnd(animation: Animator) {
        callback?.onAnimationEnd()
      }
    })
    animator.start()
  }

  private fun createAnimator(from: View?, to: View?, direction: Transition.Direction): AnimatorSet {
    val axis: Property<View, Float>
    val fromTranslation: Int
    val toTranslation: Int
    val sign = direction.sign()
    when (navigationType) {
      NavigationType.TYPE_A -> {
        axis = View.TRANSLATION_X
//        fromTranslation = sign * -(from?.width ?: 0)
//        toTranslation = sign * (to?.width ?: 0)
        fromTranslation = if (direction === Transition.Direction.FORWARD) 0 else from?.width ?: 0
        toTranslation = if (direction === Transition.Direction.BACKWARD) 0 else to?.width ?: 0
      }
      NavigationType.TYPE_B -> {
        axis = View.TRANSLATION_Y
        fromTranslation = if (direction === Transition.Direction.FORWARD) 0 else from?.height ?: 0
        toTranslation = if (direction === Transition.Direction.BACKWARD) 0 else to?.height ?: 0
      }
      else -> {
        axis = View.TRANSLATION_X
        fromTranslation = 0
        toTranslation = 0
      }
    }
    val set = AnimatorSet()
    if (from != null) {
      set.play(ObjectAnimator.ofFloat(from, axis, 0f, fromTranslation.toFloat()))
    }
    set.play(ObjectAnimator.ofFloat(to, axis, toTranslation.toFloat(), 0f))
    return set
  }

  enum class NavigationType {
    TYPE_A, TYPE_B
  }
}