package com.generallibrary.okhttp.builder;

import com.generallibrary.okhttp.OkHttpUtils;
import com.generallibrary.okhttp.request.OtherRequest;
import com.generallibrary.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
