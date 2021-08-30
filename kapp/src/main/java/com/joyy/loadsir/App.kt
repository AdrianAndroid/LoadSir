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
            ErrorCallback(),
            EmptyCallback(),
            LoadingCallback(),
            TimeoutCallback(),
            CustomCallback()
        )
    }

}