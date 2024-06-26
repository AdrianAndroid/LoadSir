package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.AnimatedCallback
import com.joyy.loadsir.callback.Callback
import com.joyy.loadsir.callback.EmptyCallback
import com.joyy.loadsir.callback.SuccessCallback
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

class AnimatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated)

        LoadSir.createNewLoadSir(
            { k: KClass<out Callback> ->
                when (k) {
                    AnimatedCallback::class -> AnimatedCallback()
                    EmptyCallback::class -> EmptyCallback()
                    else -> throw IllegalArgumentException("")
                }
            },
            AnimatedCallback::class,
            EmptyCallback::class
        ).register(this) { loadService, view ->
            loadService.showCallback(AnimatedCallback::class)
            loadService.showCallback(SuccessCallback::class, 2000)
        }.showCallback(EmptyCallback::class, 2000)

    }
}