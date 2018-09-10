package com.credit.exg.exg.mvp.repair;

import android.content.Context;

import com.credit.exg.exg.base.IModel;
import com.credit.exg.exg.base.IPresenter;
import com.credit.exg.exg.base.IView;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.RepairBean;

import java.util.HashMap;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public interface RepairContrac {
    interface View extends IView {
        void result(RepairBean data);

        void error(String e);

        void zzqxResult(BaseBean data);

        void zzqxError(String e);

        void bxjdResult(BaseBean data);

        void bxjdError(String e);

        void ddxcResult(BaseBean data);

        void ddxcError(String e);
    }

    interface Model extends IModel {
        void getData(HashMap<String, String> param, Context context, RepairContrac.Model.ICallback iCallback);

        interface ICallback {
            void callback(RepairBean data);

            void error(String e);

        }

        void zzqxData(HashMap<String, String> param, Context context, ZZQXCallback zzqxCallback);

        interface ZZQXCallback {
            void zzqxCallback(BaseBean data);

            void zzqxError(String e);

        }

        void bxjdData(HashMap<String, String> param, Context context, BXJDCallback bxjdCallback);

        interface BXJDCallback {
            void bxjdCallback(BaseBean data);

            void bxjdError(String e);
        }

        void ddxcData(HashMap<String, String> param, Context context, DDXCCallback ddxcCallback);

        interface DDXCCallback {
            void ddxcCallback(BaseBean data);

            void ddxcError(String e);
        }
    }

    interface Presenter extends IPresenter<RepairContrac.View> {
        void zzqxData(HashMap<String, String> param);

        void bxjdData(HashMap<String, String> param);

        void ddxcData(HashMap<String, String> param);
    }
}
