package com.generallibrary.net;

        import com.generallibrary.okhttp.callback.Callback;

        import java.io.File;
        import java.util.Map;

/**
 * Created by yueguang on 16-12-15.
 */

public interface IHttpUtils {
    void get(String url, final Callback callBack);
    void get(String url, Map<String, String> params, final Callback callBack);
    void post(String url, Map<String, String> params, final Callback callBack);
    void postFile(String url, Map<String, String> params, String fileKey, File file, final Callback callBack);
    void postFile(String url, Map<String, String> params,final Callback callBack);
}
