package com.bertan.taskatana

import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.view.View
import java.lang.ref.WeakReference

class FabAnim(private val fab: WeakReference<FloatingActionButton>,
              private val bottomView: WeakReference<ConstraintLayout>) {
  fun showBottomView() {
    fab.get()?.visibility = View.GONE
    bottomView.get()?.visibility = View.VISIBLE
  }

  fun hideBottomView() {
    fab.get()?.visibility = View.VISIBLE
    bottomView.get()?.visibility = View.GONE
  }
}