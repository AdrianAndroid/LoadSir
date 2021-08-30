package com.joyy.loadsir.target

import android.app.Activity
import android.view.ViewGroup
import com.joyy.loadsir.callback.SuccessCallback
import com.joyy.loadsir.core.LoadLayout

/**
 * Time:2021/8/30 10:38
 * Author: flannery
 * Description:
 */
class ActivityTarget : ITarget {
    override fun targetEquals(target: Any?): Boolean = target is Activity

    override fun replaceView(target: Any): LoadLayout {
        if (target is Activity) {
            val contentParent = target.findViewById<ViewGroup>(android.R.id.content) // content布局
            val childIndex = 0
            val oldContent = contentParent.getChildAt(childIndex)
            contentParent.removeView(oldContent)  // 先把真实布局移除

            val lp = oldContent.layoutParams
            // Loading布局
            val loadLayout = LoadLayout(target)
            // 目的：为了回调
            loadLayout.setupSuccessLayout(SuccessCallback(oldContent)/*保留原来的View*/)
            contentParent.addView(loadLayout, childIndex, lp)
            return loadLayout
        } else {
            throw IllegalArgumentException("target 不是Activity")
        }
    }
}