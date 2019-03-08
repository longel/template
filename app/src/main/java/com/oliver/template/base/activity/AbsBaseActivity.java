package com.oliver.template.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.oliver.template.base.IBaseView;


/**
 * Activity基类
 */
public abstract class AbsBaseActivity extends AppCompatActivity implements IBaseView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSetting();
        setContentView(provideContentViewId());
        initViews();
        initListener();
        initData(savedInstanceState);
    }

    /**
     * 窗口配置
     */
    protected void initSetting() {

    }


    protected void initData(Bundle savedInstanceState) {
    }


    protected void initListener() {

    }


    protected void initViews() {

    }

    protected <T extends View> T findView(int viewId) {
        return findViewById(viewId);
    }


    /**
     * 填充布局
     *
     * @return
     */
    protected abstract int provideContentViewId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void post(Runnable runnable) {
        runOnUiThread(runnable);
    }
}
