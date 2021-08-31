package com.joyy.loadsir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joyy.loadsir.target.Normal2Activity;
import com.joyy.loadsir.target.NormalActivity;

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
}