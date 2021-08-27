package com.joyy.loadsir.target;

import android.os.Bundle;
import android.view.View;

import com.joyy.loadsir.callback.LottieEmptyCallback;
import com.joyy.loadsir.callback.LottieLoadingCallback;
import com.joyy.loadsir.callback.Callback;
import com.joyy.loadsir.core.LoadService;
import com.joyy.loadsir.core.LoadSir;

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

public class LottieActivity extends AppCompatActivity {

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new LottieLoadingCallback())
                .addCallback(new LottieEmptyCallback())
                .setDefaultCallback(LottieLoadingCallback.class)
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LottieLoadingCallback.class);
                PostUtil.postSuccessDelayed(loadService, 1500);
            }
        });
        PostUtil.postCallbackDelayed(loadService, LottieEmptyCallback.class);
    }

}
