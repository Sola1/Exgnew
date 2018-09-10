package com.credit.exg.exg.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.credit.exg.exg.R;


/**
 * Created by Administrator on 2016/10/17.
 */

public abstract class BasePresenter<T extends IView, M extends IModel> implements IPresenter<T> {

    protected static final String TAG = "BasePresenter";
    protected T mView;
    protected M mModel;
    protected Context context;
    private Dialog mLoadingDialog;


    @Override
    public void attachView(T view, Context context) {
        mView = view;
        this.context = context;

    }

    @Override
    public void detachView() {
        context = null;
        mView = null;
        mModel = null;
    }

    public boolean isViewAttached() {
        return mView != null && context != null;
    }

    public T getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }

    public void showLoading(String content) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = (TextView) view.findViewById(R.id.text);
        loadingText.setText(content);
        mLoadingDialog = new Dialog(context, R.style.Dialog);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setContentView(view,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();

    }

    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}

