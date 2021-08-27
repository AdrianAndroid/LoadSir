package com.joyy.loadsir.target;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import com.joyy.loadsir.callback.EmptyCallback;
import com.joyy.loadsir.callback.LoadingCallback;

import com.joyy.loadsir.PostUtil;
import com.joyy.loadsir.R;
import com.joyy.loadsir.base.BaseFragment;

/**
 * Description:TODO
 * Create Time:2017/9/5 13:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FragmentB extends BaseFragment {
    @BindView(R.id.tv_result_b)
    TextView mTvResultB;
    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_b_content;
    }

    @Override
    protected void loadNet() {
        // do net here...
        // call back
        PostUtil.postCallbackDelayed(mBaseLoadService, EmptyCallback.class);
    }
    @Override
    protected void onNetReload(View v) {
        mTvResultB.setText("Oh, Yes too.");
        Toast.makeText(getContext(),"reload in Fragment B",Toast.LENGTH_SHORT).show();
        mBaseLoadService.showCallback(LoadingCallback.class);
        //do retry logic...

        //callback
        PostUtil.postSuccessDelayed(mBaseLoadService);
    }
}