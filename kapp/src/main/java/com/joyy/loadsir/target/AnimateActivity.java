package com.joyy.loadsir.target;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.joyy.loadsir.callback.AnimateCallback;
import com.joyy.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.joyy.loadsir.PostUtil;
import com.joyy.loadsir.R;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class AnimateActivity extends AppCompatActivity {


    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        // Your can change the callback on sub thread directly.
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new EmptyCallback())
                .addCallback(new AnimateCallback())
                .setDefaultCallback(AnimateCallback.class)
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // Your can change the status out of Main thread.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadService.showCallback(AnimateCallback.class);
                        //do retry logic...
                        SystemClock.sleep(500);
                        //callback on sub thread
                        loadService.showSuccess();
                    }
                }).start();
            }
        });
        PostUtil.postCallbackDelayed(loadService, EmptyCallback.class, 1000);
    }
}
