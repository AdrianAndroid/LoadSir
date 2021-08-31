package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.SuccessCallback
import com.joyy.loadsir.core.LoadService

class ConvertActivity : AppCompatActivity() {
    lateinit var loadService: LoadService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        loadService = LoadSir.register(
            this
        ) { _, _ -> }


        loadService.showCallbackWithConvert {
            SuccessCallback::class
        }
    }
}

data class HttpResult(val result: Int, val data: List<Any>)