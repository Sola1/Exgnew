package com.credit.exg.exg.mvp.editpwd;

import android.content.Context;

import com.credit.exg.exg.api.APIService;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.mvp.login.LoginModel;
import com.credit.exg.exg.utils.TLog;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangjiaqi on 18/8/30.
 */

public class EditPwdModel implements EditPwdConstract.Model{
    private static EditPwdModel model;

    public static EditPwdModel getInstance(){
        if (model == null){
            synchronized (EditPwdModel.class){
                if (model == null){
                    model = new EditPwdModel();

                }

            }

        }
        return model;
    }


    @Override
    public void editPwd(HashMap<String, String> param, Context context, final ICallback iCallback) {
        Observable<BaseBean> userObservable = APIService.editPwd(param);
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
                        iCallback.icallback(data);
                    }
                });
    }
}
