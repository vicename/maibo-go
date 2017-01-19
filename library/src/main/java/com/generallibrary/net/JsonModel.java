package com.generallibrary.net;

/**
 * Created by Magina on 16/7/29.
 */
public class JsonModel<T> {

    public static final int HTTP_OK = 1;

    private String status;
    private T data;

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
