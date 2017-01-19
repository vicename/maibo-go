package com.generallibrary.net;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description gson解析工具类
 */
public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(String.class, new StringConverter());
            gson = gb.create();
        }
    }

    private GsonUtil() {
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
     * @param json      json字符串
     * @param typeToken 仿这个写法new TypeToken<List<String>>(){}
     * @param skipList  要忽略的字段
     * @param <T>       泛型
     * @return TypeToken中的泛型对象
     */
    public static <T> T parse(String json, TypeToken typeToken, List<String> skipList) {
        setSkiper(skipList);
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

    public static <T> T gsonToBean(String gsonString, Class<T> cls, List<String> skipList) {
        T t = null;
        if (gson != null) {
            try {
                setSkiper(skipList);
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

    private static class CustomExclusionStrategy implements ExclusionStrategy {
        List<String> skipList;

        public CustomExclusionStrategy(List<String> skipList) {
            this.skipList = skipList;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            boolean isSkip = false;
            for (String s : skipList) {
                if (f.getName().equals(s)) {
                    isSkip = true;
                }
            }
            return isSkip;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }

    private static void setSkiper(List<String> skipList) {
        if (skipList != null) {
            gson = new GsonBuilder().setExclusionStrategies(new CustomExclusionStrategy(skipList)).create();
        }
    }

    public static Gson getGsonWithSkiper(List<String> skipList) {
        Gson gsonWithSkiper;
        if (skipList != null) {
            gsonWithSkiper = new GsonBuilder().setExclusionStrategies(new CustomExclusionStrategy(skipList)).create();
        } else {
            gsonWithSkiper = gson;
        }
        return gsonWithSkiper;
    }
}
