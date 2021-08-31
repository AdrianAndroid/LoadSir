package com.joyy.loadsir.callback

import android.R
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes


/**
 * Time:2021/8/31 16:05
 * Author: flannery
 * Description:
 */
class HintCallback : Callback() {

    private var title: String? = null

    @StyleRes
    private var titleStyleRes = 0

    private var subTitle: String? = null

    @StyleRes
    private var subTitleStyleRes = 0

    @DrawableRes
    var imgResId = 0


    fun setTitle(title: String, @StyleRes titleStyleRes: Int = R.style.TextAppearance_Large) {
        this.title = title
        this.titleStyleRes = titleStyleRes
    }

    fun setSubTitle(
        subTitle: String,
        @StyleRes subTitleStyleRes: Int = R.style.TextAppearance_Medium
    ) {
        this.subTitle = subTitle
        this.subTitleStyleRes = subTitleStyleRes
    }


    override fun onCreateView(context: Context): View? {
        return LinearLayout(context)
    }

    override fun onViewCreated(view: View) {
        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.gravity = Gravity.CENTER
        val ll = view as LinearLayout
        ll.orientation = LinearLayout.VERTICAL
        ll.gravity = Gravity.CENTER
        if (imgResId != -1) {
            val ivImage = ImageView(context)
            ivImage.setBackgroundResource(imgResId)
            ll.addView(ivImage, lp)
        }
        if (!TextUtils.isEmpty(title)) {
            val tvTitle = TextView(context)
            tvTitle.text = title
            if (titleStyleRes == -1) {
                setTextAppearance(tvTitle, context, R.style.TextAppearance_Large)
            } else {
                setTextAppearance(tvTitle, context, titleStyleRes)
            }
            ll.addView(tvTitle, lp)
        }
        if (!TextUtils.isEmpty(subTitle)) {
            val tvSubtitle = TextView(context)
            tvSubtitle.text = subTitle
            if (subTitleStyleRes == -1) {
                setTextAppearance(tvSubtitle, context, R.style.TextAppearance_Small)
            } else {
                setTextAppearance(tvSubtitle, context, subTitleStyleRes)
            }
            ll.addView(tvSubtitle, lp)
        }
    }

    private fun setTextAppearance(tv: TextView, context: Context, @StyleRes resId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAppearance(resId)
        } else {
            tv.setTextAppearance(context, resId)
        }
    }

}

