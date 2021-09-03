package com.joyy.loadsir.callback

import android.view.View
import com.joyy.loadsir.R

/**
 * Time:2021/8/30 14:52
 * Author: flannery
 * Description:
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(view: View): Boolean {
        return true
    }
}