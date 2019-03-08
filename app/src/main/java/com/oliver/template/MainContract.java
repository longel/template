package com.oliver.template;

import com.oliver.template.base.IBasePresenter;
import com.oliver.template.base.IBaseView;

/**
 * author : Oliver
 * date   : 2019/3/8
 * desc   :
 */

public interface MainContract {

    interface IMainView extends IBaseView {
        void loadUserCallback(User user);

        void loginCallback();
    }

    interface IMainPresenter extends IBasePresenter<IMainView> {

        void loadUser();

        void login(String name,String pwd);
    }
}
