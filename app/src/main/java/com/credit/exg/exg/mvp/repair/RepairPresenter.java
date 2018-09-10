package com.credit.exg.exg.mvp.repair;

import android.content.Context;

import com.credit.exg.exg.base.BasePresenter;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.RepairBean;
import com.credit.exg.exg.mvp.login.LoginContract;
import com.credit.exg.exg.mvp.login.LoginModel;
import com.credit.exg.exg.mvp.login.LoginPresenter;

import java.util.HashMap;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public class RepairPresenter extends BasePresenter<RepairContrac.View, RepairContrac.Model> implements RepairContrac.Presenter {
    private static RepairPresenter minePresenter;


    public static RepairPresenter getInstance() {
        if (minePresenter == null) {
            synchronized (RepairPresenter.class) {
                if (minePresenter == null) {

                    minePresenter = new RepairPresenter();

                }

            }

        }


        return minePresenter;
    }

    @Override
    public void attachView(RepairContrac.View view, Context context) {
        mModel = new RepairModel();
        super.attachView(view, context);

    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void start(String url, HashMap<String, String> param) {
        mModel.getData(param, context, new RepairContrac.Model.ICallback() {
            @Override
            public void callback(RepairBean data) {
                mView.result(data);
            }

            @Override
            public void error(String e) {
                mView.error(e);
            }
        });
    }

    @Override
    public void zzqxData(HashMap<String, String> param) {
        mModel.zzqxData(param, context, new RepairContrac.Model.ZZQXCallback() {
            @Override
            public void zzqxCallback(BaseBean data) {
                mView.zzqxResult(data);
            }

            @Override
            public void zzqxError(String e) {
                mView.zzqxError(e);
            }
        });
    }

    @Override
    public void bxjdData(HashMap<String, String> param) {
        mModel.bxjdData(param, context, new RepairContrac.Model.BXJDCallback() {
            @Override
            public void bxjdCallback(BaseBean data) {
                mView.bxjdResult(data);
            }

            @Override
            public void bxjdError(String e) {
                mView.bxjdError(e);
            }
        });
    }

    @Override
    public void ddxcData(HashMap<String, String> param) {
        mModel.ddxcData(param, context, new RepairContrac.Model.DDXCCallback() {
            @Override
            public void ddxcCallback(BaseBean data) {
                mView.ddxcResult(data);
            }

            @Override
            public void ddxcError(String e) {
                mView.ddxcError(e);
            }
        });
    }
}
