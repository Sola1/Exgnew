package com.credit.exg.exg.base;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2016/10/17.
 */

public abstract class IViewActivity<P extends IPresenter> extends BaseActivity implements IView{
    protected P mPresenter;
    protected Map<String,String> param = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = onLoadPresenter();
        if (getPresenter() != null){
            getPresenter().attachView(this,this);
        }
        super.onCreate(savedInstanceState);




    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract P onLoadPresenter();

}
