package com.credit.exg.exg.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.credit.exg.exg.R;
import com.credit.exg.exg.fragment.MyFragment;
import com.credit.exg.exg.fragment.RepairFragment;
import com.credit.exg.exg.fragment.SignInFragment;
import com.credit.exg.exg.init.PermissionListener;
import com.credit.exg.exg.utils.DoubleClickExitAppUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DoubleClickExitAppUtil doubleClickExitAppUtil;

    private RadioGroup radioGroup;

    public static final String fragment1Tag = "fragment1";
    public static final String fragment2Tag = "fragment2";
    public static final String fragment3Tag = "fragment3";

    RadioButton btn1, btn2, btn3;

    private int REQUEST_PERMISSION_CODE = 10000;//申请权限的请求码
    private PermissionListener permissionListener;//权限申请后的回调接口


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissionToReadUserContacts();
        doubleClickExitAppUtil = new DoubleClickExitAppUtil(this);

        radioGroup = (RadioGroup) findViewById(R.id.activity_group_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment1 = fm.findFragmentByTag(fragment1Tag);
                Fragment fragment2 = fm.findFragmentByTag(fragment2Tag);
                Fragment fragment3 = fm.findFragmentByTag(fragment3Tag);
                if (fragment1 != null) {
                    ft.hide(fragment1);
                }
                if (fragment2 != null) {
                    ft.hide(fragment2);
                }
                if (fragment3 != null) {
                    ft.hide(fragment3);
                }

//                if (t.equals("1")) {
//                    if (fragment2 == null) {
//                        fragment2 = new OrderFragemnt();
//                        ft.add(R.id.container, fragment2, fragment2Tag);
//                    }
//                    ft.show(fragment2);
//                }
                switch (checkedId) {

                    case R.id.order_process:
                        if (fragment1 == null) {
                            fragment1 = new RepairFragment();
                            ft.add(R.id.container, fragment1, fragment1Tag);
                        } else {
                            ft.show(fragment1);
                        }
                        break;
                    case R.id.order_query:
                        if (fragment2 == null) {
                            fragment2 = new SignInFragment();
                            ft.add(R.id.container, fragment2, fragment2Tag);
                        } else {
                            ft.show(fragment2);
                        }

                        break;
                    case R.id.merchant_manager:
                        if (fragment3 == null) {
                            fragment3 = new MyFragment();
                            ft.add(R.id.container, fragment3,
                                    fragment3Tag);
                        } else {
                            ft.show(fragment3);
                        }
                        break;
                    default:
                        break;
                }
                ft.commit();
            }
        });
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new RepairFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment1Tag).commit();
        }


//        Drawable d1 = getResources().getDrawable(R.drawable.select_home);
//        d1.setBounds(0, 0, 80, 80);
//        ((RadioButton) radioGroup.getChildAt(0)).setCompoundDrawables(null, d1, null, null);
//        Drawable d2 = getResources().getDrawable(R.drawable.select_dingdan);
//        d2.setBounds(0, 0, 80, 80);
//        ((RadioButton) radioGroup.getChildAt(1)).setCompoundDrawables(null, d2, null, null);
//        Drawable d3 = getResources().getDrawable(R.drawable.select_my);
//        d3.setBounds(0, 0, 80, 80);
//        ((RadioButton) radioGroup.getChildAt(2)).setCompoundDrawables(null, d3, null, null);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return doubleClickExitAppUtil.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton mTab = (RadioButton) radioGroup.getChildAt(i);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag((String) mTab.getTag());
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment != null) {
                if (!mTab.isChecked()) {
                    ft.hide(fragment);
                }
            }
            ft.commit();
        }
    }

    //定义请求
    private static final int READ_CONTACTS_REQUEST = 1;
    //当用户执行的操作需要权限时候进行询问
    public void getPermissionToReadUserContacts() {
        /**
         * 1)使用ContextCompat.chefkSelfPermission(),因为Context.permission
         * 只在棒棒糖系统中使用
         * 2）总是检查权限（即使权限被授予）因为用户可能会在设置中移除你的权限*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

            //权限为获取，检查用户是否被询问过并且拒绝了，如果是这样的话，给予更多
            //解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                //在界面上展示为什么需要读取联系人
                Toast.makeText(this, "需要定位位置权限,手机电话权限和存储权限才可以正常工作", Toast.LENGTH_SHORT).show();
            }
            //发起请求获得用户许可,可以在此请求多个权限
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE},
                    READ_CONTACTS_REQUEST);
        }
    }
    //从requestPermissions()方法回调结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //确保是我们的请求
        if (requestCode == READ_CONTACTS_REQUEST) {
            if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                System.out.println("应用权限获取成功");
            } else {
                System.out.println("应用权限获取失败");
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
