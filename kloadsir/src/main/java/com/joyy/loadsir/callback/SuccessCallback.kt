package com.joyy.loadsir.callback

import android.view.View

/**
 * Time:2021/8/30 10:47
 * Author: flannery
 * Description:
 */
class SuccessCallback(private val rootView: View) : Callback() {
    override fun onRootView(): View {
        return rootView
    }

    fun show() {
        obtainRootView().visibility = View.VISIBLE
    }

    fun hide() {
        obtainRootView().visibility = View.INVISIBLE
    }


}
