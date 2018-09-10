package com.credit.exg.exg.mvp.login;

import android.content.Context;

import com.credit.exg.exg.base.BasePresenter;
import com.credit.exg.exg.bean.Login;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2016/10/17.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    private static LoginPresenter minePresenter;



    public static LoginPresenter getInstance(){
        if (minePresenter == null){
            synchronized(LoginPresenter.class){
                if (minePresenter == null){

                    minePresenter = new LoginPresenter();

                }

            }

        }



        return minePresenter;
    }

    @Override
    public void attachView(LoginContract.View view, Context context) {
        mModel = new LoginModel();
        super.attachView(view,context);

    }

    @Override
    public void detachView() {
        super.detachView();
    }


    @Override
    public void start(String url, HashMap<String, String> param) {
        mModel.login(param, context, new LoginContract.Model.ICallback() {
            @Override
            public void callback(Login data) {


                mView.result(data);



            }

            @Override
            public void error(String e) {
                mView.error(e);
            }

        });
    }



}
