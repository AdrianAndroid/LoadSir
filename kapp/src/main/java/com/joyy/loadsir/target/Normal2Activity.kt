package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.joyy.loadsir.R

class Normal2Activity : AppCompatActivity() {

    lateinit var container: FrameLayout
    lateinit var layer1: TextView
    lateinit var layer2: TextView
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal2)
        container = findViewById(R.id.container)
        layer1 = createTextView("One")
        layer2 = createTextView("\n\nTwo")
        image = findViewById(R.id.image)
    }

    private fun createTextView(text: String): TextView {
        val tv = TextView(this)
        tv.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        tv.text = text
        tv.textSize = 30F
        return tv
    }

    var switch: Boolean = false
    fun btnClick(view: View) {
        when (view.id) {
            R.id.id1 -> {
                if (container.indexOfChild(layer1) == -1) {
                    container.addView(layer1)
                }
                if (container.indexOfChild(layer2) == -1) {
                    container.addView(layer2)
                }
            }
            R.id.id2 -> {
//                val indexOfChild = container.indexOfChild(layer1)
//                container.removeView(if (switch) layer1 else layer2)
//                container.addView(if (switch) layer1 else layer2)

                val i_1 = container.indexOfChild(layer1)
                val i_2 = container.indexOfChild(layer2)
                if (switch) {
                    if (i_1 > -1) container.removeViewAt(i_1)
                    if (i_2 == -1) container.addView(layer2)
                } else {
                    if (i_2 > -1) container.removeViewAt(i_2)
                    if (i_1 == -1) container.addView(layer1)
                }
            }
            R.id.id3 -> {

//                conta？？r.removeView(if (switch) layer1 else layer2)
//                container.addView(if (!switch) layer1 else layer2, 0)
            }
            R.id.id4 -> {
                val index1 = container.indexOfChild(layer1)
                val index2 = container.indexOfChild(layer2)
                Toast.makeText(
                    view.context,
                    "index1 = $index1 , index1 = $index2",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.id5 -> {
                if (switch) {
                    container.removeViewFromView(layer1)
                    container.addViewFromView(image)
                } else {
                    container.removeViewFromView(image)
                    container.addViewFromView(layer1)
                }
                val index1 = container.indexOfChild(layer1)
                val indexImage = container.indexOfChild(image)
                Toast.makeText(
                    view.context,
                    "index1 = $index1 , index1 = $indexImage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        switch = !switch
    }

    fun ViewGroup.validateIndex(index: Int) = index > -1

    private fun ViewGroup.removeViewFromView(view: View) {
        val index = indexOfChild(view)
        if (validateIndex(index)) {
            removeViewAt(index)
        }
    }

    private fun ViewGroup.addViewFromView(view: View) {
        val index = indexOfChild(view)
        if (!validateIndex(index)) {
            addView(view)
        }
//        view.bringToFront()
    }
}