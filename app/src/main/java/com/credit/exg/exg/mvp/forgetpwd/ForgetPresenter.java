package com.credit.exg.exg.mvp.forgetpwd;

import android.content.Context;

import com.credit.exg.exg.base.BasePresenter;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.CodeBean;
import com.credit.exg.exg.mvp.login.LoginContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public class ForgetPresenter extends BasePresenter<ForgetContract.View,ForgetContract.Model> implements ForgetContract.Presenter{

    private static ForgetPresenter minePresenter;


    public static ForgetPresenter getInstance() {
        if (minePresenter == null) {
            synchronized (ForgetPresenter.class) {
                if (minePresenter == null) {

                    minePresenter = new ForgetPresenter();

                }

            }

        }


        return minePresenter;
    }

    @Override
    public void attachView(ForgetContract.View view, Context context) {
        mModel = new ForgetModel();
        super.attachView(view, context);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void start(String url, HashMap<String, String> param) {
        mModel.forgetPwd(param, context, new ForgetContract.Model.ICallback() {
            @Override
            public void callback(BaseBean data) {
                mView.result(data);
            }

            @Override
            public void error(String e) {
                mView.error(e);
            }
        });
    }

    @Override
    public void getCode(HashMap<String, String> param) {
        mModel.getCode(param, context, new ForgetContract.Model.CodeCallback() {
            @Override
            public void callback(CodeBean codeBean) {
                mView.codeResult(codeBean);
            }

            @Override
            public void error(String e) {
                mView.codeError(e);
            }
        });
    }
}
