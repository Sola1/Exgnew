package com.credit.exg.exg.mvp.forgetpwd;

import android.content.Context;

import com.credit.exg.exg.base.IModel;
import com.credit.exg.exg.base.IPresenter;
import com.credit.exg.exg.base.IView;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.CodeBean;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.mvp.login.LoginContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public interface ForgetContract {

    interface View extends IView {

        void result(BaseBean bean);
        void error(String e);

        void codeResult(CodeBean bean);
        void codeError(String e);

    }
    interface Model extends IModel {

        void forgetPwd(HashMap<String, String> param, Context context, ForgetContract.Model.ICallback iCallback);

        interface ICallback{
            void callback(BaseBean data);
            void error(String e);

        }
        void getCode(HashMap<String, String> param, Context context, CodeCallback codeCallback);
        interface CodeCallback{
            void callback(CodeBean codeBean);
            void error(String e);
        }

    }
    interface Presenter extends IPresenter<ForgetContract.View> {
        void getCode(HashMap<String, String> param);
    }
}
