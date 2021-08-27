package com.joyy.loadsir.target;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import com.joyy.loadsir.PostUtil;
import com.joyy.loadsir.R;
import com.joyy.loadsir.base.BaseTitleActivity;
import com.joyy.loadsir.callback.ErrorCallback;
import com.joyy.loadsir.callback.LoadingCallback;

/**
 * Description:
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class KeepTitleActivity extends BaseTitleActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;


    @Override
    protected String getContentTitle() {
        return "Title";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_content;
    }

    @Override
    protected void initView() {
        TextView mTvMsg = findViewById(R.id.tv_subTitle);
        mTvMsg.setText("Keep Title In Activity");
        mTvTitle.setText("Yes, Success");
    }

    @Override
    protected void initNet() {
        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
    }

    @Override
    protected void onNetReload(View v) {
        mBaseLoadService.showCallback(LoadingCallback.class);
        PostUtil.postSuccessDelayed(mBaseLoadService);
    }
}
