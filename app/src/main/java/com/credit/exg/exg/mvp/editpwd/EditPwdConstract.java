package com.credit.exg.exg.mvp.editpwd;

import android.content.Context;

import com.credit.exg.exg.base.IModel;
import com.credit.exg.exg.base.IPresenter;
import com.credit.exg.exg.base.IView;
import com.credit.exg.exg.bean.BaseBean;

import java.util.HashMap;

/**
 * Created by zhangjiaqi on 18/8/30.
 */

public interface EditPwdConstract {

    interface View extends IView {
        void resutl(BaseBean data);

        void error(String e);
    }

    interface Model extends IModel {
        void editPwd(HashMap<String, String> param, Context context, ICallback iCallback);

        interface ICallback {
            void icallback(BaseBean data);

            void error(String e);
        }
    }

    interface Presenter extends IPresenter<View> {

    }
}
