package com.joyy.loadsir.target

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.PostUtil
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.CustomCallback
import com.joyy.loadsir.callback.EmptyCallback
import com.joyy.loadsir.callback.ErrorCallback
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.core.LoadService
import com.joyy.loadsir.core.Transport

class NormalActivity : AppCompatActivity() {

    private lateinit var loadService: LoadService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)

        loadService = LoadSir.register(this) { load, v ->
            load.showCallback(LoadingCallback::class)
            v.postDelayed({
                load.showSuccess()
            }, 2000)
        }
//            .setCallBack(EmptyCallback::class, object : Transport {
//            override fun order(context: Context, view: View) {
//            }
//        })

        loadService.showCallback(LoadingCallback::class)

        loadService.showCallback(EmptyCallback::class, 2000)
    }
}