package com.joyy.loadsir.callback

import android.content.Context
import android.view.View

/**
 * Time:2021/8/30 10:47
 * Author: flannery
 * Description: _rootView 是要显示的那个View
 */
class SuccessCallback(private val _rootView: View) : Callback() {
    override fun onCreateView(context: Context): View {
        return _rootView
    }

    override fun getMyContext(): Context {
        return _rootView.context
    }

    override fun onClickEvent(view: View) {
        //super.onClickEvent(view)
        // 不用设置点击事件的
    }

    override fun onViewCreated(view: View) {
        //super.onViewCreated(view)
        // 不用进行处理的
    }

    fun show() {
        obtainRootView().visibility = View.VISIBLE
    }

    fun hide() {
        obtainRootView().visibility = View.INVISIBLE
    }


}
