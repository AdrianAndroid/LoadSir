package com.joyy.loadsir;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;
import com.squareup.leakcanary.LeakCanary;

import com.joyy.loadsir.callback.CustomCallback;
import com.joyy.loadsir.callback.EmptyCallback;
import com.joyy.loadsir.callback.ErrorCallback;
import com.joyy.loadsir.callback.LoadingCallback;
import com.joyy.loadsir.callback.TimeoutCallback;

/**
 * Description:TODO
 * Create Time:2017/9/3 14:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (setupLeakCanary()) {
            return;
        }

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

    private boolean setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return true;
        }
        LeakCanary.install(this);
        return false;
    }
}
