package com.joyy.loadsir.core

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
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
    companion object {
        fun log(msg: Any) {
            Log.i("[LoadLayout]", "$msg")
        }
    }

    private val callbacks = HashMap<KClass<out Callback>, Callback>()

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
//        successView.visibility = View.INVISIBLE // 先隐藏
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(successView, layoutParams)
        curCallback = SuccessCallback::class
    }

    // 注册新的CallBack
    fun setupCallback(targetCallback: Callback, callback: ((View) -> Unit)) {
//        val cloneCallback = targetCallback.copy()
        targetCallback.setCallback(context, callback)
        addCallback(callback = targetCallback)
    }

    fun showCallback(callback: KClass<out Callback>) {
        if (!callbacks.containsKey(callback)) throwNoExits(callback)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showCallbackView(callback)
        } else {
            post { showCallbackView(callback) }
        }
    }

    // 更换LoadingView
    fun showCallbackView(status: KClass<out Callback>) {
        //log("preStatus = $status index=${indexOfChild(callbacks[curCallback]?.getDisplayView())} childCount=${childCount}")

        // 先判断之前是否就是这个view，就不用动了
        callbacks[curCallback]?.let {
            removeViewFromView(it.getDisplayView())
            it.onDetach()
        }

        callbacks[status]?.let {
            val view = it.getDisplayView()
            addViewFromView(view)
            it.onAttach(view)
        } ?: throwNoExits(status)

        curCallback = status
    }

    // 得到这个class的View， 然后进行一些操作
    fun setCallBack(callback: KClass<out Callback>, transport: (View) -> Unit) {
        callbacks[callback]?.obtainRootView()?.run(transport)
    }

    private fun removeViewFromView(view: View) {
        val indexOfChild = indexOfChild(view)
        if (indexOfChild > -1) {
            removeViewAt(indexOfChild)
        }
    }

    private fun addViewFromView(view: View) {
        val indexOfChild = indexOfChild(view)
        if (indexOfChild == -1) {
            addView(view)
        }
    }
}