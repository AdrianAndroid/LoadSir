package com.joyy.loadsir.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.joyy.loadsir.R

class FragmentMultiViewPagerActivity : AppCompatActivity() {

    val fragments = arrayListOf<Fragment>()
    val tabTitles = arrayOf("Fragment A", "Fragment B")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_multi_view_pager)

        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)

        fragments.add(FragmentA())
        fragments.add(FragmentB())

        viewpager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return tabTitles[position]
            }
        }

        tablayout.setupWithViewPager(viewpager)
        tablayout.tabMode = TabLayout.MODE_FIXED
    }
}