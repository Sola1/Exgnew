package com.credit.exg.exg.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.credit.exg.exg.R;
import com.credit.exg.exg.base.BaseActivity;
import com.credit.exg.exg.utils.NetworkUtil;
import com.credit.exg.exg.utils.PermissionsUtils;
import com.credit.exg.exg.utils.ToastUtil;
import com.credit.exg.exg.widget.BaseBottomView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by zhangjiaqi on 18/8/30.
 */

public class PersonalCenterActivity extends BaseActivity {
    @ViewInject(R.id.icon_iv)
    ImageView iconIv;
    @ViewInject(R.id.back_rel)
    RelativeLayout backRel;

    private final int NEED_CAMERA = 200;
    private Integer sdk_int;

    private BaseBottomView bottomView;
    private File file1;
    private Bitmap head;
    private String headPath;
    List<File> fileList;
    @Override
    protected Activity getMyContext() {
        return this;
    }

    @Override
    protected void init() {
        backRel.setOnClickListener(this);
        iconIv.setOnClickListener(this);
        fileList = new ArrayList<>();
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_personal_center;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_rel:
                finish();
                break;
            case R.id.icon_iv:
                if (NetworkUtil.isNetworkAvailable()) {
                    showPopupwindow(iconIv);
                } else {
                    ToastUtil.showShort(this, "网络不可用,请检查网络");
                }
                break;

            case R.id.takePhotoBtnId://拍照
                sdk_int = Integer.valueOf(Build.VERSION.SDK);
                System.out.println("sdk0:::" + sdk_int);
                if (sdk_int < 24) {//Android6.0
                    if (!PermissionsUtils.hasCameraPermission(this)) {
                        PermissionGen.with(this)
                                .addRequestCode(100)
                                .permissions(
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_SETTINGS,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.GET_ACCOUNTS,
                                        Manifest.permission.CAMERA)
                                .request();

                        Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent3.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        intent3.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "head.jpg")));
                        startActivityForResult(intent3, 2);//采用ForResult打开
                    }
                } else {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        PersonalCenterActivity.this.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, NEED_CAMERA);
                    } else {
                        startCamera();
                    }
                }

                bottomView.dismiss();

                break;
            case R.id.pickPhtotBtnId://相册
                Integer sdk_int2 = Integer.valueOf(Build.VERSION.SDK);
                if (sdk_int2 >= 23) {
                    PermissionGen.with(this)
                            .addRequestCode(100)
                            .permissions(
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_SETTINGS,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.GET_ACCOUNTS)
                            .request();
                }
                Intent intent4 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent4, 1);
                bottomView.dismiss();
                break;
            case R.id.cancel_btnId:
                bottomView.dismiss();
                break;
        }
    }

    //点击头像弹出的popuwindow
    private void showPopupwindow(ImageView headRelative) {
        bottomView = new BaseBottomView(this, R.layout.head_change_popu);

        TextView blackTv;
        blackTv = ((TextView) bottomView.findViewById(R.id.headChangePopupBlackId));
        blackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomView.dismiss();
            }
        });
        bottomView.setCancelable(true);
        bottomView.show();
        Button b1 = (Button) bottomView.findViewById(R.id.takePhotoBtnId);
        b1.setOnClickListener(this);
        Button b2 = (Button) bottomView.findViewById(R.id.pickPhtotBtnId);
        b2.setOnClickListener(this);
        Button b3 = (Button) bottomView.findViewById(R.id.cancel_btnId);
        b3.setOnClickListener(this);
    }

    /**
     * 打开相机获取图片
     */
    private void startCamera() {

        file1 = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file1.getParentFile().exists()) file1.getParentFile().mkdirs();
        Uri imageUri = FileProvider.getUriForFile(this, "com.credit.exg.exg.provider", file1);//通过FileProvider创建一个content类型的Uri
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, 2);

//        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        intent2.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//        startActivityForResult(intent2, 2);//采用ForResult打开
//        startActivityForResult(intent2, 1000);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case NEED_CAMERA:
                // 如果权限被拒绝，grantResults 为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    Toast.makeText(this, "改功能需要相机和读写文件权限", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    if (sdk_int < 24) {
                        File temp = new File(Environment.getExternalStorageDirectory()
                                + "/head.jpg");
                        cropPhoto(Uri.fromFile(temp));//裁剪图片
                    } else {
                        cropPicture(this, file1.getAbsolutePath());//裁剪图片
                    }
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    try {

                        head = extras.getParcelable("data");
                    } catch (Exception e) {

                    }
                    if (head != null) {
                        this.iconIv.setImageBitmap(head);//用ImageView显示出来
                        setPicToView(head);//保存在SD卡中
//                        /**
//                         * 上传服务器代码
//                         */
//                        okhttpUpload();
                    }

                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setPicToView(Bitmap bm) {

        Log.i("headinfo", "保存图片");
        Format format = new SimpleDateFormat("yyyyMMddHHMMSS");
        String fileName = "my.png";
        headPath = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;

        Log.e("ccccccccccc", headPath);
        File f = new File(headPath);
        Log.i("headinfo", "图片path:" + headPath);
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            fileList.add(f);
            Log.i("headinfo", "图片保存成功");
            //上传服务器
            if (NetworkUtil.getActiveNetwork(this)) {
//                okhttpUpload(headPath, bm);
            } else {
                ToastUtil.showShort(this, "上传失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 160);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 调用系统剪裁功能
     */
    public void cropPicture(Activity activity, String path) {
//        File temp = new File(Environment.getExternalStorageDirectory()
//                + "/head.jpg");
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri;
//        Uri outputUri;
//        crop_image = createImagePath(APP_NAME + "_crop_" + DATE);


        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        //通过FileProvider创建一个content类型的Uri
        imageUri = FileProvider.getUriForFile(activity, "com.credit.exg.exg.provider", file);
//            outputUri = Uri.fromFile(new File(temp));
        //TODO:outputUri不需要ContentUri,否则失败
        //outputUri = FileProvider.getUriForFile(activity, "com.solux.furniture.fileprovider", new File(crop_image));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 160);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
}
