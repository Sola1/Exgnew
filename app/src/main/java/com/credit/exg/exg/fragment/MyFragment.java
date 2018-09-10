package com.credit.exg.exg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.credit.exg.exg.R;
import com.credit.exg.exg.activity.EditPwdActivity;
import com.credit.exg.exg.activity.PersonalCenterActivity;
import com.credit.exg.exg.base.BaseFragment;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by zhangjiaqi on 18/8/28.
 */

public class MyFragment extends BaseFragment {

    @ViewInject(R.id.eidt_pwd_linear)
    LinearLayout editPwdLinear;
    @ViewInject(R.id.personal_center_lienar)
    LinearLayout personalLinear;

    @Override
    protected void init() {
        editPwdLinear.setOnClickListener(this);
        personalLinear.setOnClickListener(this);
    }

    @Override
    protected void onCreateInit() {

    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_my, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eidt_pwd_linear:
                startActivity(new Intent(getActivity(), EditPwdActivity.class));
                break;
            case R.id.personal_center_lienar:
                startActivity(new Intent(getActivity(), PersonalCenterActivity.class));
                break;
        }
    }
}
