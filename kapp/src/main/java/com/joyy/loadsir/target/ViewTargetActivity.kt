package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.callback.TimeoutCallback

class ViewTargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_target)

        val imageView: ImageView = findViewById(R.id.iv_img)
        val loadSir = LoadSir.createNewLoadSir(
            LoadingCallback::class,
            TimeoutCallback(),
            LoadingCallback()
        )

        val loadService = loadSir.register(imageView) { loadService, view ->
            loadService.showCallback(LoadingCallback::class)

            loadService.postDelayed({ loadService.showSuccess() }, 2000)
        }

        loadService.showCallback(TimeoutCallback::class, 2000)
    }
}