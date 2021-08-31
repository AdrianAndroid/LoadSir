package com.joyy.loadsir

import android.view.View
import androidx.annotation.NonNull
import com.joyy.loadsir.callback.Callback
import com.joyy.loadsir.core.LoadService
import com.joyy.loadsir.target.ActivityTarget
import com.joyy.loadsir.target.ITarget
import com.joyy.loadsir.target.ViewTarget
import java.util.*
import kotlin.reflect.KClass

/**
 * Time:2021/8/30 10:25
 * Author: flannery
 * Description:
 * // 范型 : https://www.jianshu.com/p/671184ce18fc
 */
class LoadSir {
    companion object {


        // 创建一个独立的咯啊的
        fun createNewLoadSir(
            defaultCallback: KClass<out Callback>,
            vararg callbacks: KClass<out Callback>
        ): LoadSir {
            val loadSir = LoadSir()
            loadSir.setDefaultCallback(defaultCallback)
            for (callback in callbacks) {
                loadSir.addCallback(callback)
            }
            return loadSir
        }


        // 创建一个默认的loadsir
        fun init(
            defaultCallback: KClass<out Callback>,
            vararg callbacks: KClass<out Callback>
        ): LoadSir {
            commonSir.setDefaultCallback(defaultCallback)
            for (callback in callbacks) {
                commonSir.addCallback(callback)
            }
            return commonSir
        }

        fun register(@NonNull target: Any, onClick: (LoadService, View) -> Unit): LoadService {
            return commonSir.register(target, onClick)
        }

        val commonSir: LoadSir by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoadSir()
        }
    }

    private val callbacks = ArrayList<KClass<out Callback>>()
    private val targetContextList = ArrayList<ITarget>()
    private lateinit var defaultCallback: KClass<out Callback>

    init {
        targetContextList.add(ActivityTarget())
        targetContextList.add(ViewTarget())
    }

    fun addCallback(@NonNull callback: KClass<out Callback>): LoadSir {
        callbacks.add(callback)
        return this
    }

    fun addTargetContext(targetContext: ITarget): LoadSir {
        targetContextList.add(targetContext)
        return this
    }

    fun setDefaultCallback(@NonNull defaultCallback: KClass<out Callback>): LoadSir {
        this.defaultCallback = defaultCallback
        return this
    }

    fun register(@NonNull target: Any, onClick: (LoadService, View) -> Unit): LoadService {
        // 获取目标位置
        val targetContext: ITarget = getTargetContext(target, targetContextList) // ITarget
        // 需要的LoadLayout：（包括：1。 loading框 2。原先的的那个view
        val loadLayout = targetContext.replaceView(target)
        return LoadService(loadLayout, callbacks, defaultCallback, onClick)
    }

    /**
     * 从
     */
    private fun getTargetContext(target: Any, targetContextList: List<ITarget>): ITarget {
        for (targetContext in targetContextList) {
            if (targetContext.targetEquals(target)) {
                return targetContext
            }
        }
        throw IllegalArgumentException("No TargetContext fit it")
    }

    private fun getTargetList() = targetContextList
    private fun getCallbacks() = callbacks
    private fun getDefaultCallback() = callbacks


}