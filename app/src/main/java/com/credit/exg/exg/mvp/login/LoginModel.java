package com.credit.exg.exg.mvp.login;

import android.content.Context;

import com.credit.exg.exg.api.APIService;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.bean.User;
import com.credit.exg.exg.utils.TLog;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/10/17.
 */

public class LoginModel implements LoginContract.Model {
    private static LoginModel model;

    public static LoginModel getInstance(){
        if (model == null){
            synchronized (LoginModel.class){
                if (model == null){
                    model = new LoginModel();

                }

            }

        }
        return model;
    }


    @Override
    public void login(HashMap<String, String> param, Context context, final ICallback iCallback) {
        Observable<Login> userObservable = APIService.userLogin(param);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
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
                    public void onNext(Login data) {
                        iCallback.callback(data);
                    }
                });
    }
}

