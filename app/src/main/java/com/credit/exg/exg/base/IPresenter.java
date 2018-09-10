package com.credit.exg.exg.base;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface IPresenter <T extends IView> {
    void attachView(T view, Context context);
    void start(String url, HashMap<String, String> param);
    void detachView();
}
