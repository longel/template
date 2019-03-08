package com.oliver.template;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oliver.template.base.activity.MvpBaseActivity;
import com.oliver.template.second.Main2Activity;

public class MainActivity extends MvpBaseActivity<MainContract.IMainView, MainContract.IMainPresenter>
        implements MainContract.IMainView {

    private EditText mEtName;
    private EditText mEtPwd;
    private TextView mTvCancel;
    private TextView mTvConfirm;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.IMainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initViews() {
        super.initViews();
        mEtName = findView(R.id.et_username);
        mEtPwd = findView(R.id.et_pwd);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mPresenter.loadUser();
    }

    @Override
    public void loadUserCallback(User user) {
        if (user != null) {
            mEtName.setText(user.getUserName());
            mEtPwd.setText(user.getUserPwd());
        }
    }

    @Override
    public void loginCallback() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();

        mEtPwd.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        },2000);
    }

    public void login(View view) {
        if (mEtName.getText().toString().length() < 0
                || mEtPwd.getText().toString().length() < 0) {
            Toast.makeText(this, "用户名或密码不合法", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.login(mEtName.getText().toString(), mEtPwd.getText().toString());
        }
    }

    public void cancel(View view) {
        mEtName.setText("");
        mEtPwd.setText("");
    }
}
