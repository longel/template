package com.oliver.template.base.presenter;


import android.support.annotation.CallSuper;


import com.oliver.template.base.IBasePresenter;
import com.oliver.template.base.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 子Presenter 基类
 * 1.实现解绑和绑定的功能
 * 2.解决mvp可能存在的内存泄漏问题
 */
public class BaseMvpPresenter<V extends IBaseView> implements IBasePresenter<V> {

    public String TAG;

    private Reference<V> mViewRef;

    //动态代理，不用每次都调用isViewAttached()判空
    private V mProxyView;

    public BaseMvpPresenter() {
        TAG = getClass().getSimpleName();
    }

    @Override
    @CallSuper
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);

        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new MvpViewHandler());
    }

    @Override
    @CallSuper
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }

    }

    /**
     * 用于判断view ,是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 获取view,注意之前一定要调用isViewAttached()
     *
     * @return
     */
    public V getView() {
        return mProxyView;
    }


    /**
     * 凡是被代理对象调用其实现的接口的方法都会执行到其代理对象的InvocationHandler#invoke()
     */
    private class MvpViewHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            //假设V层没有被销毁，执行其方法
            if (isViewAttached()) {
                return method.invoke(mViewRef.get(), args);
            }
            return null;
        }
    }
}
