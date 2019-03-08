package com.oliver.template.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliver.template.base.IBaseView;


/**
 * 基类fragment
 */
public abstract class AbsBaseFragment extends Fragment implements IBaseView {
    //fragment依赖的Activity
    protected Activity mActivity;
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("AbsBaseFragment", "rootView:" + rootView);

        //缓存view
        if (rootView == null) {
            rootView = inflater.inflate(provideContentViewId(), container, false);
            initView(rootView);
            initListener();
        } else {
            //缓存的rootView需要判断是否已经被加过parent， 如果parent有需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (rootView.getParent() != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    protected void initData() {
    }


    protected void initListener() {
    }


    protected void initView(View view) {

    }

    protected <T extends View> T findView(@IdRes int id) {
        return rootView.findViewById(id);
    }

    protected boolean isViewVisible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }


    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return
     */
    protected abstract int provideContentViewId();

    public void post(Runnable runnable) {
        if (mActivity != null) {
            mActivity.runOnUiThread(runnable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // TODO 暂时方案
        // 在 v40 板子上会出现Fragment 布局重叠的问题，这里是为了解决这个问题
        rootView = null;
    }
}
