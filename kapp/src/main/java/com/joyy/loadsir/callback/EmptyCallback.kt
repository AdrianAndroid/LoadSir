package com.joyy.loadsir.callback

import com.joyy.loadsir.R

/**
 * Time:2021/8/30 14:51
 * Author: flannery
 * Description:
 */
class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}