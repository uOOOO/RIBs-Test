package com.uoooo.ribs.test.transition;

import android.view.View;
import android.view.ViewTreeObserver;

public final class Views {

  private Views() {
    throw new AssertionError();
  }

  public static void whenMeasured(final View view, final OnMeasured onMeasured) {
    int width = view.getWidth();
    int height = view.getHeight();
    if (width > 0 && height > 0) {
      onMeasured.onMeasured();
      return;
    }

    view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (observer.isAlive()) {
          observer.removeOnPreDrawListener(this);
        }
        onMeasured.onMeasured();
        return true;
      }
    });
  }

  public interface OnMeasured {
    void onMeasured();
  }
}

