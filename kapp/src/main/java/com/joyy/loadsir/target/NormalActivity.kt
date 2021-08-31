package com.joyy.loadsir.target

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.PostUtil
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.*
import com.joyy.loadsir.core.LoadService
import com.joyy.loadsir.core.Transport

class NormalActivity : AppCompatActivity() {

    private lateinit var loadService: LoadService

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)

        loadService = LoadSir.register(this) { load, _ -> // click事件
            load.showCallback(LoadingCallback::class)
            load.postDelayed({ load.showSuccess() }, 2000)
        }.setCallBack(EmptyCallback::class, object : Transport {
            override fun order(context: Context, view: View) {
                view.findViewById<TextView>(R.id.tv_empty).text = "赵健修改了content"
            }
        })
        loadService.showCallback(LoadingCallback::class)
        loadService.showCallback(EmptyCallback::class, 2000)
    }

    fun tvClicks(view: View) {
        loadService.showCallback(
            when (index % 5) {
                0 -> ErrorCallback::class
                1 -> EmptyCallback::class
                2 -> LoadingCallback::class
                3 -> TimeoutCallback::class
                4 -> CustomCallback::class
                else -> SuccessCallback::class
            }
        )
        index++

        loadService.postDelayed({ loadService.showSuccess() }, 2000)
    }
}