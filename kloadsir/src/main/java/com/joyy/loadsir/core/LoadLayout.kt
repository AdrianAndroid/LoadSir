package com.joyy.loadsir.core

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import com.joyy.loadsir.callback.Callback
import com.joyy.loadsir.callback.SuccessCallback
import java.util.*
import kotlin.reflect.KClass

/**
 * Time:2021/8/30 10:42
 * Author: flannery
 * Description:
 */
@SuppressLint("ViewConstructor")
class LoadLayout(context: Context) : FrameLayout(context) {

    private val CALLBACK_CUSTOM_INDEX = 1

    private val callbacks = HashMap<KClass<out Callback>, Callback>()

    //    var preCallback: KClass<out Callback>? = null
    var curCallback: KClass<out Callback>? = null

    val throwNoExits: (o: Any) -> Unit = {
        throw IllegalArgumentException("The Callback ($it) is nonexistent.")
    }

    fun addCallback(callback: Callback) {
        if (!callbacks.containsKey(callback::class)) {
            callbacks[callback::class] = callback
        }
    }

    fun setupSuccessLayout(callback: Callback) {
        addCallback(callback)
        val successView = callback.getDisplayView() // loading要显示的这个View
        successView.visibility = View.INVISIBLE // 先隐藏
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(successView, layoutParams)
        curCallback = SuccessCallback::class
    }

    fun setupCallback(targetCallback: Callback, callback: ((View) -> Unit)) {
        val cloneCallback = targetCallback.copy()
        cloneCallback.setCallback(context, callback)
        addCallback(callback = cloneCallback)
    }

    fun showCallback(callback: KClass<out Callback>) {
        if (!callbacks.containsKey(callback)) throwNoExits(callback)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            post { showCallbackView(callback) }
        } else {
            post { showCallbackView(callback) }
        }
    }

    // 更换LoadingView
    fun showCallbackView(status: KClass<out Callback>) {
        // 先判断之前是否就是这个view，就不用动了
        if (curCallback != null && curCallback != status) {
            callbacks[curCallback]?.run {
                removeView(this.getDisplayView())
                onDetach()
            }
        }

        if (childCount > 1) {
            removeViewAt(CALLBACK_CUSTOM_INDEX)
        }
        val successCallback = callbacks[status]
        if (successCallback == null) {
            throwNoExits(status)
        } else {
            addView(successCallback.getDisplayView())
            successCallback.onAttach(context, successCallback.getDisplayView())
        }
        curCallback = status
    }

    fun setCallBack(callback: KClass<out Callback>, transport: Transport) {
        callbacks[callback]?.obtainRootView()?.run {
            transport.order(context, this)
        } ?: throwNoExits(callback)
    }
}