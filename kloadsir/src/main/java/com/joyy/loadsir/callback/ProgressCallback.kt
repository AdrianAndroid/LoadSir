package com.joyy.loadsir.callback

import android.R
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes


/**
 * Time:2021/8/31 16:29
 * Author: flannery
 * Description:
 */
class ProgressCallback : Callback() {

    private var title: String? = null

    @StyleRes
    private var titleStyleRes = 0

    var subTitle: String? = null

    @StyleRes
    var subTitleStyleRes = 0

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

        val progressBar = ProgressBar(context)
        ll.addView(progressBar, lp)

        if (!TextUtils.isEmpty(title)) {
            val tvTitle = TextView(context)
            tvTitle.text = title
            if (titleStyleRes == -1) {
                tvTitle.setTextAppearance(context, R.style.TextAppearance_Large)
            } else {
                tvTitle.setTextAppearance(context, titleStyleRes)
            }
            ll.addView(tvTitle, lp)
        }
        if (!TextUtils.isEmpty(subTitle)) {
            val tvSubtitle = TextView(context)
            tvSubtitle.text = subTitle
            if (subTitleStyleRes == -1) {
                tvSubtitle.setTextAppearance(context, R.style.TextAppearance_Medium)
            } else {
                tvSubtitle.setTextAppearance(context, subTitleStyleRes)
            }
            ll.addView(tvSubtitle, lp)
        }
    }


}