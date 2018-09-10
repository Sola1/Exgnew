package com.credit.exg.exg.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.EditText;

import com.credit.exg.exg.R;


/**
 * Created by zhangjiaqi on 16/10/28.
 *  Android实现获取验证码的倒计时功能
 */
public class CountDownTimerUtils extends CountDownTimer {
    //使用
    //CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(mButton, 60000, 1000);

    //    mCountDownTimerUtils.start();
    private Button mTextView;
    private EditText identifyEt;
    Context context;

    public CountDownTimerUtils(long millisInFuture, long countDownInterval, Button mTextView, EditText identifyEt,Context context) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;
        this.identifyEt = identifyEt;
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);
        mTextView.setText(millisUntilFinished / 1000 + " s");//设置倒计时时间

        //获取按钮上面的字
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.WHITE);
        //将倒计时时间设置为红色
        spannableString.setSpan(span,0,2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

    }

    @Override
    public void onFinish() {
        mTextView.setBackground(context.getResources().getDrawable(R.drawable.getcode_shape));
        mTextView.setText("重新获取");
        identifyEt.setText("");
        identifyEt.setFocusable(true);
        identifyEt.setFocusableInTouchMode(true);
        identifyEt.requestFocus();
        mTextView.setClickable(true);
//        mTextView.setBackgroundResource();
    }
}
