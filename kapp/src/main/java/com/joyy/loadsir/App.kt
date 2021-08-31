package com.joyy.loadsir

import android.app.Application
import com.joyy.loadsir.callback.*

/**
 * Time:2021/8/30 10:22
 * Author: flannery
 * Description:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        LoadSir.init(
            LoadingCallback::class,
            ErrorCallback(), // 后期可以改成使用反射创建
            EmptyCallback(),
            LoadingCallback(),
            TimeoutCallback(),
            CustomCallback()
        )
    }

}