package com.credit.exg.exg.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = createView(inflater,container,savedInstanceState);
        ViewUtils.inject(this, view);

        onCreateInit();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    protected abstract void init();
    protected abstract void onCreateInit();
    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
