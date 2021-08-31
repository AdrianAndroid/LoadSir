package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joyy.loadsir.LoadSir

import android.widget.FrameLayout
import android.widget.LinearLayout

import android.widget.TextView
import androidx.annotation.Nullable
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.ErrorCallback
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.callback.SuccessCallback
import com.joyy.loadsir.core.LoadService


class KeepTitleActivity : BaseTitleActivity() {

    var mTvTitle: TextView? = null

    override fun getContentTitle(): String? {
        return "Title"
    }

    override fun getContentView(): Int {
        return R.layout.activity_content
    }

    override fun initView() {
        val mTvMsg = findViewById<TextView>(com.joyy.loadsir.R.id.tv_subTitle)
        mTvMsg?.text = "Keep Title In Activity"
        mTvTitle?.text = "Yes, Success"
    }

    override fun initNet() {
        mBaseLoadService.showCallback(ErrorCallback::class, 2000)
    }

    override fun onNetReload(v: View) {
        mBaseLoadService.showCallback(LoadingCallback::class)
        mBaseLoadService.showCallback(SuccessCallback::class, 2000)
    }
}

abstract class BaseTitleActivity : AppCompatActivity() {

    lateinit var rootView: View
    lateinit var mBaseLoadService: LoadService

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootView = View.inflate(this, R.layout.activity_keep_title, null)
        addContent()
        setContentView(rootView)
        initView()
        initNet()
    }


    open fun addContent() {
        val flContent: FrameLayout = rootView.findViewById(R.id.fl_content)
        val tvTitleTitle: TextView = rootView.findViewById(R.id.tv_title_title)
        val llTitleBack: LinearLayout = rootView.findViewById(R.id.ll_title_back)
        tvTitleTitle.text = if (getContentTitle() == null) "" else getContentTitle()
        llTitleBack.setOnClickListener { v: View? -> backClick() }
        val content = View.inflate(this, getContentView(), null)
        if (content != null) {
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            flContent.addView(content, params)
            mBaseLoadService = LoadSir.register(content) { loadService, view ->
                onNetReload(view)
            }
//                LoadSir.getDefault().register(content, ::onNetReload as Callback.OnReloadListener)
        }
    }

    private fun backClick() {
        finish()
    }

    protected abstract fun getContentTitle(): String?

    protected abstract fun getContentView(): Int

    protected abstract fun initView()

    protected abstract fun initNet()

    protected abstract fun onNetReload(v: View)
}