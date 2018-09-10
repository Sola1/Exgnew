package com.credit.exg.exg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.credit.exg.exg.R;
import com.credit.exg.exg.base.IViewActivity;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.mvp.login.LoginContract;
import com.credit.exg.exg.mvp.login.LoginPresenter;
import com.credit.exg.exg.utils.ToastUtil;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by zhangjiaqi on 18/8/28.
 */

public class LoginActivity extends IViewActivity<LoginPresenter> implements LoginContract.View {
    @ViewInject(R.id.account_et)
    EditText accountEt;
    @ViewInject(R.id.pwd_et)
    EditText pwdEt;
    @ViewInject(R.id.pwd_control_iv)
    ImageView controlIv;
    @ViewInject(R.id.forget_pwd)
    TextView forgetTv;
    @ViewInject(R.id.login_tv)
    TextView loginTv;
    boolean flag = false;

    @Override
    protected LoginPresenter onLoadPresenter() {
        return LoginPresenter.getInstance();
    }

    @Override
    protected Activity getMyContext() {
        return this;
    }

    @Override
    protected void init() {
        //将输入法切换到英文
        accountEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        //将输入法弹出的右下角的按钮改为完成，不改的话会是下一步。
        accountEt .setImeOptions(EditorInfo.IME_ACTION_DONE);
        loginTv.setOnClickListener(this);
        controlIv.setOnClickListener(this);
        forgetTv.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv:
                if (accountEt.getText().length() < 1) {
                    ToastUtil.showShort(LoginActivity.this, "请输入您的账号");
                    return;
                }
                if (pwdEt.getText().length() < 1) {
                    ToastUtil.showShort(LoginActivity.this, "请输入您的密码");
                    return;
                }
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.pwd_control_iv:
                if (!flag) {
                    controlIv.setImageResource(R.mipmap.show_pwd);
                    pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwdEt.setSelection(pwdEt.getText().length());
                    flag = true;
                } else {
                    //否则隐藏密码
                    controlIv.setImageResource(R.mipmap.no_show);
                    pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwdEt.setSelection(pwdEt.getText().length());
                    flag = false;
                }
                break;
            case R.id.forget_pwd:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(accountEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(pwdEt.getWindowToken(), 0);
        return true;

    }


    @Override
    public void result(Login bean) {

    }

    @Override
    public void error(String e) {

    }
}
