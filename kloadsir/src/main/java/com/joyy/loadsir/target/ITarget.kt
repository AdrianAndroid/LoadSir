package com.joyy.loadsir.target

import com.joyy.loadsir.core.LoadLayout

/**
 * Time:2021/8/30 10:28
 * Author: flannery
 * Description:
 */
interface ITarget {

    fun targetEquals(target: Any?): Boolean

    /**
     * 1.removeView 2.确定LP 3.addView
     */
    fun replaceView(target: Any): LoadLayout
}