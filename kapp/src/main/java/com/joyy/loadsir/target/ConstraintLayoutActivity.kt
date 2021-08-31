package com.joyy.loadsir.target

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.EmptyCallback
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.core.LoadService

class ConstraintLayoutActivity : AppCompatActivity() {

    private lateinit var loadService: LoadService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraintlayout)

        val tv_center = findViewById<TextView>(R.id.tv_center)

        loadService = LoadSir.register(tv_center) { load, _ -> // click事件
            load.showCallback(LoadingCallback::class)
            load.postDelayed({ load.showSuccess() }, 2000)
        }.transport(EmptyCallback::class) { view ->
            view.findViewById<TextView>(R.id.tv_empty).text = "赵健修改了content"
        }
        loadService.showCallback(LoadingCallback::class)
        loadService.showCallback(EmptyCallback::class, 2000)
    }
}