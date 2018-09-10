package com.credit.exg.exg.mvp.editpwd;

import android.content.Context;

import com.credit.exg.exg.base.BasePresenter;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.mvp.login.LoginPresenter;

import java.util.HashMap;

/**
 * Created by zhangjiaqi on 18/8/30.
 */

public class EditPwdPresenter extends BasePresenter<EditPwdConstract.View, EditPwdConstract.Model> implements EditPwdConstract.Presenter {

    private static EditPwdPresenter minePresenter;


    public static EditPwdPresenter getInstance() {
        if (minePresenter == null) {
            synchronized (EditPwdPresenter.class) {
                if (minePresenter == null) {

                    minePresenter = new EditPwdPresenter();

                }

            }

        }


        return minePresenter;
    }

    @Override
    public void attachView(EditPwdConstract.View view, Context context) {
        mModel = new EditPwdModel();
        super.attachView(view, context);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void start(String url, HashMap<String, String> param) {
        mModel.editPwd(param, context, new EditPwdConstract.Model.ICallback() {
            @Override
            public void icallback(BaseBean data) {
                mView.resutl(data);
            }

            @Override
            public void error(String e) {
                mView.error(e);
            }
        });
    }
}
