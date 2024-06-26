package com.joyy.loadsir.target

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.PlaceHolderCallback
import java.lang.IllegalArgumentException

class PlaceholderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholder)


        val loadSir =
            LoadSir.createNewLoadSir(
                {
                    when (it) {
                        PlaceHolderCallback::class -> PlaceHolderCallback()
                        else -> throw  IllegalArgumentException()
                    }
                },
                PlaceHolderCallback::class
            )
        val loadService = loadSir.register(this) { loadService, view ->
            // do retry logic ...
        }.transport(PlaceHolderCallback::class) {

        }

        loadService.showCallback(PlaceHolderCallback::class)
        loadService.postDelayed({ loadService.showSuccess() }, 2000)
    }
}