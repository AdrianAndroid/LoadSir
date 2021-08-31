package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joyy.loadsir.LoadSir
import com.joyy.loadsir.R
import com.joyy.loadsir.callback.CustomCallback
import com.joyy.loadsir.callback.EmptyCallback
import com.joyy.loadsir.callback.ErrorCallback
import com.joyy.loadsir.callback.LoadingCallback
import com.joyy.loadsir.core.LoadService

class FragmentSingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_single)


        supportFragmentManager.beginTransaction().add(R.id.fl_content, NormalFragment()).commit()

    }
}

class NormalFragment : Fragment() {

    lateinit var loadService: LoadService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loadSir = LoadSir.createNewLoadSir(
            LoadingCallback::class,
            CustomCallback(),
            LoadingCallback(),
            ErrorCallback()
        )

        val rootView = inflater.inflate(R.layout.fragment_normal, container, false)
        loadService = loadSir.register(rootView) { loadService, view ->
            loadService.showCallback(LoadingCallback::class)

            loadService.postDelayed({ loadService.showSuccess() }, 2000)

        }
        return loadService.getLoadLayout()
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadService.showCallback(LoadingCallback::class)

        loadService.postDelayed({ loadService.showCallback(ErrorCallback::class) }, 2000)
    }

}