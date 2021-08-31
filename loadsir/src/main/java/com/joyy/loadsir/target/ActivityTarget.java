package com.joyy.loadsir.target;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.joyy.loadsir.callback.Callback;
import com.joyy.loadsir.core.LoadLayout;
import com.joyy.loadsir.callback.SuccessCallback;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ActivityTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return target instanceof Activity;
    }

    //1. 给原来的内容，重新添加一个FrameLayout容器
    //2. 用SuccessCallback 将原来的View保存起来，这时view已经不在容器上
    //3. 通过找到这个容器，替换View
    @Override
    public LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener) {
        Activity activity = (Activity) target;
        ViewGroup contentParent = activity.findViewById(android.R.id.content);
        int childIndex = 0;
        View oldContent = contentParent.getChildAt(childIndex);
        contentParent.removeView(oldContent);

        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(activity, onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, activity,
                onReloadListener));
        contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        return loadLayout;
    }
}
