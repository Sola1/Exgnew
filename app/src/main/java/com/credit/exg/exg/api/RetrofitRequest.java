package com.credit.exg.exg.api;


import android.util.Log;

import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.CodeBean;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.bean.RepairBean;
import com.credit.exg.exg.bean.User;

import java.io.File;
import java.util.HashMap;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Streaming;
import retrofit.http.Url;
import rx.Observable;

/**
 *
 * Created by ccj on 2016/7/6.
 */
public interface RetrofitRequest {


    boolean isTest=false; //是否在测试环境下
    //发布之前更改
    String BASE_URL_TEST = "/flyapptest/";//测试服务器
    String BASE_URL_OFFICAL = "/flyapp/";//正式服务器

    String BASE_URL = isTest?BASE_URL_TEST:BASE_URL_OFFICAL;//发布服务器


    /**
     * 登录返回(json post)
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<Login> userLogin(@Body HashMap<String, String> body);

    /**
     * 忘记密码
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<BaseBean> forgetPwd(@Body HashMap<String, String> body);

    /**
     * 获取验证码
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<CodeBean> getCode(@Body HashMap<String, String> body);


    /**
     * 获取抢修数据
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<RepairBean> repairList(@Body HashMap<String, String> body);


    /**
     * 终止抢修
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<BaseBean> zzqx(@Body HashMap<String, String> body);

    /**
     * 报修接单
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<BaseBean> bxjd(@Body HashMap<String, String> body);

    /**
     * 到达现场
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<BaseBean> ddxc(@Body HashMap<String, String> body);


    /**
     * 修改密码
     * @param body
     * @return
     */
    @Headers( "Content-Type: application/json" )
    @POST(BASE_URL+"Login.ashx/")
    Observable<BaseBean> editPwd(@Body HashMap<String, String> body);

//
//    /**
//     * 请求补丁(json post)
//     * 根据versioncode请求
//     * post:{"VersionCode":"3"}
//     * @param body
//     * @return
//     */
//    @Headers( "Content-Type: application/json" )
//    @POST(BASE_URL+"Version/GetJar.ashx/")
//    Observable<Patch> getPatch(@Body HashMap<String, String> body);
//
//
//
//
//
//    /**
//     * 获取天气数据(测试)
//     * @param cityname
//     * @param key
//     * @return
//     */
//
//    @GET("/weather/index")
//    Observable<WeatherData> getWeatherData(@Query("format") String format, @Query("cityname") String cityname, @Query("key") String key);
//
//
//
//    @GET("/api/data/福利/{date}")
//    Observable<Meizhi> getMeiZhi(@Path("date") String date);


    /**
     * 下载补丁
     * @return
     */
    @GET
    @Streaming
    Observable<File> downPatch(@Url String url);

//    @Multipart
//    @POST("upload")
//    Observable<Meizhi> uploadFile(@Part("img1") MultipartBody.Part photo, @Part("username") RequestBody username, @Part("password") RequestBody password);





}
