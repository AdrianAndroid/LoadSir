package com.joyy.loadsir

import android.app.Application
import com.joyy.loadsir.callback.*
import java.lang.IllegalArgumentException

/**
 * Time:2021/8/30 10:22
 * Author: flannery
 * Description:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        LoadSir.loadSirFactory(
            { k ->
                when (k) {
                    // 为啥不使用反射， kotlin反射包太大了
                    ErrorCallback::class -> ErrorCallback()
                    EmptyCallback::class -> EmptyCallback()
                    LoadingCallback::class -> LoadingCallback()
                    TimeoutCallback::class -> TimeoutCallback()
                    CustomCallback::class -> CustomCallback()
                    else -> throw IllegalArgumentException("没有找到对应的Callback!!")
                }
            },
            LoadingCallback::class, // 第一个就是默认的
            ErrorCallback::class,
            EmptyCallback::class,
            TimeoutCallback::class,
            CustomCallback::class
        )
    }

}