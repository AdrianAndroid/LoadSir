package com.joyy.loadsir.core

import com.joyy.loadsir.callback.Callback
import com.joyy.loadsir.callback.SuccessCallback
import kotlin.reflect.KClass

/**
 * Time:2021/8/30 14:00
 * Author: flannery
 * Description:
 */
interface Convertor<T> {
    fun map(t: T): KClass<out Callback>
}