package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.HintCallback
import com.joyy.loadsir.callback.ProgressCallback

class DefaultCallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_callback)

        val loadingCallBack = ProgressCallback().apply {
            setTitle("Loading", R.style.Hint_Title)
        }

        val hintCallback = HintCallback().apply {
            setTitle("Error", R.style.Hint_Title)
            setSubTitle("Sorry, buddy, I will try it again.")
            imgResId = R.drawable.error
        }

        LoadSir.createNewLoadSir(
            ProgressCallback::class,
            loadingCallBack,
            hintCallback
        ).register(this) { loadService, view ->
            loadService.showCallback(ProgressCallback::class)
            loadService.postDelayed({ loadService.showSuccess() }, 2000)
        }.showCallback(HintCallback::class, 2000)


    }
}