package com.joyy.loadsir.target;

import android.os.Bundle;
import android.widget.ImageView;

import com.joyy.loadsir.callback.LoadingCallback;
import com.joyy.loadsir.callback.TimeoutCallback;
import com.joyy.loadsir.callback.Callback;
import com.joyy.loadsir.core.LoadService;
import com.joyy.loadsir.core.LoadSir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.joyy.loadsir.PostUtil;
import com.joyy.loadsir.R;


/**
 * Description:TODO
 * Create Time:2017/9/3 11:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ViewTargetActivity extends AppCompatActivity {

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ImageView imageView = findViewById(R.id.iv_img);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new TimeoutCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(imageView, (Callback.OnReloadListener) v -> {
            loadService.showCallback(LoadingCallback.class);
            //do retry logic...

            //callback
            PostUtil.postSuccessDelayed(loadService);
        });
        PostUtil.postCallbackDelayed(loadService, TimeoutCallback.class);
    }

}
