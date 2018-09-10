package com.credit.exg.exg.activity;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.credit.exg.exg.R;
import com.credit.exg.exg.base.IViewActivity;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.CodeBean;
import com.credit.exg.exg.mvp.forgetpwd.ForgetContract;
import com.credit.exg.exg.mvp.forgetpwd.ForgetPresenter;
import com.credit.exg.exg.utils.CountDownTimerUtils;
import com.credit.exg.exg.utils.TelUtils;
import com.credit.exg.exg.utils.ToastUtil;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public class ForgetPwdActivity extends IViewActivity<ForgetPresenter> implements ForgetContract.View {
    @ViewInject(R.id.next_tv)
    TextView nextTv;
    @ViewInject(R.id.forget_desc_tv)
    TextView descTv;
    @ViewInject(R.id.account_et)
    EditText telEt;
    @ViewInject(R.id.code_et)
    EditText codeEt;
    @ViewInject(R.id.get_code_btn)
    Button getCodeBtn;

    @ViewInject(R.id.back_iv)
    ImageView backIv;

    @ViewInject(R.id.status_1_linear)
    LinearLayout linearLayout1;
    @ViewInject(R.id.status_2_linear)
    LinearLayout linearLayout2;
    int status = 1;
    boolean flag = false;

    @ViewInject(R.id.new_pwd_et)
    EditText newPwdEt;
    @ViewInject(R.id.confirm_pwd_et)
    EditText confirmPwdEt;

    @ViewInject(R.id.confirm_tv)
    TextView confrimTv;

    @ViewInject(R.id.pwd_control_iv)
    ImageView controlIv;

    @Override
    protected ForgetPresenter onLoadPresenter() {
        return ForgetPresenter.getInstance();
    }

    @Override
    protected Activity getMyContext() {
        return this;
    }

    @Override
    protected void init() {

        //将输入法切换到英文
        newPwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        //将输入法弹出的右下角的按钮改为完成，不改的话会是下一步。
        newPwdEt.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //将输入法切换到英文
        confirmPwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        //将输入法弹出的右下角的按钮改为完成，不改的话会是下一步。
        confirmPwdEt.setImeOptions(EditorInfo.IME_ACTION_DONE);

        nextTv.setEnabled(false);
        if (status == 1) {
            descTv.setText("请依照步骤找回密码");
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
        } else {
            descTv.setText("请设定新密码");
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
        }
        backIv.setOnClickListener(this);
        confrimTv.setOnClickListener(this);
        controlIv.setOnClickListener(this);
        nextTv.setOnClickListener(this);
        getCodeBtn.setOnClickListener(this);
        codeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    nextTv.setEnabled(false);
                    nextTv.setBackground(getResources().getDrawable(R.drawable.commit_shape_gra));
                } else {
                    nextTv.setEnabled(true);
                    nextTv.setBackground(getResources().getDrawable(R.drawable.commit_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(telEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(codeEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(newPwdEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(confirmPwdEt.getWindowToken(), 0);
        return true;

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_forgetpwd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pwd_control_iv:
                if (!flag) {
                    controlIv.setImageResource(R.mipmap.show_pwd);
                    confirmPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPwdEt.setSelection(confirmPwdEt.getText().length());
                    flag = true;
                } else {
                    //否则隐藏密码
                    controlIv.setImageResource(R.mipmap.no_show);
                    confirmPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPwdEt.setSelection(confirmPwdEt.getText().length());
                    flag = false;
                }
                break;
            case R.id.get_code_btn:
                if (codeEt.getText().length() < 1) {
                    ToastUtil.showShort(ForgetPwdActivity.this, "请输入验证码");
                    return;
                }
                getCodeBtn.setBackground(getResources().getDrawable(R.drawable.commit_shape_gra));
                CountDownTimerUtils countUtils = new CountDownTimerUtils(60000, 1000, getCodeBtn, codeEt, this);
                countUtils.start();
                break;
            case R.id.next_tv://下一步
                if (telEt.getText().length() < 1) {
                    ToastUtil.showShort(this, "请输入手机号");
                    return;
                }
                if (codeEt.getText().length() < 1) {
                    ToastUtil.showShort(this, "请输入验证码");
                    return;
                }
                if (TelUtils.isChinaPhoneLegal(telEt.getText().toString())) {

                } else {
                    ToastUtil.showShort(this, "请输入正确的手机号");
                    return;
                }
                status = 2;
                init();
                break;

            case R.id.confirm_tv://确定
                break;
            case R.id.back_iv:
                finish();
                break;
        }
    }

    @Override
    public void result(BaseBean bean) {

    }

    @Override
    public void error(String e) {

    }

    @Override
    public void codeResult(CodeBean bean) {
    }

    @Override
    public void codeError(String e) {

    }
}
