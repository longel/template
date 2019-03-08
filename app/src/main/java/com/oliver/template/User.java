package com.oliver.template;

/**
 * author : Oliver
 * date   : 2019/3/8
 * desc   :
 */

public class User {

    private String mUserName;
    private String mUserPwd;

    public User(){}

    public User(String userName, String userPwd) {
        mUserName = userName;
        mUserPwd = userPwd;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName == null ? "" : userName;
    }

    public String getUserPwd() {
        return mUserPwd;
    }

    public void setUserPwd(String userPwd) {
        mUserPwd = userPwd == null ? "" : userPwd;
    }
}
