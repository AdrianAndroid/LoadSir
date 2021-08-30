package com.joyy.loadsir.callback

import android.content.Context
import android.view.View
import android.widget.Toast
import com.joyy.loadsir.R


/**
 * Time:2021/8/30 14:52
 * Author: flannery
 * Description:
 */
class CustomCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_custom
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        Toast.makeText(context.applicationContext, "Hello buddy, how r u! :p", Toast.LENGTH_SHORT)
            .show()
        view.findViewById<View>(R.id.iv_gift).setOnClickListener {
            Toast.makeText(
                context.applicationContext,
                "It's your gift! :p",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }
}