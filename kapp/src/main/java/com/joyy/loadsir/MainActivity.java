package com.joyy.loadsir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.joyy.loadsir.target.NormalActivity;
import com.joyy.loadsir.target.ViewTargetActivity;
import com.joyy.loadsir.target.AnimateActivity;
import com.joyy.loadsir.target.BestPracticesActivity;
import com.joyy.loadsir.target.ConstraintLayoutActivity;
import com.joyy.loadsir.target.DefaultCallbackActivity;
import com.joyy.loadsir.target.KeepTitleActivity;
import com.joyy.loadsir.target.KeepTitleFragmentActivity;
import com.joyy.loadsir.target.MultiFragmentActivity;
import com.joyy.loadsir.target.FragmentSingleActivity;
import com.joyy.loadsir.target.ConvertorActivity;
import com.joyy.loadsir.target.MultiFragmentWithViewPagerActivity;
import com.joyy.loadsir.target.PlaceholderActivity;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inActivity(View view) {
        startActivity(new Intent(this, NormalActivity.class));
    }

    public void showPlaceholder(View view) {
        startActivity(new Intent(this, PlaceholderActivity.class));
    }

    public void inActivityWithConvertor(View view) {
        startActivity(new Intent(this, ConvertorActivity.class));
    }

    public void inFragment(View view) {
        startActivity(new Intent(this, FragmentSingleActivity.class));
    }

    public void inFragmentMutil(View view) {
        startActivity(new Intent(this, MultiFragmentActivity.class));
    }

    public void inFragmentViewSirPager(View view) {
        startActivity(new Intent(this, MultiFragmentWithViewPagerActivity.class));
    }

    public void inView(View view) {
        startActivity(new Intent(this, ViewTargetActivity.class));
    }

    public void defaultCallback(View view) {
        startActivity(new Intent(this, DefaultCallbackActivity.class));
    }

    public void animatCallback(View view) {
        startActivity(new Intent(this, AnimateActivity.class));
    }

    public void keepTitleInFragment(View view) {
        startActivity(new Intent(this, KeepTitleFragmentActivity.class));
    }

    public void bestPractices(View view) {
        startActivity(new Intent(this, BestPracticesActivity.class));
    }

    public void keepTitleInActivity(View view) {
        startActivity(new Intent(this, KeepTitleActivity.class));
    }

    public void inConstraintLayoutActivity(View view) {
        startActivity(new Intent(this, ConstraintLayoutActivity.class));
    }
}
