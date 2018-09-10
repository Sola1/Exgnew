package com.credit.exg.exg.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2016/10/17.
 */

public abstract class IViewFragment <P extends IPresenter> extends BaseFragment implements IView{
    protected P mPresenter;
    protected Map<String,String> param = new HashMap<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = onLoadPresenter();
        if (getPresenter() != null){
            getPresenter().attachView(this,getContext());
        }


        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }

        super.onDestroy();
    }

    protected abstract P onLoadPresenter();
}
