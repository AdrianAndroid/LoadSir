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
    private lateinit var rootView: View
    var onClickCallback: ((view: View) -> Unit)? = null // 点击事件的回调

    open fun onCreateView(): Int = 0
    open fun onAttach(view: View) {}
    open fun onDetach() {}
    open fun onCreateView(context: Context): View? = null
    open fun onReloadEvent(view: View): Boolean = false
    open fun onViewCreated(view: View) {}

    fun getDisplayView(): View { // 不能为空， 本来就是要用的
        return obtainRootView()
    }

    // 默认的是整块的点击事件
    // 如果想缩小点击的范围，请自行继承这个方法
    open fun onClickEvent(view: View) {
        // 父布局的点击事件
        view.setOnClickListener { v ->
            if (!onReloadEvent(v)) {
                onClickCallback?.invoke(v)
            }
        }
    }

    open fun getMyContext(): Context = context

    fun obtainRootView(): View {
        if (::rootView.isInitialized) return rootView.apply {
            onClickEvent(this)
            // onViewCreated(this) // 第一次创建的时候，才调用
        }
        onCreateView(getMyContext())?.let { rootView = it }
        if (::rootView.isInitialized) return rootView.apply {
            onClickEvent(this)
            onViewCreated(this)
        }
        View.inflate(context, onCreateView(), null)?.let { rootView = it }
        if (::rootView.isInitialized) return rootView.apply {
            onClickEvent(this)
            onViewCreated(this)
        }

        throw IllegalArgumentException("必须又一个RootView(TARGET)")
    }

    fun setCallback(context: Context, onReload: (view: View) -> Unit) {
        this.context = context
        this.onClickCallback = onReload // 点击的回调
    }

//    fun copy(): Callback {
//        val bao = ByteArrayOutputStream()
//        val oos = ObjectOutputStream(bao)
//        oos.writeObject(this)
//        oos.close()
//        val bis = ByteArrayInputStream(bao.toByteArray())
//        val ois = ObjectInputStream(bis)
//        val obj = ois.readObject()
//        ois.close()
//
//        return obj as Callback
//    }

}