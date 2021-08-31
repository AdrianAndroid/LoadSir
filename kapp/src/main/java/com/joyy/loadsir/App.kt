package com.joyy.loadsir

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.joyy.loadsir.callback.*

/**
 * Time:2021/8/30 10:22
 * Author: flannery
 * Description:
 */
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // 初始化MultiDex
        MultiDex.install(this);
    }

    override fun onCreate() {
        super.onCreate()

        LoadSir.init(
            ErrorCallback::class, // 后期可以改成使用反射创建
            EmptyCallback::class,
            LoadingCallback::class,
            TimeoutCallback::class,
            CustomCallback::class
        ).setDefaultCallback(LoadingCallback::class)
    }

}