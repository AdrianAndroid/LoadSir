package com.joyy.loadsir.target

import android.view.View
import android.view.ViewGroup
import com.joyy.loadsir.callback.SuccessCallback
import com.joyy.loadsir.core.LoadLayout

/**
 * Time:2021/8/30 13:29
 * Author: flannery
 * Description:
 */
class ViewTarget : ITarget {
    override fun targetEquals(target: Any?): Boolean = target is View

    override fun replaceView(target: Any): LoadLayout {
        if (target is View) {
            val contentParent = target.parent as? ViewGroup
                ?: throw java.lang.IllegalArgumentException("LoadSir 需要有父容器！")
            var childIndex = 0
            for (index in 0 until contentParent.childCount) {
                if (target == contentParent.getChildAt(index)) {
                    childIndex = index
                    break
                }
            }
            // 移除掉需要加载loading的view
            contentParent.removeView(target)

            val lp = target.layoutParams
            val loadLayout = LoadLayout(target.context)
            loadLayout.setupSuccessLayout(SuccessCallback(target))
            contentParent.addView(loadLayout, childIndex, lp)
            return loadLayout
        } else {
            throw IllegalArgumentException("target 不是 view!")
        }
    }
}