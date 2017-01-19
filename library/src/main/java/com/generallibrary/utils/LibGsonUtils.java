package com.generallibrary.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @data 2015/9/22
 */
public class LibGsonUtils {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private LibGsonUtils() {
    }

    /**
     * @param json      json字符串
     * @param typeToken 仿这个写法new TypeToken<List<String>>(){}
     * @param <T>       泛型
     * @return TypeToken中的泛型对象
     */
    public static <T> T parse(String json, TypeToken typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String gsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    public static List<String> gsonToList(String jsonString) {
        List<String> list = new ArrayList<String>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;

    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            try {
                t = gson.fromJson(gsonString, cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有list的
     *
     * @param gsonString
     * @return
     */
    public static List<List<String>> gsonToDoubleList(String gsonString) {
        List<List<String>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<List<String>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

}
