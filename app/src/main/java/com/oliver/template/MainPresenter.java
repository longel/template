package com.oliver.template;

import com.oliver.MyApp;
import com.oliver.template.base.presenter.BaseMvpPresenter;

/**
 * author : Oliver
 * date   : 2019/3/8
 * desc   :
 */

public class MainPresenter extends BaseMvpPresenter<MainContract.IMainView>
        implements MainContract.IMainPresenter {


    @Override
    public void attachView(MainContract.IMainView view) {
        super.attachView(view);

    }

    @Override
    public void detachView() {
        super.detachView();

    }

    @Override
    public void loadUser() {
        getView().loadUserCallback(MyApp.sUser);
    }

    @Override
    public void login(String name, String pwd) {
        MyApp.sUser = new User(name, pwd);
        getView().loginCallback();
    }
}
