package com.joyy.loadsir.core

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.joyy.loadsir.callback.Callback
import com.joyy.loadsir.callback.SuccessCallback
import kotlin.reflect.KClass

/**
 * Time:2021/8/30 13:59
 * Author: flannery
 * Description:
 */
class LoadService(
    private var loadLayout: LoadLayout,
    callbacks: List<KClass<out Callback>>,
    defaultCallback: KClass<out Callback>,
    private val onClick: ((LoadService, View) -> Unit)
) {

    private val _onClick: (View) -> Unit = { v ->
        onClick(this, v)
    }

    init {
        // 都先换成默认的Callback
        callbacks.forEach { cb: KClass<out Callback> ->
            loadLayout.setupCallback(cb, _onClick)
        }
        loadLayout.showCallback(defaultCallback)
    }

    fun showSuccess() {
        loadLayout.showCallback(SuccessCallback::class)
    }

    fun showCallback(callback: KClass<out Callback>, delay: Long = 0L) {
        if (delay == 0L) {
            loadLayout.showCallback(callback)
        } else {
            loadLayout.postDelayed({
                loadLayout.showCallback(callback)
            }, delay)
        }
    }

    fun showCallbackWithConvert(convert: () -> KClass<out Callback>) {
        showCallback(convert())
    }


    fun postDelayed(run: Runnable, delay: Long) = loadLayout.postDelayed(run, delay)

    fun getCurrentCallback() = loadLayout.curCallback

    /**
     * obtain rootView if you want keep the toolbar in Fragment
     */
    fun getTitleLoadLayout(context: Context, rootView: ViewGroup, titleView: View): LinearLayout {
        val newRootView = LinearLayout(context)
        newRootView.orientation = LinearLayout.VERTICAL
        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        newRootView.layoutParams = lp
        rootView.removeView(titleView)
        newRootView.addView(titleView)
        newRootView.addView(loadLayout, lp)
        return newRootView
    }

    fun transport(callback: KClass<out Callback>, transport: (View) -> Unit): LoadService {
        loadLayout.setCallBack(callback, transport)
        return this
    }

//    fun <T> convert(convert: (t: T) -> Callback): LoadService {
//        loadLayout.setCallBack(convert.invoke(t))
//        return this
//    }

    fun getLoadLayout(): View = loadLayout

}