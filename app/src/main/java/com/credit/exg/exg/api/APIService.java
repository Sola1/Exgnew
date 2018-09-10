package com.credit.exg.exg.api;



import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.CodeBean;
import com.credit.exg.exg.bean.Login;
import com.credit.exg.exg.bean.RepairBean;
import com.credit.exg.exg.bean.User;

import java.util.HashMap;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * 调用后台的接口,架构网络层采用Retroft+Rxjava+gson
 * Created by ccj on 2016/7/1.
 *
 */
public class APIService {

    private static final String TAG = "APIService";
    public static final String URL_HOST ="http://123.234.82.23" ;//服务器端口

    //get请求
    public static final String URL_GANK_IO ="http://gank.io" ;//gank.io 中的妹子API


    /**
     * 基础地址
     * 初始化 retroft
     */
    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(URL_GANK_IO==null?URL_HOST:URL_GANK_IO)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final RetrofitRequest apiManager = sRetrofit.create(RetrofitRequest.class);
    /**
     * 登录,返回,我这边用的是json格式的post,大家可以进行选择
     * @param
     * @return
     */
    public static Observable<Login> userLogin(HashMap<String,String> map) {
        Observable<Login> ss = apiManager.userLogin(map);
        return  ss;
    }

    public static Observable<BaseBean> forgetPwd(HashMap<String,String> map) {
        Observable<BaseBean> ss = apiManager.forgetPwd(map);
        return  ss;
    }

    public static Observable<CodeBean> getCode(HashMap<String,String> map) {
        Observable<CodeBean> ss = apiManager.getCode(map);
        return  ss;
    }

    public static Observable<RepairBean> repairList(HashMap<String,String> map) {
        Observable<RepairBean> ss = apiManager.repairList(map);
        return  ss;
    }

    public static Observable<BaseBean> zzqx(HashMap<String,String> map) {
        Observable<BaseBean> ss = apiManager.zzqx(map);
        return  ss;
    }

    public static Observable<BaseBean> bxjd(HashMap<String,String> map) {
        Observable<BaseBean> ss = apiManager.zzqx(map);
        return  ss;
    }

    public static Observable<BaseBean> ddxc(HashMap<String,String> map) {
        Observable<BaseBean> ss = apiManager.zzqx(map);
        return  ss;
    }

    public static Observable<BaseBean> editPwd(HashMap<String,String> map) {
        Observable<BaseBean> ss = apiManager.editPwd(map);
        return  ss;
    }
//
//    /**
//     *
//     * @param versionCode 当前versionCode来校验,判断应该返回那个补丁包
//     * @return
//     */
//    public static Observable<Patch> getPatch(String versionCode) {
//        HashMap<String,String> hashMap =new HashMap<>();
//        hashMap.put("VersionCode", versionCode);
//        TLog.logI(hashMap.toString());
//        Observable<Patch> ss = apiManager.getPatch(hashMap);
//        return  ss;
//    }
//
//    /**
//     *
//     *
//     * @return
//     */
//    public static Observable<Meizhi> getMeiZhi(String date) {
//        Observable<Meizhi> ss = apiManager.getMeiZhi( date);
//        TLog.logI(date);
//        return  ss;
//    }
//
//    /**
//     *下载补丁包
//     * @param downUrl
//     * @return
//     */
//    public static Observable<File> downPatch(String downUrl) {
//
//        Observable<File> ss = apiManager.downPatch(downUrl);
//        TLog.log(downUrl.toString());
//        return ss;
//    }



    /**********************仿照上面的方法,进行请求数据****************************/





}
