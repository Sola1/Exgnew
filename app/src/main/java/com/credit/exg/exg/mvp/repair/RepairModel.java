package com.credit.exg.exg.mvp.repair;

import android.content.Context;

import com.credit.exg.exg.api.APIService;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.bean.RepairBean;
import com.credit.exg.exg.mvp.login.LoginModel;
import com.credit.exg.exg.utils.TLog;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public class RepairModel implements RepairContrac.Model {
    private static RepairModel model;

    public static RepairModel getInstance() {
        if (model == null) {
            synchronized (RepairModel.class) {
                if (model == null) {
                    model = new RepairModel();

                }

            }

        }
        return model;
    }


    @Override
    public void getData(HashMap<String, String> param, Context context, final ICallback iCallback) {
        Observable<RepairBean> userObservable = APIService.repairList(param);
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RepairBean>() {
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
                    public void onNext(RepairBean data) {
                        iCallback.callback(data);
                    }
                });
    }

    @Override
    public void zzqxData(HashMap<String, String> param, Context context, final ZZQXCallback zzqxCallback) {
        Observable<BaseBean> userObservable = APIService.zzqx(param);
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
                        zzqxCallback.zzqxError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        zzqxCallback.zzqxCallback(data);
                    }
                });
    }

    @Override
    public void bxjdData(HashMap<String, String> param, Context context, final BXJDCallback bxjdCallback) {
        Observable<BaseBean> userObservable = APIService.bxjd(param);
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
                        bxjdCallback.bxjdError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        bxjdCallback.bxjdCallback(data);
                    }
                });
    }

    @Override
    public void ddxcData(HashMap<String, String> param, Context context, final DDXCCallback ddxcCallback) {
        Observable<BaseBean> userObservable = APIService.ddxc(param);
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
                        ddxcCallback.ddxcError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        ddxcCallback.ddxcCallback(data);
                    }
                });
    }
}
