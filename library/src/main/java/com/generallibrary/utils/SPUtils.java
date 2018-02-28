package com.generallibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Li DaChang on 10/20/2015.
 * ..-..---.-.--..---.-...-..-....-.
 */
public class SPUtils {
    private static final String DEFAULT_CONFIG = "config";

    private SharedPreferences mSharedPreferences;

    public SPUtils(Context context) {
        super();
        mSharedPreferences = context.getSharedPreferences(DEFAULT_CONFIG, Context.MODE_PRIVATE);
    }
    public SPUtils(Context context,String name) {
        super();
        mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 保存参数,自动识别存储类型
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value != null) {
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof Set) {
                editor.putStringSet(key, (Set<String>) value);
            } else if (value instanceof List) {
                Set<String> temp = new HashSet<>();
                temp.addAll((List<String>) value);
                editor.putStringSet(key, temp);
            } else {
                throw new IllegalArgumentException("不支持的数据类型");
            }
            editor.apply();
        }
    }

    /**
     * 清除所有数据
     */
    public void clearAllData() {
        mSharedPreferences.edit().clear().apply();
    }

    /**
     * 获取各项配置参数
     * 需要传入默认值,
     *
     * @return mSharedPreferences.getxxxx(key, defValue)
     */
    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public Integer getInt(String key, Integer defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float getFloat(String key, Float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Long getLong(String key, Long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public Set<String> getSet(String key, Set<String> defaultValue) {
        return mSharedPreferences.getStringSet(key, defaultValue);
    }

    public List<String> getList(String key, List<String> defaultValue) {
        Set<String> defaultValueSet = new HashSet<>();
        defaultValueSet.addAll(defaultValue);
        Set<String> resultSet = mSharedPreferences.getStringSet(key, defaultValueSet);
        List<String> result = new ArrayList<>();
        result.addAll(resultSet);
        return result;
    }
}