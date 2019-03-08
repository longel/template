package com.oliver.template.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oliver.template.base.IBasePresenter;
import com.oliver.template.base.IBaseView;


/**
 * mvp Fragment基类
 *
 * @param <V>
 * @param <P>
 */
public abstract class MvpBaseFragment<V extends IBaseView, P extends IBasePresenter<V>> extends AbsBaseFragment {
    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 创建presenter
     *
     * @return
     */
    public abstract P createPresenter();
}
