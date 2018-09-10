package com.credit.exg.exg.mvp.login;

import android.content.Context;

import com.credit.exg.exg.base.IModel;
import com.credit.exg.exg.base.IPresenter;
import com.credit.exg.exg.base.IView;
import com.credit.exg.exg.bean.Login;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2016/10/17.
 */

public interface LoginContract {

    interface View extends IView {

        void result(Login bean);
        void error(String e);

    }

    interface Model extends IModel {

        void login(HashMap<String, String> param, Context context, ICallback iCallback);

        interface ICallback{
            void callback(Login data);
            void error(String e);

        }

    }

    interface Presenter extends IPresenter<View> {

    }

}
