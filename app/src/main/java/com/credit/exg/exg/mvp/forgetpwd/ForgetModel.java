package com.credit.exg.exg.mvp.forgetpwd;

import android.content.Context;

import com.credit.exg.exg.api.APIService;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.CodeBean;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.mvp.login.LoginModel;
import com.credit.exg.exg.utils.TLog;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public class ForgetModel implements ForgetContract.Model{
    private static ForgetModel model;

    public static ForgetModel getInstance(){
        if (model == null){
            synchronized (LoginModel.class){
                if (model == null){
                    model = new ForgetModel();

                }

            }

        }
        return model;
    }
    @Override
    public void forgetPwd(HashMap<String, String> param, Context context, final ICallback iCallback) {
        Observable<BaseBean> userObservable = APIService.forgetPwd(param);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
//                        loginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
//                        loginView.hideProgress();
//                        loginView.showError(e.getMessage().toString());
                        iCallback.error(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        iCallback.callback(data);
                    }
                });
    }

    @Override
    public void getCode(HashMap<String, String> param, Context context, final CodeCallback codeCallback) {
        Observable<CodeBean> userObservable = APIService.getCode(param);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CodeBean>() {
                    @Override
                    public void onCompleted() {
//                        loginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.log(e.getMessage().toString());
//                        loginView.hideProgress();
//                        loginView.showError(e.getMessage().toString());
                        codeCallback.error(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(CodeBean data) {
                        codeCallback.callback(data);
                    }
                });
    }
}
