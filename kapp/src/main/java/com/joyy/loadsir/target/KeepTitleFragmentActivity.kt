package com.joyy.loadsir.target

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.EmptyCallback
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.callback.SuccessCallback
import com.joyy.loadsir.core.LoadService


class KeepTitleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_keep_title_fragment)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().add(R.id.fl_content, KeepTitleFragment()).commit()
    }
}

class KeepTitleFragment : Fragment() {
    lateinit var rootView: View
    lateinit var loadService: LoadService
    lateinit var mIvBack: ImageView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentView = rootView.findViewById<LinearLayout>(R.id.ll_content)
        LoadSir.createNewLoadSir(
            LoadingCallback::class,
            EmptyCallback(),
            LoadingCallback()
        ).register(contentView) { loadService, view ->
            loadService.showCallback(LoadingCallback::class)
            loadService.showCallback(SuccessCallback::class, 2000)
        }.showCallback(EmptyCallback::class, 2000)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.title_title_bar, container, false)
        return rootView

        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}