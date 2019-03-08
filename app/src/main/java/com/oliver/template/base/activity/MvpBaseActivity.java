package com.oliver.template.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oliver.template.base.IBasePresenter;
import com.oliver.template.base.IBaseView;


/**
 * mvp Activity基类
 *
 * @param <V>
 * @param <P>
 */
public abstract class MvpBaseActivity<V extends IBaseView, P extends IBasePresenter<V>> extends AbsBaseActivity {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 创建presenter
     *
     * @return
     */
    public abstract P createPresenter();

}
