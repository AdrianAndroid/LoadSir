package com.joyy.loadsir.core

import android.content.Context
import android.view.View

/**
 * Time:2021/8/30 13:21
 * Author: flannery
 * Description:
 */
interface Transport {
    /**
     * @param view 是要操作的这个View
     */
    fun order(context: Context, view: View)
}