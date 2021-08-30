package com.joyy.loadsir.callback

import android.content.Context
import android.view.View
import java.io.*

/**
 * Time:2021/8/30 10:26
 * Author: flannery
 * Description:
 */
open class Callback : Serializable {

    lateinit var context: Context
    private var rootView: View? = null
    var onClickCallback: ((view: View) -> Unit)? = null // 点击事件的回调

    open fun onCreateView(): Int = 0
    open fun onAttach(context: Context, view: View) {}
    open fun onDetach() {}
    open fun onRootView(): View? = null
    open fun onReloadEvent(context: Context, view: View): Boolean = false
    open fun onViewCreated(context: Context, view: View) {}

    fun getDisplayView(): View { // 不能为空， 本来就是要用的
        rootView = onRootView() // 保留要使用的View
        if (rootView == null) {
            if (!::context.isInitialized) throw IllegalArgumentException("context不能为空的！")
            if (rootView != null) return rootView as View
            val resId: Int = onCreateView()
            if (rootView == null) rootView = View.inflate(context, resId, null)
            rootView?.let { v ->
                onClickEvent(v)
                onViewCreated(context, v)
            }
        }
        return rootView!!
    }

    // 默认的是整块的点击事件
    open fun onClickEvent(view: View?) {
        // 父布局的点击事件
        view?.setOnClickListener { v ->
            if (!onReloadEvent(view.context, v)) {
                onClickCallback?.invoke(v)
            }
        }
    }

    fun obtainRootView(): View = rootView ?: View.inflate(context, onCreateView(), null)

    fun setCallback(context: Context, onReload: (view: View) -> Unit) {
        this.context = context
        this.onClickCallback = onReload // 点击的回调
    }

    fun copy(): Callback {
        val bao = ByteArrayOutputStream()
        val oos = ObjectOutputStream(bao)
        oos.writeObject(this)
        oos.close()
        val bis = ByteArrayInputStream(bao.toByteArray())
        val ois = ObjectInputStream(bis)
        val obj = ois.readObject()
        ois.close()

        return obj as Callback
    }

}