package com.joyy.loadsir.callback

import android.view.View
import com.joyy.loadsir.R

/**
 * Time:2021/8/31 14:06
 * Author: flannery
 * Description:
 */
class PlaceHolderCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_placeholder
    }

    override fun onReloadEvent(view: View): Boolean {
        return true
    }

}