package com.credit.exg.exg.activity;

import android.app.Activity;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.credit.exg.exg.R;
import com.credit.exg.exg.base.IViewActivity;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.mvp.editpwd.EditPwdConstract;
import com.credit.exg.exg.mvp.editpwd.EditPwdPresenter;
import com.credit.exg.exg.utils.ToastUtil;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by zhangjiaqi on 18/8/30.
 */

public class EditPwdActivity extends IViewActivity<EditPwdPresenter> implements EditPwdConstract.View {

    @ViewInject(R.id.back_rel)
    RelativeLayout backRel;
    @ViewInject(R.id.old_pwd_et)
    EditText oldPwdEt;
    @ViewInject(R.id.new_pwd_et)
    EditText newPwdEt;
    @ViewInject(R.id.confirm_pwd_et)
    EditText confirmPwdEt;

    @ViewInject(R.id.pwd_control_iv)
    ImageView control1;
    @ViewInject(R.id.pwd_control_iv1)
    ImageView control2;

    @ViewInject(R.id.commit_tv)
    TextView commitTv;
    boolean flag = false;
    boolean flag1 = false;

    @Override
    protected EditPwdPresenter onLoadPresenter() {
        return EditPwdPresenter.getInstance();
    }

    @Override
    protected Activity getMyContext() {
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(newPwdEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(oldPwdEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(confirmPwdEt.getWindowToken(), 0);
        return true;

    }


    @Override
    protected void init() {
        backRel.setOnClickListener(this);
        control1.setOnClickListener(this);
        control2.setOnClickListener(this);
        commitTv.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_edit_pwd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_rel:
                finish();
                break;
            case R.id.pwd_control_iv:
                if (!flag) {
                    control1.setImageResource(R.mipmap.show_pwd);
                    newPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newPwdEt.setSelection(newPwdEt.getText().length());
                    flag = true;
                } else {
                    //否则隐藏密码
                    control1.setImageResource(R.mipmap.no_show);
                    newPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newPwdEt.setSelection(newPwdEt.getText().length());
                    flag = false;
                }
                break;
            case R.id.pwd_control_iv1:
                if (!flag1) {
                    control2.setImageResource(R.mipmap.show_pwd);
                    confirmPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPwdEt.setSelection(confirmPwdEt.getText().length());
                    flag1 = true;
                } else {
                    //否则隐藏密码
                    control2.setImageResource(R.mipmap.no_show);
                    confirmPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPwdEt.setSelection(confirmPwdEt.getText().length());
                    flag1 = false;
                }
                break;
            case R.id.commit_tv:
                if (oldPwdEt.getText().length() < 1) {
                    ToastUtil.showShort(this, "请输入原始密码");
                    return;
                }
                if (newPwdEt.getText().length() < 1) {
                    ToastUtil.showShort(this, "请输入新密码");
                    return;
                }
                if (confirmPwdEt.getText().length() < 1) {
                    ToastUtil.showShort(this, "请再次输入密码");
                    return;
                }
                if (!newPwdEt.getText().toString().equals(confirmPwdEt.getText().toString())) {
                    ToastUtil.showShort(this, "两次的输入的密码不一致");
                    return;
                }
                break;
        }
    }

    @Override
    public void resutl(BaseBean data) {

    }

    @Override
    public void error(String e) {

    }
}
