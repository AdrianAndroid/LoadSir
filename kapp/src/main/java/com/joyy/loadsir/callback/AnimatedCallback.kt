package com.joyy.loadsir.callback

import android.view.View
import android.widget.Toast
import com.joyy.loadsir.R
import android.view.animation.LinearInterpolator

import android.view.animation.Animation

import android.view.animation.RotateAnimation


/**
 * Time:2021/8/31 17:21
 * Author: flannery
 * Description:
 */
class AnimatedCallback : Callback() {

    var animateView: View? = null

    override fun onCreateView(): Int {
        return R.layout.layout_animate
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        animateView = view.findViewById(R.id.view_animate)
        val animation: Animation = RotateAnimation(
            0F, 359F, Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 1000
        animation.repeatCount = Int.MAX_VALUE
        animation.fillAfter = true
        animation.interpolator = LinearInterpolator()
        animateView?.startAnimation(animation)
        Toast.makeText(context.applicationContext, "start animation", Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        animateView?.clearAnimation()
        Toast.makeText(context.applicationContext, "stop animation", Toast.LENGTH_SHORT).show()
    }


}