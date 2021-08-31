package com.joyy.loadsir.target

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.EmptyCallback
import com.joyy.loadsir.callback.ErrorCallback
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.core.LoadService


class FragmentMultiActivity : AppCompatActivity() {

    val fragmentA = FragmentA()
    val fragmentB = FragmentB()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_multi)


        supportFragmentManager.beginTransaction().add(R.id.fl_content, fragmentA).commit()
        supportFragmentManager.beginTransaction().add(R.id.fl_content, fragmentB).commit()
        supportFragmentManager.beginTransaction().show(fragmentA).hide(fragmentB).commit()
    }


    fun showFragmentA(view: View) {
        supportFragmentManager.beginTransaction().show(fragmentA).hide(fragmentB).commit()
    }

    fun showFragmentB(view: View) {
        supportFragmentManager.beginTransaction().show(fragmentB).hide(fragmentA).commit()
    }
}

abstract class BaseFragment : Fragment() {

    lateinit var mBaseLoadService: LoadService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBaseLoadService = LoadSir.register(
            inflater.inflate(onCreateViewFromId(), container, false)
        ) { loadService, view ->
            onNetReload(view)
        }
        return mBaseLoadService.getLoadLayout()
    }

    abstract fun onCreateViewFromId(): Int
    abstract fun loadNet()
    abstract fun onNetReload(v: View)
}

class FragmentA : BaseFragment() {

    var mTvResultA: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mTvResultA = view.findViewById(R.id.tv_result_a)

        mBaseLoadService.postDelayed({ loadNet() }, 2000)
    }

    override fun onCreateViewFromId(): Int = R.layout.fragment_a

    override fun loadNet() {
        mBaseLoadService.showCallback(ErrorCallback::class)
    }

    override fun onNetReload(v: View) {
        mTvResultA?.text = "Oh, Yes."
        Toast.makeText(context, "reload in Fragment A", Toast.LENGTH_SHORT).show()
        mBaseLoadService.showCallback(LoadingCallback::class)

        mBaseLoadService.postDelayed({ mBaseLoadService.showSuccess() }, 2000)
    }
}

class FragmentB : BaseFragment() {

    var mTvResultB: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mTvResultB = view.findViewById(R.id.tv_result_b)


        mBaseLoadService.postDelayed({ loadNet() }, 2000)
    }

    override fun onCreateViewFromId(): Int = R.layout.fragment_b

    override fun loadNet() {
        mBaseLoadService.showCallback(EmptyCallback::class)
    }

    override fun onNetReload(v: View) {
        mTvResultB?.text = "Oh, Yes too."
        Toast.makeText(context, "reload in Fragment B", Toast.LENGTH_SHORT).show()
        mBaseLoadService.showCallback(LoadingCallback::class)

        mBaseLoadService.postDelayed({ mBaseLoadService.showSuccess() }, 2000)
    }
}