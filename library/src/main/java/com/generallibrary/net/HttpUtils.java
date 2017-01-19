package com.generallibrary.net;



import com.generallibrary.okhttp.OkHttpUtils;
import com.generallibrary.okhttp.callback.Callback;
import com.generallibrary.okhttp.request.RequestCall;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * zhy的okhttp封装的再次封装
 * Created by feng on 2016/10/12.
 */
public class HttpUtils implements IHttpUtils {

    public void get(String url, final Callback callBack) {
        get(url, new HashMap<String, String>(), callBack);
    }

    /**
     * get请求
     *
     * @param url      接口地址
     * @param params   参数
     * @param callBack 回调
     */
    public void get(String url, Map<String, String> params, final Callback callBack) {
        RequestCall call = OkHttpUtils.get().url(url).params(params).build();
        call.execute(callBack);
    }


    /**
     * post请求
     *
     * @param url      接口地址
     * @param params   参数
     * @param callBack 回调
     */
    public void post(String url, Map<String, String> params, final Callback callBack) {
        RequestCall call = OkHttpUtils.post().url(url).params(params).build();
        call.execute(callBack);
    }

    /**
     * post请求
     *
     * @param url      接口地址
     * @param params   参数
     * @param callBack 回调
     */
    public void postFile(String url, Map<String, String> params, String fileKey, File file, final Callback callBack) {
        RequestCall call;
        if (file != null && file.exists()) {
            call = OkHttpUtils.post().addFile(fileKey, file.getName(), file).url(url).params(params).build();
        } else {
            call = OkHttpUtils.post().url(url).params(params).build();
        }
        call.execute(callBack);
    }

    @Override
    public void postFile(String url, Map<String, String> params, Callback callBack) {
        RequestCall call;
        call = OkHttpUtils.post().url(url).params(params).build();
        call.execute(callBack);
    }
}