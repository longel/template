package com.oliver.template.base;

/**
 * 1.约束Presenter 类型
 * 2.提供view的绑定和解绑的操作
 */
public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);

    void detachView();
}
