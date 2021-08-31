package com.joyy.loadsir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.joyy.loadsir.target.AnimatedActivity;
import com.joyy.loadsir.target.ConstraintLayoutActivity;
import com.joyy.loadsir.target.DefaultCallbackActivity;
import com.joyy.loadsir.target.FragmentMultiActivity;
import com.joyy.loadsir.target.FragmentMultiViewPagerActivity;
import com.joyy.loadsir.target.FragmentSingleActivity;
import com.joyy.loadsir.target.KeepTitleActivity;
import com.joyy.loadsir.target.KeepTitleFragmentActivity;
import com.joyy.loadsir.target.Normal2Activity;
import com.joyy.loadsir.target.NormalActivity;
import com.joyy.loadsir.target.PlaceholderActivity;
import com.joyy.loadsir.target.ViewTargetActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inActivity(View view) {
        startActivity(new Intent(this, NormalActivity.class));
    }

    public void normalLayer2(View view) {
        startActivity(new Intent(this, Normal2Activity.class));
    }

    public void inConstraintLayoutActivity(View view) {
        startActivity(new Intent(this, ConstraintLayoutActivity.class));
    }

    public void showPlaceholder(View view) {
        startActivity(new Intent(this, PlaceholderActivity.class));
    }

    public void inFragment(View view) {
        startActivity(new Intent(this, FragmentSingleActivity.class));
    }

    public void inFragmentMutil(View view) {
        startActivity(new Intent(this, FragmentMultiActivity.class));
    }

    public void inFragmentViewSirPager(View view) {
        startActivity(new Intent(this, FragmentMultiViewPagerActivity.class));
    }

    public void viewTarget(View view) {
        startActivity(new Intent(this, ViewTargetActivity.class));
    }

    public void defaultCallback(View view) {
        startActivity(new Intent(this, DefaultCallbackActivity.class));
    }

    public void inActivityWithConvertor(View view) {
    }


    public void keepTitleInActivity(View view) {
        startActivity(new Intent(this, KeepTitleActivity.class));
    }

    public void animatCallback(View view) {
        startActivity(new Intent(this, AnimatedActivity.class));
    }

    public void keepTitleInFragment(View view) {
        startActivity(new Intent(this, KeepTitleFragmentActivity.class));
    }
}