package com.joyy.loadsir

import android.os.Handler
import android.os.Looper
import com.joyy.loadsir.callback.Callback
import com.joyy.loadsir.core.LoadService
import kotlin.reflect.KClass

/**
 * Time:2021/8/30 15:47
 * Author: flannery
 * Description:
 */
object PostUtil {

    private val DELAY_TIME: Long = 1000

    fun postCallbackDelayed(loadService: LoadService, clazz: KClass<out Callback>) {
        postCallbackDelayed(loadService, clazz, DELAY_TIME)
    }

    fun postCallbackDelayed(loadService: LoadService, clazz: KClass<out Callback>, delay: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            loadService.showCallback(clazz)
        }, delay)
    }

    fun postSuccessDelayed(loadService: LoadService) {
        postSuccessDelayed(loadService, DELAY_TIME)
    }

    fun postSuccessDelayed(loadService: LoadService, delay: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            loadService.showSuccess()
        }, delay)
    }

}