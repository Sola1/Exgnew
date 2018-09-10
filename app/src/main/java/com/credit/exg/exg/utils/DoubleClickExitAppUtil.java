package com.credit.exg.exg.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by zhangjiaqi on 16/10/28.
 */
public class DoubleClickExitAppUtil {
    private Activity mActivity;
    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;

    public DoubleClickExitAppUtil(Activity activity){
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Activity：onKeyDown事件
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode!= KeyEvent.KEYCODE_BACK){
            return false;
        }
        if(isOnKeyBacking){
            mHandler.removeCallbacks(onBackTimeRunnable);
            if(mBackToast!=null){
                mBackToast.cancel();
            }
            mActivity.finish();
            return true;
        }else{
            isOnKeyBacking = true;
            if(mBackToast == null){
                mBackToast = Toast.makeText(mActivity,"再按一次退出", Toast.LENGTH_SHORT);
            }
            mBackToast.show();
            //延迟两秒的时间，把Runnable发出去
            mHandler.postDelayed(onBackTimeRunnable,2000);
            return true;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            isOnKeyBacking = false;
            if(mBackToast!=null){
                mBackToast.cancel();
            }
        }
    };
}
