package com.generallibrary.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * 回调的json处理
 * Created by yueguang on 16-12-2.
 */

public abstract class JsonModelCallBack<T> extends Callback<T> {

    public abstract T parseJson(String jsonStr) throws Exception;

    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        T result = null;
        try {
            if (response != null) {
                String info = response.body().string();
                result = parseJson(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
